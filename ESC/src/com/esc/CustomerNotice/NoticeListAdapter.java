package com.esc.CustomerNotice;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NoticeListAdapter extends BaseExpandableListAdapter {
	
	private Context context;
	private ArrayList<Notice> notices;


	public NoticeListAdapter ( Context context, ArrayList<Notice> notices ){ 
		this.context = context;
		this.notices = notices;
	}

	@Override
	public int getGroupCount() {
		return this.notices.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.notices.get(groupPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}
	
	@Override
	public int getChildrenCount(int groupPosition) {
		return this.notices.get(groupPosition).GetContent().size();
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return this.notices.get(groupPosition).GetContent().get(childPosition);
	}


	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}


	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}
	
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	
	class ViewHolder {
		ImageView logo;
		TextView title;
		TextView date;
		ImageView content;
	}

}
