package com.esc.CustomerNotice;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.esc.Constants;
import com.esc.R;
import com.esc.Connection.JsonHelper;
import com.esc.Connection.SocketHelper;

public class CustomerNoticeFragment extends Fragment {

	SocketHelper socketHelper;
	JsonHelper jsonHelper;
	
	GetDBHandler getDBHandler;
	
	NoticeListAdapter noticeListAdpater;
	ExpandableListView noticeListView;

	ArrayList<Notice> notices;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_customernotice, container, false);

		jsonHelper = JsonHelper.getInstance(getActivity().getApplicationContext());
		socketHelper = SocketHelper.getInstance(getActivity().getApplicationContext());
		
		noticeListView = (ExpandableListView)getActivity().findViewById(R.id.EXPANDABLELISTVIEW_NOTICE);
		notices = new ArrayList<Notice> ();
		noticeListAdpater = new NoticeListAdapter(getActivity().getApplicationContext(), notices);
		
		getDBHandler = new GetDBHandler();
		
		noticeListView.setAdapter(noticeListAdpater);
		
		/**서버에 NoticeDB를 요청할 JSON문장 만들기**/
		String requestNoticeDB = jsonHelper.makeJsonMessage(Constants.requestNoticeDB, null);
		socketHelper.sendMessage( getDBHandler, requestNoticeDB);
	    		
		return view;
	}
	
	 // Handler 클래스
    class GetDBHandler extends Handler {
         
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
             
            switch (msg.what) {
            case Constants.THREAD_MESSAGE:
            	break;
            default:
                break;
            }
        }
         
    };
	

}
