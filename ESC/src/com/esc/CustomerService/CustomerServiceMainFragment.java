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
import com.esc.CustomerVoice.CustomerVoiceFragment;

public class CustomerServiceMainFragment extends Fragment implements OnClickListener {
	
	private View view;

	private Fragment fragment;
	private FragmentManager mFragmentManager; 
	private FragmentTransaction mFragmentTransaction;
	
	private Button noticeButton;
	private Button qnaButton;
	private Button productEvaluationButton;
	private Button customerNoiseButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		super.onCreateView(inflater, container, savedInstanceState);
		
		this.view = inflater.inflate(R.layout.fragment_customerservice, container, false);
	    		
		noticeButton = (Button)view.findViewById(R.id.BUTTON_NOTICE);
		qnaButton = (Button)view.findViewById(R.id.BUTTON_QNA);
		customerNoiseButton = (Button)view.findViewById(R.id.BUTTON_CUSTOMERVOICE);
		
		noticeButton.setOnClickListener(this);
		qnaButton.setOnClickListener(this);
		customerNoiseButton.setOnClickListener(this);

		fragment = new CustomerNoticeFragment();
		mFragmentManager = getFragmentManager();
		mFragmentTransaction = mFragmentManager.beginTransaction();
		mFragmentTransaction.replace(R.id.FRAGMENT_CUSTOMERSERVICE, fragment);
		mFragmentTransaction.commit();
		
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
		case R.id.BUTTON_CUSTOMERVOICE : 
			fm = new CustomerVoiceFragment();
			break;
		}
		
		Log.e("logFragment", "current child fragment view status : " + view.getId() );

		mFragmentTransaction = mFragmentManager.beginTransaction();
		mFragmentTransaction.replace(R.id.FRAGMENT_CUSTOMERSERVICE, fm);
		mFragmentTransaction.commit();
		
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

