package com.esc.productManager;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.esc.R;

public class ProductListAdaptor extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mLayoutInflater;
	private ArrayList<Product> products;
	private ArrayList<CustomerCart> productsInCart;
	private ViewHolder viewHolder;
	
	public ProductListAdaptor(Context context, ArrayList<Product> products , ArrayList<CustomerCart> productsInCart ) {
		// TODO Auto-generated constructor stub
		mContext = context;
		mLayoutInflater = LayoutInflater.from(context);
		this.products = products;
		this.productsInCart = productsInCart;
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

	public void updateProducts(ArrayList<Product> newProducts) {
		this.products = newProducts;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	 	
    	ViewHolder holder;
        if (convertView == null) {
        	holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_productlist,parent, false);
     
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.price = (TextView) convertView.findViewById(R.id.price);
            holder.type = (TextView) convertView.findViewById(R.id.type);
            holder.description = (TextView)  convertView.findViewById(R.id.description);
            holder.manufacturer = (TextView)  convertView.findViewById(R.id.manufacturer);
            holder.count = (TextView) convertView.findViewById(R.id.count);
     
            convertView.setTag(holder);
        } else{
        	holder = (ViewHolder) convertView.getTag();
        }	
        int imageURL = mContext.getResources().getIdentifier(this.products.get(position).getImgURL(), "drawable", mContext.getPackageName());
		holder.image.setImageResource(imageURL);
		holder.image.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.name.setText(this.products.get(position).getName());
        holder.price.setText(this.products.get(position).getPriceNow() + "��");
        holder.type.setText(this.products.get(position).getType());
        holder.description.setText(this.products.get(position).getDescription());
        holder.manufacturer.setText(this.products.get(position).getManufacturer() );
    //    holder.count.setText(this.productsInCart.get(position).getCount() + "��");
        holder.count.setText("1��");
		
		return convertView;
	}
	
	static class ViewHolder {
		ImageView image;
		TextView name;
		TextView price;
		TextView type;
		TextView description;
		TextView manufacturer;
		TextView count;
	};

}
