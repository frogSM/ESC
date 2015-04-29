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
	
	/** ���� �Ҹ����� ���̴� ��� ���ǳ� **/
	private Spinner mSpinnerPhone;
	private Spinner mSpinnerMail;
	private Spinner mSpinnerMethod;
	private Spinner mSpinnerContentType;
	
	/** ���ǳ��� �� �߰� �κ� **/
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
							"���� ������ ���۵Ǿ����ϴ�.", Toast.LENGTH_SHORT).show();
			}
		});
		
		return view;
	}
	
	private void spinnerSetting() {
		// phone ���ǳ� ����
		//mSpinnerPhone.setPrompt("�޴��� �� ��ȣ");
		ArrayList<String> phone = new ArrayList<String>();
		phone.add("010");
		phone.add("011");
		phone.add("016");
		phone.add("017");
		phone.add("019");
		ArrayAdapter<String> phoneListAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
				R.layout.item_spinner, phone);
		mSpinnerPhone.setAdapter(phoneListAdapter);
		
		// mail ���ǳ� ����
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
		
		// method ���ǳ� ����
		ArrayList<String> method = new ArrayList<String>();
		method.add("�ڵ���");
		method.add("�̸���(SMS �������)");
		method.add("�亯 ���ʿ�");
		ArrayAdapter<String> methodListAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
				R.layout.item_spinner, method);
		mSpinnerMethod.setAdapter(methodListAdapter);
		
		// contenttype ���ǳ� ����
		ArrayList<String> contenttype = new ArrayList<String>();
		contenttype.add("Ī�����");
		contenttype.add("�������� �Ҹ�");
		contenttype.add("��ǰǰ��/���ݺҸ�");
		contenttype.add("����");
		contenttype.add("�Ϲݹ��ǻ���");
		contenttype.add("��Ÿ");
		ArrayAdapter<String> contenttypeListAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
				R.layout.item_spinner, contenttype);
		mSpinnerContentType.setAdapter(contenttypeListAdapter);
	}

}
