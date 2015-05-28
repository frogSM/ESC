package com.esc;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.esc.Connection.JsonHelper;
import com.esc.Connection.SocketHelper;
import com.esc.CustomerService.CustomerServiceMainFragment;
import com.esc.MainAdvertise.BestProductAdapter;
import com.esc.printLocation.MapFragment;
import com.esc.productManager.CustomerCart;
import com.esc.productManager.ProductManager;
import com.esc.productManager.ProductManagerFragment;
import com.esc.searchProduct.SearchFragment;
import com.esc.shoppingBasket.BasketManager;
import com.esc.shoppingBasket.ShoppingBasketFragment;

public class MainActivity extends Activity {

	/** fragment를 위한 멤버변수 **/
	private Fragment mMainFragment;
	private FragmentManager mFragmentManager; 
	private FragmentTransaction mFragmentTransaction;
	
	/** 소켓 및 Json 도우미**/
	private SocketHelper mSocketHelper;
	private JsonHelper mJsonHelper;
	
	private ProductManager productManager;
	private BasketManager mBasketManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/** 첫 메인 화면 fragment 띄우기 **/
		mMainFragment = new mainFragment();
		mFragmentManager = getFragmentManager();
		mFragmentTransaction = mFragmentManager.beginTransaction();
		mFragmentTransaction.replace(R.id.layout_fragment, mMainFragment);
		mFragmentTransaction.commit();	
		
		mBasketManager = BasketManager.getInstance();
		productManager = new ProductManager(this);
		
		createDialog();
		
		//productManager.OpenSerialPort();
		
        
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {	
		/*
        View layoutMainView = (View)this.findViewById(R.id.main_ad_haed1);
        //*/
		//View saleButton = (View)this.findViewById(R.id.iv_ad_image1_left);

				/*
        Log.w("LayoutSize", String.valueOf(saleButton.getWidth()));
        Log.w("LayoutSize", String.valueOf(saleButton.getHeight()));
        //*/
        
        
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		productManager.CloseSerialPort();
		mBasketManager.getBasket().clear();
	}

