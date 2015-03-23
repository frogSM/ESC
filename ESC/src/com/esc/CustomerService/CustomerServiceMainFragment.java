package com.esc.CustomerService;

import java.util.ArrayList;

import com.esc.R;
import com.esc.CustomerNotice.CustomerNoticeFragment;
import com.esc.MainActivity.mainFragment;
import com.esc.printLocation.NavigationFragment;
import com.esc.productManager.ProductManagerFragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class CustomerServiceMainFragment extends Fragment {
	View view;

	private Fragment fragment;
	private FragmentManager mFragmentManager; 
	private FragmentTransaction mFragmentTransaction;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		this.view = inflater.inflate(R.layout.fragment_customerservice, container, false);
	    		
		fragment = new CustomerNoticeFragment();
		mFragmentManager = getFragmentManager();
		mFragmentTransaction = mFragmentManager.beginTransaction();
		mFragmentTransaction.replace(R.id.FRAGMENT_CUSTOMERSERVICE, fragment);
		mFragmentTransaction.commit();
		
		return view;
	}
	
	public void onButtonClick(View v) {
		Fragment fm = null;
		
		switch(v.getId()) {
		case R.id.BUTTON_NOTICE :
			fm  = new CustomerNoticeFragment();
			break;
		case R.id.BUTTON_QNA :
			fm = new QuestionAndAnswerFragment();
			break;
		case R.id.BUTTON_PRODUCTEVALUATION :
			fm = new ProductEvaluationFragment();
			break;
		case R.id.BUTTON_CUSTOMERNOISE :
			fm = new CustomerMessageFragment();
			break;
		}
		mFragmentTransaction = mFragmentManager.beginTransaction();
		mFragmentTransaction.replace(R.id.FRAGMENT_CUSTOMERSERVICE, fm);
		mFragmentTransaction.commit();
	}

}
