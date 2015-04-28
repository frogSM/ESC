package com.esc.searchProduct;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RatingBar;
import android.widget.TextView;

import com.esc.R;
import com.esc.productManager.Product;

public class SearchListAdapter extends BaseAdapter implements Filterable {
	
	private Context mContext;
	
	/** �������� ��üDB ��ǰ **/
	private ArrayList<Product> mOriginal;
	/** ��Ȳ�� �°� ��� ���ϴ� ���͸� �� ��ǰ **/
	private ArrayList<Product> mSearchProduct;
	
	public SearchListAdapter(Context context, ArrayList<Product> searchProduct) {
		// TODO Auto-generated constructor stub
		super();
		this.mContext = context;
		this.mSearchProduct = searchProduct;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mSearchProduct.size();
	}

	@Override
	public Product getItem(int position) {
		// TODO Auto-generated method stub
		return mSearchProduct.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public void notifyDataSetChanged() {
		// TODO Auto-generated method stub
		super.notifyDataSetChanged();
	}

	@Override
	public Filter getFilter() {
		// TODO Auto-generated method stub
		Filter filter = new Filter() {

			/** �Ű������� ���� constraint ���ڿ��� �� ��ǰ�̸� �˻� �� 
			 * �ش�Ǵ� ��ü ��ȯ�ϴ� �޼ҵ�**/
			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				// TODO Auto-generated method stub
				FilterResults returnObject = new FilterResults();
				ArrayList<Product> result = new ArrayList<Product>();
				if(mOriginal == null) {
					mOriginal = mSearchProduct;
				}
				if(constraint != null) {
					for(int i=0 ; i<mOriginal.size() ; i++) {
						if(mOriginal.get(i).getName().contains(constraint.toString())) {
							result.add(mOriginal.get(i));
						}
					}
				} 
				
				returnObject.count = result.size();
				returnObject.values = result;
				
				return returnObject;
			}

			/** performFiltering�޼ҵ忡�� ��ȯ�� results�� �̿��Ͽ� 
			 * SearchFragment�� ��ȯ�� ��ü ���� **/
			@Override
			protected void publishResults(CharSequence constraint,
					FilterResults results) {
				// TODO Auto-generated method stub
				mSearchProduct = (ArrayList<Product>)results.values;
				notifyDataSetChanged();
			}
			
		};
		return filter;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder mViewHolder;
		if(convertView == null) {
			mViewHolder = new ViewHolder();
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.item_searchlist, parent, false);
			
			mViewHolder.name = (TextView) convertView.findViewById(R.id.tv_searchname);
			mViewHolder.score = (RatingBar) convertView.findViewById(R.id.rtb_score);
			
			convertView.setTag(mViewHolder);
		} else {
			mViewHolder = (ViewHolder) convertView.getTag();
		}
		
		/**������ ���� �ؾ��ϴ� �κ�**/
		// name�� �� �����۵� �̸� ����
		mViewHolder.name.setText(mSearchProduct.get(position).getName());
		// �� ���� ratingbar�� �� ������ �ο�.
		mViewHolder.score.setNumStars(5);
		mViewHolder.score.setRating(Float.parseFloat(mSearchProduct.get(position).getScore()));
		mViewHolder.score.setStepSize((float)0.5);
		mViewHolder.score.setIsIndicator(true);
		
		return convertView;
	}
	
	static class ViewHolder {
		TextView name;
		RatingBar score;
	}

}
