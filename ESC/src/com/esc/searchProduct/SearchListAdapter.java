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
	
	/** 오리지날 전체DB 상품 **/
	private ArrayList<Product> mOriginal;
	/** 상황에 맞게 계속 변하는 필터링 된 상품 **/
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

			/** 매개변수로 들어온 constraint 문자열이 들어간 상품이름 검색 후 
			 * 해당되는 객체 반환하는 메소드**/
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

			/** performFiltering메소드에서 반환된 results를 이용하여 
			 * SearchFragment로 반환할 객체 설정 **/
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
		
		/**데이터 조작 해야하는 부분**/
		// name에 각 아이템들 이름 설정
		mViewHolder.name.setText(mSearchProduct.get(position).getName());
		// 그 다음 ratingbar에 각 평점들 부여.
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
