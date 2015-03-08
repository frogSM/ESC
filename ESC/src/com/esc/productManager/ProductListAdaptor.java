package com.esc.productManager;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.esc.R;

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
		return this.products.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	 	
    	ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
     
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.fragment_productmanager, null);
     
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.price = (TextView) convertView.findViewById(R.id.price);
            holder.type = (TextView) convertView.findViewById(R.id.type);
            holder.positionX = (TextView) convertView.findViewById(R.id.positionX);
            holder.positionY = (TextView) convertView.findViewById(R.id.positionY);
     
            convertView.setTag(holder);
        } else{
        	holder = (ViewHolder) convertView.getTag();
        }
     
        Product product = this.products.get(position);
     
        holder.name.setText(product.getName());
        holder.price.setText(product.getPrice());
        holder.type.setText(product.getType());
        holder.positionX.setText(product.getX());
        holder.positionY.setText(product.getY());
		
		return convertView;
	}
	
	class ViewHolder {
		TextView name;
		TextView price;
		TextView type;
		TextView positionX;
		TextView positionY;
	};

}
