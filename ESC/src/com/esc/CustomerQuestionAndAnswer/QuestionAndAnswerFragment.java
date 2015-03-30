package com.esc.CustomerQuestionAndAnswer;

import java.util.ArrayList;

import android.R.anim;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.esc.Constants;
import com.esc.R;
import com.esc.Connection.JsonHelper;
import com.esc.Connection.SocketHelper;

public class QuestionAndAnswerFragment extends Fragment{
	
	SocketHelper socketHelper;
	JsonHelper jsonHelper;
	GetDBHandler getDBHandler;

	Spinner spinner;
	ArrayList<String> categories;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_questionandanswer, container, false);

		jsonHelper = JsonHelper.getInstance(getActivity().getApplicationContext());
		socketHelper = SocketHelper.getInstance(getActivity().getApplicationContext());
		
		/** 1. ���ǳʿ� �з����� �߰��Ѵ�. **/
		
		categories.add("BEST5");
		categories.add("��ȯ & ȯ�� ");
		categories.add("����");
		categories.add("��Ʈ�̿�");
		
		ArrayAdapter<String> adaper = new ArrayAdapter( getActivity(), android.R.layout.simple_spinner_dropdown_item, categories );
		spinner = (Spinner)view.findViewById(R.id.SPINNER_CATEGORIES);
		spinner.setAdapter(adaper);
		//�⺻ ����Ʈ ������ BEST 5�� ����.
		spinner.setSelection(0);
		
		/** 2.DB�� ��û�� JSON������ ����� ������ �����Ѵ�. **/
		
		String requestBest5QADB = jsonHelper.makeJsonMessage(Constants.requestBest5QADB, null);
		socketHelper.sendMessage( getDBHandler, requestBest5QADB);
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	

}


class GetDBHandler extends Handler { 

}
