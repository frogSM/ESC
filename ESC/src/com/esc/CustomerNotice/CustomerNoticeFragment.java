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
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageView;

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
		
		ImageView noticeTitle = (ImageView)view.findViewById(R.id.IMAGEVIEW_NOTICETITLE);
		noticeTitle.setImageResource(R.drawable.img_noticetitle);

		jsonHelper = JsonHelper.getInstance(getActivity().getApplicationContext());
		socketHelper = SocketHelper.getInstance(getActivity().getApplicationContext());
		
		noticeListView = (ExpandableListView)view.findViewById(R.id.EXPANDABLELISTVIEW_NOTICE);
		notices = new ArrayList<Notice> ();
		noticeListAdpater = new NoticeListAdapter(getActivity().getApplicationContext(), notices);
		
		noticeListView.setAdapter(noticeListAdpater);

		getDBHandler = new GetDBHandler();
		
		/**������ NoticeDB�� ��û�� JSON���� �����**/
		String requestNoticeDB = jsonHelper.makeJsonMessage(Constants.requestNoticeDB, null);
		socketHelper.sendMessage( getDBHandler, requestNoticeDB);
		
	    // �׷� Ŭ�� ���� ��� �̺�Ʈ
        noticeListView.setOnGroupClickListener(new OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                    int groupPosition, long id) {
//                Toast.makeText(getActivity().getApplicationContext(), "g click = " + groupPosition, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
         
        // ���ϵ� Ŭ�� ���� ��� �̺�Ʈ
        noticeListView.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) {
//                Toast.makeText(getActivity().getApplicationContext(), "c click = " + childPosition, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
         
        // �׷��� ���� ��� �̺�Ʈ
        noticeListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getActivity().getApplicationContext(), "g Collapse = " + groupPosition, Toast.LENGTH_SHORT).show();
            }
        });
         
        // �׷��� ���� ��� �̺�Ʈ
        noticeListView.setOnGroupExpandListener(new OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
//               Toast.makeText(getActivity().getApplicationContext(), "g Expand = " + groupPosition, Toast.LENGTH_SHORT).show();
            }
        });
    
 
	    		
		return view;
	}
	
	 // Handler Ŭ����
    class GetDBHandler extends Handler {
         
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
             
            switch (msg.what) {
            case Constants.THREAD_MESSAGE:

            	notices = (ArrayList<Notice>)jsonHelper.parserJsonMessage(msg.obj.toString());
            	noticeListAdpater.UpdateNoticeListAdapter(notices);
            	noticeListAdpater.notifyDataSetChanged();
            	break;
            default:
                break;
            }
        }
    };
}
