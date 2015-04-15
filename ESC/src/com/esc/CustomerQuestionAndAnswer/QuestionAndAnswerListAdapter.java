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
        	v = inflater.inflate(R.layout.listview_question, parent, false);
            
        	viewHolder.questionImage = (ImageView)v.findViewById(R.id.IMAGEVIEW_QUESTION);
        	viewHolder.questionTitle = (TextView)v.findViewById(R.id.TEXTVIEW_TITLE);
        	
        	v.setTag(viewHolder);
        } else{
        	viewHolder = (ViewHolder)v.getTag();
        }

        viewHolder.questionImage.setImageResource(R.drawable.img_question);
        viewHolder.questionTitle.setText(questionAndAnswers.get(groupPosition).getQuestionTitle());
        
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
        	v = inflater.inflate(R.layout.listview_answer, parent, false);
            
            viewHolder.answerImage = (ImageView) v.findViewById(R.id.IMAGEVIEW_ANSWER);
            viewHolder.answerContent = (TextView) v.findViewById(R.id.TEXTVIEW_ANSWERCONTENT);
            v.setTag(viewHolder);
            
        }else{
            viewHolder = (ViewHolder)v.getTag();
        }
         
        viewHolder.answerImage.setImageResource(R.drawable.img_answer);
        viewHolder.answerContent.setText(this.contents.get(groupPosition).get(childPosition));   
         
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
	
	public void UpdateNoticeListAdapter( ArrayList<QuestionAndAnswer> renewdQuestionAndAnswers ) {

		this.questionAndAnswers = renewdQuestionAndAnswers;

		/**차일드 뷰 반환을 위해 questionAndAnswers객체에서 content값 뽑아오기**/
		for(int i = 0 ; i < questionAndAnswers.size() ; i ++ ) { 
			ArrayList<String> content = new ArrayList<String> ( ) ;
			content.add(questionAndAnswers.get(i).getAnswerContent() );
			this.contents.add(content);
		}
	}
	
	class ViewHolder {
		ImageView questionImage;
		TextView questionTitle;
		ImageView answerImage;
		TextView answerContent;
	}

}