	/** 액션바 상품검색버튼, 장바구니버튼 구현 **/
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		} else if (id == R.id.action_Search) {
			setTitle("상품검색");
			Fragment fm = new SearchFragment(this);
			mFragmentTransaction = mFragmentManager.beginTransaction();
			mFragmentTransaction.replace(R.id.layout_fragment, fm);
			mFragmentTransaction.addToBackStack(null);
			mFragmentTransaction.commit();
		} else if (id == R.id.action_setGoalPrice) {
			
			createDialog();
			
		} else if (id == R.id.action_Cart) {
			setTitle("장바구니");
			Fragment fm = new ShoppingBasketFragment();
			mFragmentTransaction = mFragmentManager.beginTransaction();
			mFragmentTransaction.replace(R.id.layout_fragment, fm);
			mFragmentTransaction.addToBackStack(null);
			mFragmentTransaction.commit();
		} else if(id == R.id.action_exit) {
			
			ArrayList<CustomerCart> products = new ArrayList<CustomerCart>();
//			products = productManager.getCustomerCart();
			// 임시데이터
			products.add(new CustomerCart("1", "상현1", "10000", "1", "상현컴퍼니1", "과자류1"));
			products.add(new CustomerCart("2", "상현2", "20000", "1", "상현컴퍼니2", "과자류2"));
			products.add(new CustomerCart("3", "상현3", "30000", "1", "상현컴퍼니3", "과자류3"));
			products.add(new CustomerCart("4", "상현4", "40000", "1", "상현컴퍼니4", "과자류4"));
			
			mSocketHelper = SocketHelper.getInstance(getApplicationContext());
			mJsonHelper = JsonHelper.getInstance(getApplicationContext());
			
			String str_json = mJsonHelper.makeJsonMessage(Constants.CustomerCart_Info_STORE, products);
			mSocketHelper.sendMessage(null, str_json);
			
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		
		// 스택에 있는 프래그먼트의 수가 0개가 아닐때는 이전 프래그먼트로 이동.
		if (mFragmentManager.getBackStackEntryCount() != 0)
			super.onBackPressed();
		else { // 만약 더이상 이전 프래그먼트가 없을 경우 종료대화상자 띄어줌.
			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
			dialog.setTitle("종료 대화상자");
			dialog.setMessage("정말 종료하시겠습니까?");
			dialog.setPositiveButton("네", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					finish();
				}
			});
			dialog.setNegativeButton("아니요", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					return;
				}
			});
			AlertDialog ad = dialog.create();
			ad.show();
		}
	}
	
	/** 메인액티비티 하단 각 기능 버튼 구현 **/
	public void onButtonClick(View v) {
		Fragment fm = null;
		
		switch(v.getId()) {
		case R.id.btn_main :
			fm  = new mainFragment();
			break;
		case R.id.btn_navi :
			fm = new MapFragment();
			break;
		case R.id.btn_product :
			fm = new ProductManagerFragment(this.productManager);
			break;
		case R.id.btn_customerService :
			fm = new CustomerServiceMainFragment();
			break;
		}
		mFragmentTransaction = mFragmentManager.beginTransaction();
		mFragmentTransaction.replace(R.id.layout_fragment, fm);
		mFragmentTransaction.addToBackStack(null);
		mFragmentTransaction.commit();
	}
	
	/** 쇼핑 목표금액 설정하는 다이얼로그 **/
	private void createDialog() {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		final View layout = getLayoutInflater().inflate(R.layout.dialog_goalprice, null);
		
		// 금액 초기화
		final EditText edittext = (EditText)layout.findViewById(R.id.et_price);
		edittext.setText(String.valueOf(mBasketManager.getGoalPrice()));
		edittext.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				edittext.setText("");
			}
		});
		
		// 포커스 가장 뒤로 이동
		Editable etext = edittext.getText();
		Selection.setSelection(etext, etext.length());
		
		builder.setTitle("쇼핑 목표금액 설정");
		builder.setView(layout);
		builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				mBasketManager.setGoalPrice(Integer.valueOf(edittext.getText().toString()));
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
		
	}
	
	public class mainFragment extends Fragment implements android.view.View.OnClickListener{
		
		/** inflater한 fragment레이아웃 정보 **/
		private View mView;
		
		private ListView mListView;
		private Button mButtonTen;
		private Button mButtonTwenty;
		private Button mButtonThirty;
		private Button mButtonForty;
		private Button mButtonFifty;
		
		private BestProductAdapter mAdapter;
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			mView = inflater.inflate(R.layout.fragment_main, container, false);
			dataSetting();
			
			return mView;
		}
		
		private void dataSetting() {
			
			mListView = (ListView) mView.findViewById(R.id.listview_main_bestproduct);
			mButtonTen = (Button) mView.findViewById(R.id.btn_ten);
			mButtonTen.setOnClickListener(this);
			mButtonTwenty = (Button) mView.findViewById(R.id.btn_twenty);
			mButtonTwenty.setOnClickListener(this);
			mButtonThirty = (Button) mView.findViewById(R.id.btn_thirty);
			mButtonThirty.setOnClickListener(this);
			mButtonForty = (Button) mView.findViewById(R.id.btn_forty);
			mButtonForty.setOnClickListener(this);
			mButtonFifty = (Button) mView.findViewById(R.id.btn_fifty);
			mButtonFifty.setOnClickListener(this);
			
			mAdapter = new BestProductAdapter(getActivity().getApplicationContext(), 10);
			mListView.setAdapter(mAdapter);
			
			// 취소선 추가
			TextView tv_cancelline1 =  (TextView)mView.findViewById(R.id.tv_cancel_line1);
			tv_cancelline1.setPaintFlags(tv_cancelline1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
			TextView tv_cancelline2 =  (TextView)mView.findViewById(R.id.tv_cancel_line2);
			tv_cancelline2.setPaintFlags(tv_cancelline2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
			TextView tv_cancelline3 =  (TextView)mView.findViewById(R.id.tv_cancel_line3);
			tv_cancelline3.setPaintFlags(tv_cancelline3.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
			TextView tv_cancelline4 =  (TextView)mView.findViewById(R.id.tv_cancel_line4);
			tv_cancelline4.setPaintFlags(tv_cancelline4.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
			
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v.getId()  == R.id.btn_ten) {
				mAdapter = new BestProductAdapter(getActivity().getApplicationContext(), 10);
			} else if(v.getId() == R.id.btn_twenty) {
				mAdapter = new BestProductAdapter(getActivity().getApplicationContext(), 20);
			} else if(v.getId() == R.id.btn_thirty) {
				mAdapter = new BestProductAdapter(getActivity().getApplicationContext(), 30);
			} else if(v.getId() == R.id.btn_forty) {
				mAdapter = new BestProductAdapter(getActivity().getApplicationContext(), 40);
			} else if(v.getId() == R.id.btn_fifty) {
				mAdapter = new BestProductAdapter(getActivity().getApplicationContext(), 50);
			}
			mListView.setAdapter(mAdapter);
		}
		
	
	}
}
