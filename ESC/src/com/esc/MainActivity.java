package com.esc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import com.esc.CustomerService.CustomerServiceMainFragment;
import com.esc.printLocation.NavigationFragment;
import com.esc.productManager.ProductManager;
import com.esc.productManager.ProductManagerFragment;
import com.esc.searchProduct.SearchFragment;

public class MainActivity extends FragmentActivity {

	private Fragment mMainFragment;
	private FragmentManager mFragmentManager; 
	private FragmentTransaction mFragmentTransaction;
	private ProductManager productManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/** 첫 메인 화면 fragment 띄우기 **/
		mMainFragment = new mainFragment();
		mFragmentManager = getFragmentManager();
		mFragmentTransaction = mFragmentManager.beginTransaction();
		mFragmentTransaction.replace(R.id.Button, mMainFragment);
		mFragmentTransaction.commit();
		
		productManager = new ProductManager(this);
		productManager.OpenSerialPort();
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
			Fragment fm = new SearchFragment();
			mFragmentTransaction = mFragmentManager.beginTransaction();
			mFragmentTransaction.replace(R.id.Button, fm);
			mFragmentTransaction.commit();
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
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
				return ;
			}
		});
		AlertDialog ad = dialog.create();
		ad.show();
	}
	
	/** 메인액티비티 하단 각 기능 버튼 구현 **/
	public void onButtonClick(View v) {
		Fragment fm = null;
		
		switch(v.getId()) {
		case R.id.btn_main :
			fm  = new mainFragment();
			break;
		case R.id.btn_navi :
			fm = new NavigationFragment();
			break;
		case R.id.btn_product :
			fm = new ProductManagerFragment(this.productManager);
			break;
		case R.id.btn_customerService :
			fm = new CustomerServiceMainFragment();
			break;
		}
		mFragmentTransaction = mFragmentManager.beginTransaction();
		mFragmentTransaction.replace(R.id.Button, fm);
		mFragmentTransaction.commit();
	}
	
	public class mainFragment extends Fragment {
		
		/** inflater한 fragment레이아웃 정보 **/
		private View mView;
		
		private ViewFlipper mViewFlipper;
		private int m_nPreTouchPosX = 0;
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			mView = inflater.inflate(R.layout.fragment_main, container, false);
			
			mViewFlipper = (ViewFlipper)mView.findViewById(R.id.main_viewflipper);
			mViewFlipper.setOnTouchListener(MyTouchListener);
			
			return mView;
		}
		
		private void MoveNextView() {
			mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
					R.anim.appear_from_right));
			mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
					R.anim.disappear_to_left));
			mViewFlipper.showNext();
		}

		private void MovewPreviousView() {
			mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
					R.anim.appear_from_left));
			mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
					R.anim.disappear_to_right));
			mViewFlipper.showPrevious();
		}

		View.OnTouchListener MyTouchListener = new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					m_nPreTouchPosX = (int) event.getX();
				}

				if (event.getAction() == MotionEvent.ACTION_UP) {
					int nTouchPosX = (int) event.getX();

					if (nTouchPosX < m_nPreTouchPosX) {
						MoveNextView();
					} else if (nTouchPosX > m_nPreTouchPosX) {
						MovewPreviousView();
					}

					m_nPreTouchPosX = nTouchPosX;
				}

				return true;
			}
		};
	}
}
