package com.esc.CustomerQuestionAndAnswer;

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

public class QuestionAndAnswerListAdapter extends BaseExpandableListAdapter {
	
	private Context context;
	private ArrayList<QuestionAndAnswer> questionAndAnswers;
	private ViewHolder viewHolder;
	private ArrayList<ArrayList<String>> contents = new ArrayList<ArrayList<String>> ();


	public QuestionAndAnswerListAdapter ( Context context, ArrayList<QuestionAndAnswer> questionAndAnswers ){ 
		this.context = context;
		this.questionAndAnswers = questionAndAnswers;
	}

	@Override
	public int getGroupCount() {
		return this.questionAndAnswers.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.questionAndAnswers.get(groupPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}
	
	@Override
	public int getChildrenCount(int groupPosition) {
		return this.contents.get(groupPosition).size();
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
         
        int logoIntegerValue = context.getResources().getIdentifier(this.questionAndAnswers.get(groupPosition).getLogo(),"drawable",context.getPackageName() ); 
        viewHolder.logo.setImageResource(logoIntegerValue);
        viewHolder.logo.setScaleType(ImageView.ScaleType.FIT_XY);
        viewHolder.title.setText(questionAndAnswers.get(groupPosition).getTitle());
        viewHolder.date.setText(questionAndAnswers.get(groupPosition).getDate());
        
        return v;
    }

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return this.contents.get(groupPosition).get(childPosition);
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
         
        int contentIntegerValue = context.getResources().getIdentifier( this.contents.get(groupPosition).get(childPosition),"drawable",context.getPackageName() ); 
        viewHolder.content.setImageResource(contentIntegerValue);
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
	
	public void UpdateNoticeListAdapter( ArrayList<Notice> renewdquestionAndAnswers ) {

		this.questionAndAnswers = renewdquestionAndAnswers;

		/**���ϵ� �� ��ȯ�� ���� questionAndAnswers��ü���� content�� �̾ƿ���**/
		for(int i = 0 ; i < questionAndAnswers.size() ; i ++ ) { 
			ArrayList<String> content = new ArrayList<String> ( ) ;
			content.add(questionAndAnswers.get(i).getContent() );
			contents.add(content);
		}
	}
	
	class ViewHolder {
		ImageView logo;
		TextView title;
		TextView date;
		ImageView content;
	}

}
