package com.esc;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

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
		mFragmentTransaction.replace(R.id.layout_fragment, mMainFragment);
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
			mFragmentTransaction.replace(R.id.layout_fragment, fm);
			mFragmentTransaction.commit();
		}
		return super.onOptionsItemSelected(item);
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
		case R.id.btn_exit :
			break;
		}
		mFragmentTransaction = mFragmentManager.beginTransaction();
		mFragmentTransaction.replace(R.id.layout_fragment, fm);
		mFragmentTransaction.commit();
	}
	
	public class mainFragment extends Fragment {
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			View v = inflater.inflate(R.layout.fragment_main, container, false);
			
			return v;
		}
	}
}
