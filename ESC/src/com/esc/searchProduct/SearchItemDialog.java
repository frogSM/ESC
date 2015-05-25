package com.esc.searchProduct;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.RangeCategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.esc.Constants;
import com.esc.R;
import com.esc.Connection.JsonHelper;
import com.esc.Connection.SocketHelper;
import com.esc.printLocation.MapDialog;
import com.esc.productManager.Product;
import com.esc.shoppingBasket.BasketManager;
import com.perples.recosdk.v;


public class SearchItemDialog extends Dialog implements OnClickListener{

	/** ���̾�α� ���̾ƿ� ���� **/
	private View mView;
	private Context mContext;
	
	private ViewFlipper mViewFlipper;
	private Button mButtonLeft;
	private Button mButtonRight;
	
	private LayoutInflater mLayoutInflater;
	
	private ProgressDialog mProgressDialog;
	
	/** ��ٱ��� ��ü **/
	private BasketManager mBasketManager;
	
	/** SearchFragment�κ��� ���޹��� ������ **/
	private ArrayList<Product> receiveData;
	
	/** SearchFragment���� ���õ� Position
	 *  ���̾�α׿��� ������� �����ֱ� ���� ���� **/
	private int selectNum;
	
	/** �ִϸ��̼��� ���Ѻ���(ó�� ��ġ��ġ ����) **/
	private int m_nPreTouchPosX = 0;
	
	/** ��õ���� DB������ **/
	private ArrayList<RecommendProduct> mRecommendProduct;
	
	/** ���� �� Json �����**/
	private SocketHelper mSocketHelper;
	private JsonHelper mJsonHelper;
	
	private LinearLayout ll;
	
	/** �߰� ��ų ��ǰ ��õ ������ ���� **/
	//
	//
	//
	//
	//
	
	
	public SearchItemDialog(Context context, ArrayList<Product> data, int position) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
		receiveData = data;
		selectNum = position;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mBasketManager = BasketManager.getInstance();
		
		mSocketHelper = SocketHelper.getInstance(mContext);
		mJsonHelper = JsonHelper.getInstance(mContext);
		
		mRecommendProduct = new ArrayList<RecommendProduct>();
		
