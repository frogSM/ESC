package com.esc.MainAdvertise;

import java.util.ArrayList;

import com.esc.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BestProductAdapter extends BaseAdapter  {
	
	private Context mContext;
	private ArrayList<AdProduct> mAdProduct;
	
	/** 생성자 **/
	public BestProductAdapter(Context context, int selectNumber) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
		mAdProduct = new ArrayList<AdProduct>();
		DataSetting(selectNumber);
	}
	
	/** 10대 연령별 인기 상품 데이터 미리 세팅 **/
	private void DataSetting(int selectNumber) {
		switch(selectNumber) {
		case 10 :
			mAdProduct.add(new AdProduct("temp", "10", "30000"));
			mAdProduct.add(new AdProduct("temp", "10", "30000"));
			mAdProduct.add(new AdProduct("temp", "10", "30000"));
			mAdProduct.add(new AdProduct("temp", "10", "30000"));
			mAdProduct.add(new AdProduct("temp", "10", "30000"));
			mAdProduct.add(new AdProduct("temp", "10", "30000"));
			mAdProduct.add(new AdProduct("temp", "10", "30000"));
			mAdProduct.add(new AdProduct("temp", "10", "30000"));
			mAdProduct.add(new AdProduct("temp", "10", "30000"));
			mAdProduct.add(new AdProduct("temp", "10", "30000"));
			mAdProduct.add(new AdProduct("temp", "10", "30000"));
			mAdProduct.add(new AdProduct("temp", "10", "30000"));
			mAdProduct.add(new AdProduct("temp", "10", "30000"));
			mAdProduct.add(new AdProduct("temp", "10", "30000"));
			mAdProduct.add(new AdProduct("temp", "10", "30000"));
			mAdProduct.add(new AdProduct("temp", "10", "30000"));
			mAdProduct.add(new AdProduct("temp", "10", "30000"));
			mAdProduct.add(new AdProduct("temp", "10", "30000"));
			mAdProduct.add(new AdProduct("temp", "10", "30000"));
			mAdProduct.add(new AdProduct("temp", "10", "30000"));
			mAdProduct.add(new AdProduct("temp", "10", "30000"));
			mAdProduct.add(new AdProduct("temp", "10", "30000"));
			mAdProduct.add(new AdProduct("temp", "10", "30000"));
			mAdProduct.add(new AdProduct("temp", "10", "30000"));
			break;
		case 20 :
			mAdProduct.add(new AdProduct("temp", "20", "30005"));
			mAdProduct.add(new AdProduct("temp", "20", "30005"));
			mAdProduct.add(new AdProduct("temp", "20", "30005"));
			mAdProduct.add(new AdProduct("temp", "20", "30005"));
			mAdProduct.add(new AdProduct("temp", "20", "30005"));
			mAdProduct.add(new AdProduct("temp", "20", "30005"));
			mAdProduct.add(new AdProduct("temp", "20", "30005"));
			mAdProduct.add(new AdProduct("temp", "20", "30005"));
			mAdProduct.add(new AdProduct("temp", "20", "30005"));
			mAdProduct.add(new AdProduct("temp", "20", "30005"));
			mAdProduct.add(new AdProduct("temp", "20", "30005"));
			mAdProduct.add(new AdProduct("temp", "20", "30005"));
			mAdProduct.add(new AdProduct("temp", "20", "30005"));
			mAdProduct.add(new AdProduct("temp", "20", "30005"));
			mAdProduct.add(new AdProduct("temp", "20", "30005"));
			mAdProduct.add(new AdProduct("temp", "20", "30005"));
			mAdProduct.add(new AdProduct("temp", "20", "30005"));
			mAdProduct.add(new AdProduct("temp", "20", "30005"));
			mAdProduct.add(new AdProduct("temp", "20", "30005"));
			mAdProduct.add(new AdProduct("temp", "20", "30005"));
			mAdProduct.add(new AdProduct("temp", "20", "30005"));
			mAdProduct.add(new AdProduct("temp", "20", "30005"));
			mAdProduct.add(new AdProduct("temp", "20", "30005"));
			break;
		case 30 :
			mAdProduct.add(new AdProduct("temp", "30", "30005"));
			mAdProduct.add(new AdProduct("temp", "30", "30005"));
			mAdProduct.add(new AdProduct("temp", "30", "30005"));
			mAdProduct.add(new AdProduct("temp", "30", "30005"));
			mAdProduct.add(new AdProduct("temp", "30", "30005"));
			mAdProduct.add(new AdProduct("temp", "30", "30005"));
			mAdProduct.add(new AdProduct("temp", "30", "30005"));
			mAdProduct.add(new AdProduct("temp", "30", "30005"));
			mAdProduct.add(new AdProduct("temp", "30", "30005"));
			mAdProduct.add(new AdProduct("temp", "30", "30005"));
			mAdProduct.add(new AdProduct("temp", "30", "30005"));
			mAdProduct.add(new AdProduct("temp", "30", "30005"));
			mAdProduct.add(new AdProduct("temp", "30", "30005"));
			mAdProduct.add(new AdProduct("temp", "30", "30005"));
			mAdProduct.add(new AdProduct("temp", "30", "30005"));
			mAdProduct.add(new AdProduct("temp", "30", "30005"));
			mAdProduct.add(new AdProduct("temp", "30", "30005"));
			mAdProduct.add(new AdProduct("temp", "30", "30005"));
			mAdProduct.add(new AdProduct("temp", "30", "30005"));
			mAdProduct.add(new AdProduct("temp", "30", "30005"));
			mAdProduct.add(new AdProduct("temp", "30", "30005"));
			mAdProduct.add(new AdProduct("temp", "30", "30005"));
			mAdProduct.add(new AdProduct("temp", "30", "30005"));
			mAdProduct.add(new AdProduct("temp", "30", "30005"));
			mAdProduct.add(new AdProduct("temp", "30", "30005"));
			mAdProduct.add(new AdProduct("temp", "30", "30005"));
			mAdProduct.add(new AdProduct("temp", "30", "30005"));
			break;
		case 40 :
			mAdProduct.add(new AdProduct("temp", "40", "30005"));
			mAdProduct.add(new AdProduct("temp", "40", "30005"));
			mAdProduct.add(new AdProduct("temp", "40", "30005"));
			mAdProduct.add(new AdProduct("temp", "40", "30005"));
			mAdProduct.add(new AdProduct("temp", "40", "30005"));
			mAdProduct.add(new AdProduct("temp", "40", "30005"));
			mAdProduct.add(new AdProduct("temp", "40", "30005"));
			mAdProduct.add(new AdProduct("temp", "40", "30005"));
			mAdProduct.add(new AdProduct("temp", "40", "30005"));
			mAdProduct.add(new AdProduct("temp", "40", "30005"));
			mAdProduct.add(new AdProduct("temp", "40", "30005"));
			mAdProduct.add(new AdProduct("temp", "40", "30005"));
			mAdProduct.add(new AdProduct("temp", "40", "30005"));
			mAdProduct.add(new AdProduct("temp", "40", "30005"));
			mAdProduct.add(new AdProduct("temp", "40", "30005"));
			mAdProduct.add(new AdProduct("temp", "40", "30005"));
			mAdProduct.add(new AdProduct("temp", "40", "30005"));
			mAdProduct.add(new AdProduct("temp", "40", "30005"));
			mAdProduct.add(new AdProduct("temp", "40", "30005"));
			mAdProduct.add(new AdProduct("temp", "40", "30005"));
			mAdProduct.add(new AdProduct("temp", "40", "30005"));
			mAdProduct.add(new AdProduct("temp", "40", "30005"));
			mAdProduct.add(new AdProduct("temp", "40", "30005"));
			mAdProduct.add(new AdProduct("temp", "40", "30005"));
			mAdProduct.add(new AdProduct("temp", "40", "30005"));
			mAdProduct.add(new AdProduct("temp", "40", "30005"));
			mAdProduct.add(new AdProduct("temp", "40", "30005"));
			mAdProduct.add(new AdProduct("temp", "40", "30005"));
			break;
		case 50 :
			mAdProduct.add(new AdProduct("temp", "50", "30005"));
			mAdProduct.add(new AdProduct("temp", "50", "30005"));
			mAdProduct.add(new AdProduct("temp", "50", "30005"));
			mAdProduct.add(new AdProduct("temp", "50", "30005"));
			mAdProduct.add(new AdProduct("temp", "50", "30005"));
			mAdProduct.add(new AdProduct("temp", "50", "30005"));
			mAdProduct.add(new AdProduct("temp", "50", "30005"));
			mAdProduct.add(new AdProduct("temp", "50", "30005"));
			mAdProduct.add(new AdProduct("temp", "50", "30005"));
			mAdProduct.add(new AdProduct("temp", "50", "30005"));
			mAdProduct.add(new AdProduct("temp", "50", "30005"));
			mAdProduct.add(new AdProduct("temp", "50", "30005"));
			mAdProduct.add(new AdProduct("temp", "50", "30005"));
			mAdProduct.add(new AdProduct("temp", "50", "30005"));
			mAdProduct.add(new AdProduct("temp", "50", "30005"));
			mAdProduct.add(new AdProduct("temp", "50", "30005"));
			mAdProduct.add(new AdProduct("temp", "50", "30005"));
			mAdProduct.add(new AdProduct("temp", "50", "30005"));
			mAdProduct.add(new AdProduct("temp", "50", "30005"));
			mAdProduct.add(new AdProduct("temp", "50", "30005"));
			mAdProduct.add(new AdProduct("temp", "50", "30005"));
			mAdProduct.add(new AdProduct("temp", "50", "30005"));
			mAdProduct.add(new AdProduct("temp", "50", "30005"));
			mAdProduct.add(new AdProduct("temp", "50", "30005"));
			break;
		}
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mAdProduct.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mAdProduct.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder mViewHolder;
		
		if(convertView == null) {
			mViewHolder = new ViewHolder();
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.item_adproduct, parent, false);
			
			mViewHolder.image = (ImageView)convertView.findViewById(R.id.iv_ad_image);
			mViewHolder.title = (TextView)convertView.findViewById(R.id.tv_ad_title);
			mViewHolder.price = (TextView)convertView.findViewById(R.id.tv_ad_price);
			
			convertView.setTag(mViewHolder);
			
		} else {
			mViewHolder = (ViewHolder)convertView.getTag();
		}
		
//			mViewHolder.image.setImageResource(mContext.getResources()
//					.getIdentifier(mAdProduct.get(position).getImgURL(), "drawable",
//							mContext.getPackageName()));
			mViewHolder.title.setText(mAdProduct.get(position).getTitle());
			mViewHolder.price.setText(mAdProduct.get(position).getPrice());
		
		return convertView;
	}
	
	static class ViewHolder {
		ImageView image;
		TextView title;
		TextView price;
	}

}
