package com.esc.CustomerNotice;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.esc.R;

public class NoticeListAdapter extends BaseExpandableListAdapter {
	
	private Context context;
	private ArrayList<Notice> notices;
	private ViewHolder viewHolder;


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
		return this.notices.get(groupPosition).getContent().size();
	}

   @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
         
        View v = convertView;
         
        if(v == null){

        	viewHolder = new ViewHolder();

        	LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        	v = inflater.inflate(R.layout.listview_notice, parent, false);
            
        	viewHolder.logo = (ImageView)v.findViewById(R.id.IMAGEVIEW_LOGO);
        	viewHolder.title = (TextView) v.findViewById(R.id.TEXTVIEW_TITLE);
        	viewHolder.date = (TextView) v.findViewById(R.id.TEXTVIEW_DATE);
        	
        	v.setTag(viewHolder);
        } else{
        	viewHolder = (ViewHolder)v.getTag();
        }
         
//        // 그룹을 펼칠때와 닫을때 아이콘을 변경해 준다.
//        if(isExpanded){
//            viewHolder.iv_image.setBackgroundColor(Color.GREEN);
//        }else{
//            viewHolder.iv_image.setBackgroundColor(Color.WHITE);
//        }
         
        int logoIntegerValue = context.getResources().getIdentifier(this.notices.get(groupPosition).getLogo(),"drawable",context.getPackageName() ); 
        viewHolder.logo.setImageResource(logoIntegerValue);
        viewHolder.logo.setScaleType(ImageView.ScaleType.FIT_XY);
        viewHolder.title.setText(notices.get(groupPosition).getTitle());
        viewHolder.date.setText(notices.get(groupPosition).getDate());
        
        return v;
    }

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return this.notices.get(groupPosition).getContent().get(childPosition);
	}


	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
         
        View v = convertView;
         
        if(v == null){

        	viewHolder = new ViewHolder();

        	LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        	v = inflater.inflate(R.layout.imageview_noticecontent, parent, false);
            
            viewHolder.content = (ImageView) v.findViewById(R.id.IMAGEVIEW_NOTICECONTENT);
            v.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)v.getTag();
        }
         
        int StringIntegerImageValue = context.getResources().getIdentifier(this.notices.get(groupPosition).getContent().get(childPosition),"drawable",context.getPackageName() ); 
        viewHolder.content.setImageResource(StringIntegerImageValue);
        viewHolder.content.setScaleType(ImageView.ScaleType.FIT_XY);        
         
        return v;
    }
 

	@Override
	public boolean hasStableIds() {
		return true;
	}
	
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	
	public void UpdateNoticeListAdapter( ArrayList<Notice> renewdNotices ) {
		this.notices = renewdNotices;
	}
	
	class ViewHolder {
		ImageView logo;
		TextView title;
		TextView date;
		ImageView content;
	}

}
