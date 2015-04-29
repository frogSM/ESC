package com.esc.CustomerVoice;

import java.util.ArrayList;

import com.esc.R;

import android.app.Fragment;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerVoiceFragment extends Fragment {
	
	/** 고객의 소리에서 쓰이는 모든 스피너 **/
	private Spinner mSpinnerPhone;
	private Spinner mSpinnerMail;
	private Spinner mSpinnerMethod;
	private Spinner mSpinnerContentType;
	
	/** 문의내용 상세 추가 부분 **/
	private LinearLayout mLinearLayout;
	private CheckBox mCheckBox;
	private Button mButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_customervoice, container, false);
		
		mSpinnerPhone = (Spinner)view.findViewById(R.id.spinner_phone);
		mSpinnerMail = (Spinner)view.findViewById(R.id.spinner_mail);
		mSpinnerMethod = (Spinner)view.findViewById(R.id.spinner_method);
		mSpinnerContentType = (Spinner)view.findViewById(R.id.spinner_content_type);
		
		spinnerSetting();
		
		mLinearLayout = (LinearLayout)view.findViewById(R.id.ll_customervoice_detail);
		mCheckBox = (CheckBox)view.findViewById(R.id.cb_infoAgree);
		mButton = (Button)view.findViewById(R.id.btn_send);
		
		mCheckBox.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mCheckBox.isChecked()) {
					mLinearLayout.setVisibility(View.VISIBLE);
				} else {
					mLinearLayout.setVisibility(View.INVISIBLE);
				}
			}
		});
		
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v.getId() == R.id.btn_send)
					Toast.makeText(getActivity().getApplicationContext(), 
							"문의 내용이 전송되었습니다.", Toast.LENGTH_SHORT).show();
			}
		});
		
		return view;
	}
	
	private void spinnerSetting() {
		// phone 스피너 설정
		//mSpinnerPhone.setPrompt("휴대폰 앞 번호");
		ArrayList<String> phone = new ArrayList<String>();
		phone.add("010");
		phone.add("011");
		phone.add("016");
		phone.add("017");
		phone.add("019");
		ArrayAdapter<String> phoneListAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
				R.layout.item_spinner, phone);
		mSpinnerPhone.setAdapter(phoneListAdapter);
		
		// mail 스피너 설정
		ArrayList<String> mailAddress = new ArrayList<String>();
		mailAddress.add("gmail.com");
		mailAddress.add("nate.com");
		mailAddress.add("naver.com");
		mailAddress.add("hotmail.com");
		mailAddress.add("korea.com");
		mailAddress.add("kornet.com");
		mailAddress.add("lycos.co.kr");
		mailAddress.add("hanmail.net");
		ArrayAdapter<String> mailListAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
				R.layout.item_spinner, mailAddress);
		mSpinnerMail.setAdapter(mailListAdapter);
		
		// method 스피너 설정
		ArrayList<String> method = new ArrayList<String>();
		method.add("핸드폰");
		method.add("이메일(SMS 수신허용)");
		method.add("답변 불필요");
		ArrayAdapter<String> methodListAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
				R.layout.item_spinner, method);
		mSpinnerMethod.setAdapter(methodListAdapter);
		
		// contenttype 스피너 설정
		ArrayList<String> contenttype = new ArrayList<String>();
		contenttype.add("칭찬사례");
		contenttype.add("접객서비스 불만");
		contenttype.add("상품품질/가격불만");
		contenttype.add("제안");
		contenttype.add("일반문의사항");
		contenttype.add("기타");
		ArrayAdapter<String> contenttypeListAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
				R.layout.item_spinner, contenttype);
		mSpinnerContentType.setAdapter(contenttypeListAdapter);
	}

}
