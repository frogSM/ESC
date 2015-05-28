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
	
	/** ������ **/
	public BestProductAdapter(Context context, int selectNumber) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
		mAdProduct = new ArrayList<AdProduct>();
		DataSetting(selectNumber);
	}
	
	/** 10�� ���ɺ� �α� ��ǰ ������ �̸� ���� **/
	private void DataSetting(int selectNumber) {
		switch(selectNumber) {
		case 10 :
			mAdProduct.add(new AdProduct("@drawable/img_10best_product1", "��� USB �̴ϰ����� LMUH-15C", "19,800"));
			mAdProduct.add(new AdProduct("@drawable/img_10best_product2", "�÷�������Ʈ �ְ������ٳ�Ʈ", "2,700"));
			mAdProduct.add(new AdProduct("@drawable/img_10best_product3", "[��ױ۷θ�] �÷�Ȧ�� SP ��������Ʈ(40�� X 5��)", "5,600"));
			mAdProduct.add(new AdProduct("@drawable/img_10best_product4", "[��ױ۷θ�] �̴� ���� ���ʱ���", "6,400"));
			mAdProduct.add(new AdProduct("@drawable/img_10best_product5", "7500 ���� �����븶�� ��������", "6,000"));
			break;
		case 20 :
			mAdProduct.add(new AdProduct("@drawable/img_20best_product1", "[���ǹٽ�] ������ �ٵ��ũ 450ml", "12,200"));
			mAdProduct.add(new AdProduct("@drawable/img_20best_product2", "[��Ʈ������] ��Ŭ�� ��������¡ ��Ŭ���� 100g", "8,160"));
			mAdProduct.add(new AdProduct("@drawable/img_20best_product3", "�Ұ��� ��� �ȹ� 50ml", "48,000"));
			mAdProduct.add(new AdProduct("@drawable/img_20best_product4", "�����������ű�", "3,900"));
			mAdProduct.add(new AdProduct("@drawable/img_20best_product5", "Ķ��Ŭ���� CK one ���̽��� ������ �μ�", "27,000"));
			break;
		case 30 :
			mAdProduct.add(new AdProduct("@drawable/img_30best_product1", "[LG] G�е� 8.3 16G(ȭ��Ʈ)", "361,050"));
			mAdProduct.add(new AdProduct("@drawable/img_30best_product2", "[LG] 15U340-LT30K", "610,530"));
			mAdProduct.add(new AdProduct("@drawable/img_30best_product3", "[ĳ��CANON] EOS700D 18-55 IS STM KIT", "748,000"));
			mAdProduct.add(new AdProduct("@drawable/img_30best_product4", "[��޻��] VS5543PIK - 2000W �丣���� �̿� ������̱�", "52,800"));
			mAdProduct.add(new AdProduct("@drawable/img_30best_product5", "�ʸ��� �鵵�� RQ1167/17", "149,000"));
			break;
		case 40 :
			mAdProduct.add(new AdProduct("@drawable/img_40best_product1", "���� ��Ż������ƮKI140D 1.7L", "62,900"));
			mAdProduct.add(new AdProduct("@drawable/img_40best_product2", "����Ȩ �̴Ϲͼ�SFM-E200", "21,900"));
			mAdProduct.add(new AdProduct("@drawable/img_40best_product3", "���� �ƹݼ� ������ SCM-900DT", "54,800"));
			mAdProduct.add(new AdProduct("@drawable/img_40best_product4", "[������] ȫ���� �÷��� 120g", "102,000"));
			mAdProduct.add(new AdProduct("@drawable/img_40best_product5", "[�ҽ���] ���� ���ž� 1.8L", "3,290"));
			break;
		case 50 :
			mAdProduct.add(new AdProduct("@drawable/img_50best_product1", "����� LŸ�� �ȸ�����", "2,980,000"));
			mAdProduct.add(new AdProduct("@drawable/img_50best_product2", "���Ƿ� �ڵ��������а� HEM-7111", "55,000"));
			mAdProduct.add(new AdProduct("@drawable/img_50best_product3", "[KOLPING] ���� 14SS ���� Ŭ���̹��� ������(��)", "39,000"));
			mAdProduct.add(new AdProduct("@drawable/img_50best_product4", "[�޸�Ʈ] ������ ��(����/������)", "19,000"));
			mAdProduct.add(new AdProduct("@drawable/img_50best_product5", "���� ���䰡830 �����϶���", "10,900"));
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
			
			mViewHolder.image = (ImageView)convertView.findViewById(R.id.iv_ad_image99);
			mViewHolder.title = (TextView)convertView.findViewById(R.id.tv_ad_title1);
			mViewHolder.price = (TextView)convertView.findViewById(R.id.tv_cancel_line1);
			
			convertView.setTag(mViewHolder);
			
		} else {
			mViewHolder = (ViewHolder)convertView.getTag();
		}
		
			mViewHolder.image.setImageResource(mContext.getResources()
					.getIdentifier(mAdProduct.get(position).getImgURL(), "drawable",
							mContext.getPackageName()));
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
