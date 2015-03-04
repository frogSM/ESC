package com.esc.getDiscountInfo;

/** GreenDialog 클래스
 * 		이 클래스는 BeaconMonitoringService 서비스를 실행 시켰을때 
 * 		Yellow 비콘 영역안에 들어오게 되었을때 호출되는 다이얼로그이며
 * 		본래 서비스단에서 다이얼로그를 띄우는거 자체가 허용되지않지만
 * 		매니페스트에 SYSTEM_ALERT_WINDOW 퍼미션을 부여하고
 * 		getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT); 을 이용하여
 * 		다이얼로그를 최상위 단에 올려주므로써 해결하였다.
 * **/

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.esc.R;

public class YellowDialog extends Dialog implements OnClickListener {

	/** 다이얼로그 레이아웃 정보 **/
	private View mView;
	
	/** 종료버튼 **/
	private Button mButton_end;
	
	/** 이미지 띄우는 이미지뷰 **/
	private ImageView mImageView;
	
	public YellowDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		mView = getLayoutInflater().inflate(R.layout.dialog_discountinfo, null);  
		setContentView(mView);
		
		mImageView = (ImageView) mView.findViewById(R.id.enterImage);
		mImageView.setImageResource(R.drawable.img_yellowarea);
		
		mButton_end = (Button)mView.findViewById(R.id.btn_exit);
		mButton_end.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.btn_exit :
			dismiss();
			break;
		}
	}
}
