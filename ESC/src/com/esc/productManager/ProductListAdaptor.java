package com.esc.productManager;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ProductListAdaptor extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mLayoutInflater;
	private ArrayList<Product> products;
	private ViewHolder viewHolder;
	
	public ProductListAdaptor(Context context, ArrayList<Product> products ) {
		// TODO Auto-generated constructor stub
		mContext = context;
		mLayoutInflater = LayoutInflater.from(context);
		this.products = products;
	}
	
	@Override
	public int getCount() {
		return this.products.size(); 
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		/*
		if(v == null) {
			v = mLayoutInflater.inflate(R.layout.activity_test_item, parent, false);
			
			viewHolder = new ViewHolder();
			viewHolder.tv_name = (TextView)v.findViewById(R.id.txtView_name);
			viewHolder.tv_price = (TextView)v.findViewById(R.id.txtView_price);
			viewHolder.tv_cnt = (TextView)v.findViewById(R.id.txtView_cnt);
			viewHolder.tv_sum = (TextView)v.findViewById(R.id.txtView_sum);
			
			v.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder)v.getTag();
		}
		
		//*/
		
		return null;
	}
	
	class ViewHolder {
		TextView tv_name;
		TextView tv_price;
		TextView tv_cnt;
		TextView tv_sum;
	};

}
