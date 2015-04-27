package com.esc.searchProduct;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.esc.R;
import com.esc.productManager.Product;
import com.esc.shoppingBasket.BasketManager;


public class SearchItemDialog extends Dialog implements OnClickListener{

	/** 다이얼로그 레이아웃 정보 **/
	private View mView;
	private Context mContext;
	
	private ViewFlipper mViewFlipper;
	private Button mButtonLeft;
	private Button mButtonRight;
	
	private LayoutInflater mLayoutInflater;
	
	/** 장바구니 객체 **/
	private BasketManager mBasketManager;
	
	/** SearchFragment로부터 전달받은 데이터 **/
	private ArrayList<Product> receiveData;
	
	/** SearchFragment에서 선택된 Position
	 *  다이얼로그에서 가장먼저 보여주기 위한 변수 **/
	private int selectNum;
	
	/** 애니메이션을 위한변수(처음 터치위치 저장) **/
	private int m_nPreTouchPosX = 0;
	
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
		
		dialogSetting();
		addDynamicViewFlipper();
	}
	
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
	
	private GraphicalView getGraph(String priceBeforeOne, String priceBeforeTwo, 
			String priceBeforeThree, String priceBeforeSix) {
		List<double[]> values = new ArrayList<double[]>();
		values.add(new double[] { 14230, 12300, 14240, 15244, 15900, 19200,
				22030, 21200, 19500, 15500, 12600, 14000 });

		/** 그래프 출력을 위한 그래픽 속성 지정객체 */
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();

		// 상단 표시 제목과 글자 크기
		renderer.setChartTitle("2011년도 판매량");
		renderer.setChartTitleTextSize(20);

		// 분류에 대한 이름
		String[] titles = new String[] { "월별 판매량" };

		// 항목을 표시하는데 사용될 색상값
		int[] colors = new int[] { Color.RED };

		// 분류명 글자 크기 및 각 색상 지정
		renderer.setLegendTextSize(15);
		int length = colors.length;
		for (int i = 0; i < length; i++) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(colors[i]);
			r.setDisplayChartValues(true);
			renderer.addSeriesRenderer(r);
		}

		// X,Y축 항목이름과 글자 크기
		renderer.setXTitle("월");
		renderer.setYTitle("판매량");
		renderer.setAxisTitleTextSize(12);

		// 수치값 글자 크기 / X축 최소,최대값 / Y축 최소,최대값
		renderer.setLabelsTextSize(10);
		renderer.setXAxisMin(0.5);
		renderer.setXAxisMax(12.5);
		renderer.setYAxisMin(0);
		renderer.setYAxisMax(24000);

		// X,Y축 라인 색상
		renderer.setAxesColor(Color.WHITE);
		// 상단제목, X,Y축 제목, 수치값의 글자 색상
		renderer.setLabelsColor(Color.RED);

		// X축의 표시 간격
		renderer.setXLabels(12);
		// Y축의 표시 간격
		renderer.setYLabels(5);

		// X,Y축 정렬방향
		renderer.setXLabelsAlign(Align.LEFT);
		renderer.setYLabelsAlign(Align.LEFT);
		// X,Y축 스크롤 여부 ON/OFF
		renderer.setPanEnabled(false, false);
		// ZOOM기능 ON/OFF
		renderer.setZoomEnabled(false, false);
		// ZOOM 비율
		renderer.setZoomRate(1.0f);
		// 막대간 간격
		renderer.setBarSpacing(0.5f);
		
		// 그리드 색상 변경 및 마진 컬러 변경
		renderer.setGridColor(Color.parseColor("#c9c9c9"));
        renderer.setMarginsColor(Color.parseColor("#FFFFFF"));

		// 설정 정보 설정
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
		
		// 그래프 객체 생성
		GraphicalView gv = ChartFactory.getBarChartView(mContext, dataset,
				renderer, Type.STACKED);
		
		return gv;

//		// 그래프를 LinearLayout에 추가
//		LinearLayout llBody = (LinearLayout)ll.findViewById(R.id.ll_graph);
//		llBody.addView(gv);
	}
	
	private void addDynamicViewFlipper() {
		for(int i=0 ; i<receiveData.size() ; i++) {
			LinearLayout ll = (LinearLayout)mLayoutInflater.inflate(R.layout.item_productinfo, null);
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
					receiveData.get(i).getPriceBeforeThree(), receiveData.get(i).getPriceBeforeSix()));
			
			int id = mContext.getResources().getIdentifier(receiveData.get(i).getImgURL(), "drawable", mContext.getPackageName());
			mImageView.setImageResource(id);
			
			final int loopValue = i;
			mButtonBasketAdd.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					mBasketManager.addBasketProduct(receiveData.get(loopValue));
					Toast.makeText(getContext(), "장바구니에 추가되었습니다.", Toast.LENGTH_SHORT).show();
				}
			});
			
			mButtonPositionConfirm.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				}
			});
			
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
