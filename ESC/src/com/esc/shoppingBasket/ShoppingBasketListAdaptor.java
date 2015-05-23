package com.esc.shoppingBasket;

import java.util.ArrayList;
import java.util.HashSet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.esc.R;
import com.esc.productManager.Product;

public class ShoppingBasketListAdaptor extends BaseAdapter {

	private Context mContext;
	private ArrayList<Product> mBasket;
	private BasketManager mBasketManager;
	
	public ShoppingBasketListAdaptor(Context context) {
		// TODO Auto-generated constructor stub
		mContext = context;
		mBasketManager = BasketManager.getInstance();
		mBasket = mBasketManager.getBasket();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mBasket.size();
	}

	@Override
	public Product getItem(int position) {
		// TODO Auto-generated method stub
		return mBasket.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	public void remove(Object product) {
		mBasket.remove(product);
		mBasketManager.removeBasketProduct((Product)product);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder mViewHolder;
		
		if(convertView == null) {
			mViewHolder = new ViewHolder();
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.item_shoppingbasket, parent, false);
			
			mViewHolder.image = (ImageView)convertView.findViewById(R.id.iv_basket_product_image);
			mViewHolder.name = (TextView)convertView.findViewById(R.id.tv_basket_product_name);
			mViewHolder.price = (TextView)convertView.findViewById(R.id.tv_basket_product_price);
			mViewHolder.manufacturer = (TextView)convertView.findViewById(R.id.tv_basket_product_manufacturer);
			mViewHolder.type = (TextView)convertView.findViewById(R.id.tv_basket_product_type);
			
			convertView.setTag(mViewHolder);
		} else {
			mViewHolder = (ViewHolder) convertView.getTag();
		}
		
		/** ¿€º∫ **/
		Product nowProduct = (Product) getItem(position);
		mViewHolder.image.setImageResource(mContext.getResources()
				.getIdentifier(nowProduct.getImgURL(), "drawable",
						mContext.getPackageName()));
		mViewHolder.name.setText(nowProduct.getName());
		mViewHolder.price.setText(nowProduct.getPriceNow());
		mViewHolder.manufacturer.setText(nowProduct.getManufacturer());
		mViewHolder.type.setText(nowProduct.getType());
		
		return convertView;
	}
	
	static class ViewHolder {
		ImageView image;
		TextView name;
		TextView price;
		TextView manufacturer;
		TextView type;
	}

}
