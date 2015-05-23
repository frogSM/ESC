package com.esc.MainAdvertise;

import java.util.ArrayList;

import com.esc.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BestProductAdapter_Ten extends BaseAdapter  {
	
	/** ESC몰 Front에 보여주기 위한 광고상품 객체(Product 객체 이용 X)**/
	static class AdProduct {
		String imgURL;
		String title;
		String price;
		
		public AdProduct(String imgURL, String title, String price) {
			// TODO Auto-generated constructor stub
			this.imgURL = imgURL;
			this.title = title;
			this.price = price;
		}
		
		public String getImgURL() { return this.imgURL; }
		public String getTitle() { return this.title; }
		public String getPrice() { return this.price; }
		
	}

	private Context mContext;
	private ArrayList<AdProduct> mAdProduct;
	
	/** 생성자 **/
	public BestProductAdapter_Ten(Context context) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
		mAdProduct = new ArrayList<BestProductAdapter_Ten.AdProduct>();
		DataSetting();
	}
	
	/** 10대 연령별 인기 상품 데이터 미리 세팅 **/
	private void DataSetting() {
		mAdProduct.add(new AdProduct("temp", "라면", "30000"));
		mAdProduct.add(new AdProduct("temp", "라면2", "30005"));
		mAdProduct.add(new AdProduct("temp", "라면2", "30005"));
		mAdProduct.add(new AdProduct("temp", "라면2", "30005"));
		mAdProduct.add(new AdProduct("temp", "라면2", "30005"));
		mAdProduct.add(new AdProduct("temp", "라면2", "30005"));
		mAdProduct.add(new AdProduct("temp", "라면2", "30005"));
		mAdProduct.add(new AdProduct("temp", "라면2", "30005"));
		mAdProduct.add(new AdProduct("temp", "라면2", "30005"));
		mAdProduct.add(new AdProduct("temp", "라면2", "30005"));
		mAdProduct.add(new AdProduct("temp", "라면2", "30005"));
		mAdProduct.add(new AdProduct("temp", "라면2", "30005"));
		mAdProduct.add(new AdProduct("temp", "라면2", "30005"));
		mAdProduct.add(new AdProduct("temp", "라면2", "30005"));
		mAdProduct.add(new AdProduct("temp", "라면2", "30005"));
		mAdProduct.add(new AdProduct("temp", "라면2", "30005"));
		mAdProduct.add(new AdProduct("temp", "라면2", "30005"));
		mAdProduct.add(new AdProduct("temp", "라면2", "30005"));
		mAdProduct.add(new AdProduct("temp", "라면2", "30005"));
		mAdProduct.add(new AdProduct("temp", "라면2", "30005"));
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
