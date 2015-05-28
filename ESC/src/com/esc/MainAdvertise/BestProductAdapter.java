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
			mAdProduct.add(new AdProduct("@drawable/img_10best_product1", "라미 USB 미니가습기 LMUH-15C", "19,800"));
			mAdProduct.add(new AdProduct("@drawable/img_10best_product2", "플러스메이트 주간스케줄노트", "2,700"));
			mAdProduct.add(new AdProduct("@drawable/img_10best_product3", "[모닝글로리] 컬러홀릭 SP 스프링노트(40매 X 5권)", "5,600"));
			mAdProduct.add(new AdProduct("@drawable/img_10best_product4", "[모닝글로리] 미니 돼지 연필깎이", "6,400"));
			mAdProduct.add(new AdProduct("@drawable/img_10best_product5", "7500 심플 정리대마왕 봉제필통", "6,000"));
			break;
		case 20 :
			mAdProduct.add(new AdProduct("@drawable/img_20best_product1", "[해피바스] 촉촉한 바디밀크 450ml", "12,200"));
			mAdProduct.add(new AdProduct("@drawable/img_20best_product2", "[뉴트로지나] 딥클린 에너자이징 폼클랜져 100g", "8,160"));
			mAdProduct.add(new AdProduct("@drawable/img_20best_product3", "불가리 블루 옴므 50ml", "48,000"));
			mAdProduct.add(new AdProduct("@drawable/img_20best_product4", "슬림각질제거기", "3,900"));
			mAdProduct.add(new AdProduct("@drawable/img_20best_product5", "캘빈클라인 CK one 모이스쳐 라이져 로션", "27,000"));
			break;
		case 30 :
			mAdProduct.add(new AdProduct("@drawable/img_30best_product1", "[LG] G패드 8.3 16G(화이트)", "361,050"));
			mAdProduct.add(new AdProduct("@drawable/img_30best_product2", "[LG] 15U340-LT30K", "610,530"));
			mAdProduct.add(new AdProduct("@drawable/img_30best_product3", "[캐논CANON] EOS700D 18-55 IS STM KIT", "748,000"));
			mAdProduct.add(new AdProduct("@drawable/img_30best_product4", "[비달사순] VS5543PIK - 2000W 토르말린 이온 헤어드라이기", "52,800"));
			mAdProduct.add(new AdProduct("@drawable/img_30best_product5", "필립스 면도기 RQ1167/17", "149,000"));
			break;
		case 40 :
			mAdProduct.add(new AdProduct("@drawable/img_40best_product1", "테팔 메탈전기포트KI140D 1.7L", "62,900"));
			mAdProduct.add(new AdProduct("@drawable/img_40best_product2", "러빙홈 미니믹서SFM-E200", "21,900"));
			mAdProduct.add(new AdProduct("@drawable/img_40best_product3", "신일 맥반석 찜질기 SCM-900DT", "54,800"));
			mAdProduct.add(new AdProduct("@drawable/img_40best_product4", "[정관장] 홍삼정 플러스 120g", "102,000"));
			mAdProduct.add(new AdProduct("@drawable/img_40best_product5", "[불스원] 코팅 워셔액 1.8L", "3,290"));
			break;
		case 50 :
			mAdProduct.add(new AdProduct("@drawable/img_50best_product1", "브람스 L타입 안마의자", "2,980,000"));
			mAdProduct.add(new AdProduct("@drawable/img_50best_product2", "오므론 자동전자혈압계 HEM-7111", "55,000"));
			mAdProduct.add(new AdProduct("@drawable/img_50best_product3", "[KOLPING] 콜핑 14SS 여름 클라이밍핏 등산바지(남)", "39,000"));
			mAdProduct.add(new AdProduct("@drawable/img_50best_product4", "[휴몬트] 오슬로 햇(여름/남성용)", "19,000"));
			mAdProduct.add(new AdProduct("@drawable/img_50best_product5", "낫소 알페가830 배드민턴라켓", "10,900"));
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
