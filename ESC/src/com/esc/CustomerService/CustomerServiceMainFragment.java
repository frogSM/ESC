package com.esc.CustomerService;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.esc.R;
import com.esc.CustomerNotice.CustomerNoticeFragment;
import com.esc.CustomerQuestionAndAnswer.QuestionAndAnswerFragment;

public class CustomerServiceMainFragment extends Fragment implements OnClickListener {
	
	private View view;

	private Fragment fragment;
	private FragmentManager mFragmentManager; 
	private FragmentTransaction mFragmentTransaction;
	
	private Button btn1;
	private Button btn2;
	private Button btn3;
	private Button btn4;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		super.onCreateView(inflater, container, savedInstanceState);
		
		this.view = inflater.inflate(R.layout.fragment_customerservice, container, false);
	    		
		btn1 = (Button)view.findViewById(R.id.BUTTON_NOTICE);
		btn2 = (Button)view.findViewById(R.id.BUTTON_QNA);
		btn3 = (Button)view.findViewById(R.id.BUTTON_PRODUCTEVALUATION);
		btn4 = (Button)view.findViewById(R.id.BUTTON_CUSTOMERNOISE);
		
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		
//		fragment = new CustomerNoticeFragment();
//		mFragmentTransaction = getChildFragmentManager().beginTransaction();
//		mFragmentTransaction.add(R.id.FRAGMENT_CUSTOMERSERVICE, fragment).commit();
		Log.d("logFragment", "childFragment is "+ getChildFragmentManager());
		

		fragment = new QuestionAndAnswerFragment();
		mFragmentManager = getFragmentManager();
		mFragmentTransaction = mFragmentManager.beginTransaction();
		mFragmentTransaction.replace(R.id.FRAGMENT_CUSTOMERSERVICE, fragment);
		mFragmentTransaction.addToBackStack(null);
		mFragmentTransaction.commit();
		
//		mFragmentTransaction.replace(R.id.FRAGMENT_CUSTOMERSERVICE, fragment);
//		mFragmentTransaction.commit();
//		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		
		
		return view;
	}

	@Override
	public void onClick(View v) {
		
		this.view = v;
		
		Log.d("logFragment", "main frament view id : " + this.view.getId());
	
		Fragment fm = null ;
		switch (this.view.getId()) {
		
		case R.id.BUTTON_NOTICE:
			fm = new CustomerNoticeFragment();
			break;
		case R.id.BUTTON_QNA:
			fm = new QuestionAndAnswerFragment();
			break;
		case R.id.BUTTON_PRODUCTEVALUATION :
			fm = new ProductEvaluationFragment();
			break;
		case R.id.BUTTON_CUSTOMERNOISE : 
			fm = new CustomerMessageFragment();
			break;
		}
		
		Log.e("logFragment", "current child fragment view status : " + view.getId() );

		mFragmentTransaction  = getChildFragmentManager().beginTransaction();
		mFragmentTransaction.add(R.id.FRAGMENT_CUSTOMERSERVICE, fm).commit();
//		mFragmentTransaction.replace(R.id.FRAGMENT_CUSTOMERSERVICE, fm);
//		mFragmentTransaction.addToBackStack(null);
//		mFragmentTransaction.commit();
		
		
//		Log.i("logFragment", "current Fragment status : " + fm.toString());
	}
	
	@Override
	public void onDetach() {
		Log.i("logFragment","onDatachCalled");
		super.onDetach();
	}
	
	@Override
	public void onDestroyView() {
		if ( this.view != null ) {
	        ViewGroup parent = (ViewGroup)this.view.getParent();
	        if(parent!=null){
	            parent.removeView(this.view);
	        }
	    }
		Log.i("logFragment","onDestroyViewCalled");
		super.onDestroyView();
	}
	
	@Override
	public void onDestroy() {
		Log.i("logFragment","onDestroyCalled");
		super.onDestroy();
	}
}

