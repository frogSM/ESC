package com.esc.CustomerQuestionAndAnswer;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import com.esc.Constants;
import com.esc.R;
import com.esc.Connection.JsonHelper;
import com.esc.Connection.SocketHelper;
import com.esc.CustomerNotice.Notice;
import com.esc.CustomerNotice.NoticeListAdapter;

public class QuestionAndAnswerFragment extends Fragment{
	
	SocketHelper socketHelper;
	JsonHelper jsonHelper;
	GetDBHandler getDBHandler;

	Spinner spinner;
	ArrayList<String> categories;
	
	ArrayList<QuestionAndAnswer> questionAndAnswers;
	QuestionAndAnswerListAdapter questionAndAnswerListAdapter;
	
	ExpandableListView qaListView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_questionandanswer, container, false);

		jsonHelper = JsonHelper.getInstance(getActivity().getApplicationContext());
		socketHelper = SocketHelper.getInstance(getActivity().getApplicationContext());
		getDBHandler = new GetDBHandler( );
		categories = new ArrayList<String> ( );
		qaListView = (ExpandableListView) view.findViewById(R.id.EXPANDABLELISTVIEW_QUESTIONANDANSWER);
		questionAndAnswers = new ArrayList<QuestionAndAnswer> ( );
		questionAndAnswerListAdapter = new QuestionAndAnswerListAdapter(getActivity().getApplicationContext(), questionAndAnswers);
				
		/** 1. 스피너에 분류값을 추가한다. **/
		
		categories.add("BEST5");
		categories.add("교환 & 환불 ");
		categories.add("결제");
		categories.add("마트이용");
		
		ArrayAdapter<String> spinnerAdaper = new ArrayAdapter( getActivity(), android.R.layout.simple_spinner_dropdown_item, categories );
		spinner = (Spinner)view.findViewById(R.id.SPINNER_CATEGORIES);
		spinner.setAdapter(spinnerAdaper);
		//기본 디폴트 값으로 BEST 5로 지정.
		spinner.setSelection(0);
		
		
		/** 2. Best5 DB를 요청할 JSON문장을 만들고 서버에 전송한다. **/
		
		String requestBest5QADB = jsonHelper.makeJsonMessage(Constants.requestBest5QADB, null);
		socketHelper.sendMessage( getDBHandler, requestBest5QADB);
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	
	
	class GetDBHandler extends Handler {
		
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			
			switch (msg.what) {
			case Constants.THREAD_MESSAGE:
				
				questionAndAnswers = (ArrayList<QuestionAndAnswer>)jsonHelper.parserJsonMessage(msg.obj.toString());
				questionAndAnswerListAdapter.UpdateNoticeListAdapter(questionAndAnswers);
				questionAndAnswerListAdapter.notifyDataSetChanged();
				break;
				
			default:
				break;
			}
		}
	};
}
