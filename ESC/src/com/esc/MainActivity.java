package com.esc;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.esc.CustomerService.CustomerServiceMainFragment;
import com.esc.MainAdvertise.BestProductAdapter;
import com.esc.printLocation.MapFragment;
import com.esc.productManager.ProductManager;
import com.esc.productManager.ProductManagerFragment;
import com.esc.searchProduct.SearchFragment;
import com.esc.shoppingBasket.BasketManager;
import com.esc.shoppingBasket.ShoppingBasketFragment;

public class MainActivity extends Activity {

	/** fragment�� ���� ������� **/
	private Fragment mMainFragment;
	private FragmentManager mFragmentManager; 
	private FragmentTransaction mFragmentTransaction;
	
	private ProductManager productManager;
	private BasketManager mBasketManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/** ù ���� ȭ�� fragment ���� **/
		mMainFragment = new mainFragment();
		mFragmentManager = getFragmentManager();
		mFragmentTransaction = mFragmentManager.beginTransaction();
		mFragmentTransaction.replace(R.id.layout_fragment, mMainFragment);
		mFragmentTransaction.commit();	
		
		mBasketManager = BasketManager.getInstance();
		productManager = new ProductManager(this);
//		productManager.OpenSerialPort();
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
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

	/** �׼ǹ� ��ǰ�˻���ư, ��ٱ��Ϲ�ư ���� **/
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		} else if (id == R.id.action_Search) {
			setTitle("��ǰ�˻�");
			Fragment fm = new SearchFragment();
			mFragmentTransaction = mFragmentManager.beginTransaction();
			mFragmentTransaction.replace(R.id.layout_fragment, fm);
			mFragmentTransaction.addToBackStack(null);
			mFragmentTransaction.commit();
		} else if (id == R.id.action_Cart) {
			setTitle("��ٱ���");
			Fragment fm = new ShoppingBasketFragment();
			mFragmentTransaction = mFragmentManager.beginTransaction();
			mFragmentTransaction.replace(R.id.layout_fragment, fm);
			mFragmentTransaction.addToBackStack(null);
			mFragmentTransaction.commit();
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		
		// ���ÿ� �ִ� �����׸�Ʈ�� ���� 0���� �ƴҶ��� ���� �����׸�Ʈ�� �̵�.
		if (mFragmentManager.getBackStackEntryCount() != 0)
			super.onBackPressed();
		else { // ���� ���̻� ���� �����׸�Ʈ�� ���� ��� �����ȭ���� �����.
			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
			dialog.setTitle("���� ��ȭ����");
			dialog.setMessage("���� �����Ͻðڽ��ϱ�?");
			dialog.setPositiveButton("��", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					finish();
				}
			});
			dialog.setNegativeButton("�ƴϿ�", new OnClickListener() {

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
	
	/** ���ξ�Ƽ��Ƽ �ϴ� �� ��� ��ư ���� **/
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
	
	public class mainFragment extends Fragment implements android.view.View.OnClickListener{
		
		/** inflater�� fragment���̾ƿ� ���� **/
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