		dialogSetting();
		addDynamicViewFlipper();
		
	}
	
	/** Socket���� �޾ƿ� JSON���� ������ �Ľ��Ͽ� ��ü�� �����ϴ� �ڵ鷯**/
	private Handler mHandler = new Handler() {
		
		@Override
		public void handleMessage(android.os.Message msg)
				throws NullPointerException {
			if (msg.what == Constants.THREAD_MESSAGE) {
				mRecommendProduct = (ArrayList<RecommendProduct>) mJsonHelper.parserJsonMessage(msg.obj.toString());
				
				TextView reco1_title = (TextView)ll.findViewById(R.id.tv_ad_title1);
				TextView reco1_price = (TextView)ll.findViewById(R.id.tv_ad_price1);
				TextView reco2_title = (TextView)ll.findViewById(R.id.tv_ad_title2);
				TextView reco2_price = (TextView)ll.findViewById(R.id.tv_ad_price2);
				TextView reco3_title = (TextView)ll.findViewById(R.id.tv_ad_title3);
				TextView reco3_price = (TextView)ll.findViewById(R.id.tv_ad_price3);
				TextView reco4_title = (TextView)ll.findViewById(R.id.tv_ad_title4);
				TextView reco4_price = (TextView)ll.findViewById(R.id.tv_ad_price4);
				TextView reco5_title = (TextView)ll.findViewById(R.id.tv_ad_title5);
				TextView reco5_price = (TextView)ll.findViewById(R.id.tv_ad_price5);
				
				for(int i=0 ; i<mRecommendProduct.size() ; i++) {
					switch(i) {
					case 0 :
						reco1_title.setText(mRecommendProduct.get(0).name);
						reco1_price.setText(mRecommendProduct.get(0).price);
						break;
					case 1 :
						reco2_title.setText(mRecommendProduct.get(1).name);
						reco2_price.setText(mRecommendProduct.get(1).price);
						break;
					case 2 :
						reco3_title.setText(mRecommendProduct.get(2).name);
						reco3_price.setText(mRecommendProduct.get(2).price);
						break;
					case 3 :
						reco4_title.setText(mRecommendProduct.get(3).name);
						reco4_price.setText(mRecommendProduct.get(3).price);
						break;
					case 4 :
						reco5_title.setText(mRecommendProduct.get(4).name);
						reco5_price.setText(mRecommendProduct.get(4).price);
						break;
					}
				}
				
			}
		};
	};
	
	private void dialogSetting() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		
		mView = getLayoutInflater().inflate(R.layout.dialog_searchviewflipper, null);
		setContentView(mView);
		
		mViewFlipper = (ViewFlipper) findViewById(R.id.main_viewflipper);
		mButtonLeft = (Button) findViewById(R.id.btn_left);
		mButtonRight = (Button) findViewById(R.id.btn_right);
		
		mButtonLeft.setOnClickListener(this);
		mButtonRight.setOnClickListener(this);
		
		mLayoutInflater = (LayoutInflater)mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
	}
	
	private GraphicalView getGraph(String priceBeforeOne, String priceBeforeTwo, String priceBeforeThree, 
			String priceBeforeFour, String priceBeforeFive, String priceBeforeSix) {
		List<double[]> values = new ArrayList<double[]>();
		values.add(new double[] {
				Double.valueOf(priceBeforeOne), Double.valueOf(priceBeforeTwo),
				Double.valueOf(priceBeforeThree), Double.valueOf(priceBeforeFour),
				Double.valueOf(priceBeforeFive), Double.valueOf(priceBeforeSix)
		});

		/** �׷��� ����� ���� �׷��� �Ӽ� ������ü */
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();

		// ��� ǥ�� ����� ���� ũ��
		renderer.setChartTitle("�ֱ� 6�������� ��ǰ����");
		renderer.setChartTitleTextSize(40);

		// �з��� ���� �̸�
		String[] titles = new String[] { "���� �� ����" };

		// �׸��� ǥ���ϴµ� ���� ����
		int[] colors = new int[] { Color.RED };

		// �з��� ���� ũ�� �� �� ���� ����
		renderer.setLegendTextSize(20);
		int length = colors.length;
		for (int i = 0; i < length; i++) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(colors[i]);
			r.setDisplayChartValues(true);
			r.setChartValuesTextSize(20);
			renderer.addSeriesRenderer(r);
		}

		// X,Y�� �׸��̸��� ���� ũ��
		renderer.setXTitle("���� ��");
		renderer.setYTitle("��ǰ����(��)");
		renderer.setAxisTitleTextSize(30);

		// ��ġ�� ���� ũ�� / X�� �ּ�,�ִ밪 / Y�� �ּ�,�ִ밪
		renderer.setLabelsTextSize(20);
		renderer.setXAxisMin(0.5);
		renderer.setXAxisMax(6.5);
		renderer.setYAxisMin(0);
		renderer.setYAxisMax(10000);

		// X,Y�� ���� ����
		renderer.setAxesColor(Color.BLACK);
		// �������, X,Y�� ����, ��ġ���� ���� ����
		renderer.setLabelsColor(Color.RED);

		// X���� ǥ�� ����
		renderer.setXLabels(6);
		// Y���� ǥ�� ����
		renderer.setYLabels(0);

		// X,Y�� ���Ĺ���
		renderer.setXLabelsAlign(Align.LEFT);
		renderer.setYLabelsAlign(Align.LEFT);
		// X,Y�� ��ũ�� ���� ON/OFF
		renderer.setPanEnabled(false, false);
		// ZOOM��� ON/OFF
		renderer.setZoomEnabled(false, false);
		// ZOOM ����
		renderer.setZoomRate(1.0f);
		// ���밣 ����
		renderer.setBarSpacing(0.5f);
		
		// �׸��� ���� ���� �� ���� �÷� ����
		renderer.setGridColor(Color.parseColor("#c9c9c9"));
        renderer.setMarginsColor(Color.parseColor("#FFFFFF"));

		// ���� ���� ����
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		for (int i = 0; i < titles.length; i++) {
			CategorySeries series = new CategorySeries(titles[i]);
			double[] v = values.get(i);
			int seriesLength = v.length;
			for (int k = 0; k < seriesLength; k++) {
				series.add(v[k]);
			}
			dataset.addSeries(series.toXYSeries());
		}

		// �׷��� ��ü ����
		GraphicalView gv = ChartFactory.getBarChartView(mContext, dataset,
				renderer, Type.STACKED);
		
		return gv;
	}
	
	private void addDynamicViewFlipper() {
		for(int i=0 ; i<receiveData.size() ; i++) {
			ll = (LinearLayout)mLayoutInflater.inflate(R.layout.item_productinfo, null);
			TextView mTextViewName= (TextView)ll.findViewById(R.id.tv_name);
			TextView mTextViewPrice= (TextView)ll.findViewById(R.id.tv_price);
			TextView mTextViewType = (TextView)ll.findViewById(R.id.tv_type);
			TextView mTextViewManufacturer = (TextView)ll.findViewById(R.id.tv_manufacturer);
			TextView mTextViewDescription = (TextView)ll.findViewById(R.id.tv_description);
			ImageView mImageView = (ImageView)ll.findViewById(R.id.iv_productpicture);
			Button mButtonBasketAdd = (Button)ll.findViewById(R.id.btn_basket_add);
			Button mButtonPositionConfirm = (Button)ll.findViewById(R.id.btn_position_confirm);
			LinearLayout mGraph = (LinearLayout)ll.findViewById(R.id.ll_graph);
			
			mTextViewName.setText(receiveData.get(i).getName());
			mTextViewPrice.setText(String.valueOf(receiveData.get(i).getPriceNow()));
			mTextViewType.setText(receiveData.get(i).getType());
			mTextViewManufacturer.setText(receiveData.get(i).getManufacturer());
			mTextViewDescription.setText(receiveData.get(i).getDescription());
			
			mGraph.addView(getGraph(receiveData.get(i).getPriceBeforeOne(), receiveData.get(i).getPriceBeforeTwo(),
					receiveData.get(i).getPriceBeforeThree(), receiveData.get(i).getPriceBeforeFour(),
					receiveData.get(i).getPriceBeforeFive(), receiveData.get(i).getPriceBeforeSix()));
			
			int id = mContext.getResources().getIdentifier(receiveData.get(i).getImgURL(), "drawable", mContext.getPackageName());
			mImageView.setImageResource(id);
			
			/** ��ũ�� ���� �� �������� ������ �޾ƿ��� �κ� **/
			ScrollView scrollView = (ScrollView)ll.findViewById(R.id.scv_contents);
			
			
			/** �� ��ǰ�� ������ ��ǰ �� ��ư ���� **/
			ViewGroup recomandation1 = (ViewGroup)ll.findViewById(R.id.ll_recomandation1);
			ViewGroup recomandation2 = (ViewGroup)ll.findViewById(R.id.ll_recomandation2);
			ViewGroup recomandation3 = (ViewGroup)ll.findViewById(R.id.ll_recomandation3);
			ViewGroup recomandation4 = (ViewGroup)ll.findViewById(R.id.ll_recomandation4);
			ViewGroup recomandation5 = (ViewGroup)ll.findViewById(R.id.ll_recomandation5);
			
			recomandation1.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					SearchItemDialog dialog = new SearchItemDialog(mContext, receiveData, 0);
					dialog.show();
				}
			});
			
			/** [��ٱ��� �߰�] [��ġ���� Ȯ��] ��ư OnClickListener�������̽� �߰� �κ�**/
			final int loopValue = i;
			mButtonBasketAdd.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					mBasketManager.addBasketProduct(receiveData.get(loopValue));
					Toast.makeText(getContext(), "��ٱ��Ͽ� �߰��Ǿ����ϴ�.", Toast.LENGTH_SHORT).show();
				}
			});
			
			final String type = receiveData.get(i).getType();
			mButtonPositionConfirm.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					MapDialog map = new MapDialog(mContext, type);
					map.show();
				}
			});
			
			String str_json = mJsonHelper.makeJsonMessage(Constants.RecommendedProduct_Info2, receiveData.get(0).getNumber());
			mSocketHelper.sendMessage(mHandler, str_json);
			
			mViewFlipper.addView(ll);
		}
		
		mViewFlipper.setDisplayedChild(selectNum);
		mViewFlipper.setOnTouchListener(MyTouchListener);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.btn_left :
			MovewPreviousView();
			break;
		case R.id.btn_right :
			MoveNextView();
			break;
		}
	}

	private void MoveNextView() {
		mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext,
				R.anim.appear_from_right));
		mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext,
				R.anim.disappear_to_left));
		mViewFlipper.showNext();
	}

	private void MovewPreviousView() {
		mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext,
				R.anim.appear_from_left));
		mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext,
				R.anim.disappear_to_right));
		mViewFlipper.showPrevious();
	}

	View.OnTouchListener MyTouchListener = new View.OnTouchListener() {
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				m_nPreTouchPosX = (int) event.getX();
			}

			if (event.getAction() == MotionEvent.ACTION_UP) {
				int nTouchPosX = (int) event.getX();

				if (nTouchPosX < m_nPreTouchPosX) {
					MoveNextView();
				} else if (nTouchPosX > m_nPreTouchPosX) {
					MovewPreviousView();
				}

				m_nPreTouchPosX = nTouchPosX;
			}

			return true;
		}
	};

	
}
