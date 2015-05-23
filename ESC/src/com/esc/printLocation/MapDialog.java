package com.esc.printLocation;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;

import com.esc.R;
import com.perples.recosdk.RECOBeacon;
import com.perples.recosdk.RECOBeaconManager;
import com.perples.recosdk.RECOBeaconRegion;
import com.perples.recosdk.RECORangingListener;

public class MapDialog extends Dialog implements OnClickListener, RECORangingListener {
	
	/** ���̾�α� ���̾ƿ� ���� **/
	private View mView;
	private Context mContext;
	
	/** ������ ������ ���� ��ü **/
	private RECOBeaconManager mBeaconManager;
	private BeaconHelper mBeaconHelper;
	
	/** �� ���� ���� **/
	private ImageView mMap;
	private ImageView mMarker;				//��Ŀ �׸�
	private Button mBtnExit;
	
	/** ��ǰ���� ���� �ٸ��� �׸��� ���� ���� **/
	private String mProductType;

	/** ������ **/
	public MapDialog(Context context, String productType) {
		super(context);
		// TODO Auto-generated constructor stub
		this.mContext = context;
		this.mProductType = productType;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		
		mView = getLayoutInflater().inflate(R.layout.dialog_map, null);
		setContentView(mView);
		
		mBeaconHelper = new BeaconHelper(mContext);
		mBeaconManager = mBeaconHelper.getBeaconManager();
		mBeaconManager.setRangingListener(this);
		
		mMap = (ImageView)mView.findViewById(R.id.iv_map);
		mMarker = (ImageView)mView.findViewById(R.id.iv_productpicture2);
		drawMap();
		mBtnExit = (Button)mView.findViewById(R.id.btn_map_exit);
		mBtnExit.setOnClickListener(this);
		
	}
	
	private void drawMap() {
		switch(mProductType) {
		case "���ڷ�" :
			mMap.setBackgroundResource(R.drawable.background_map2_area1);
			break;
		case "����" :
			mMap.setBackgroundResource(R.drawable.background_map2_area2);
			break;
		case "������" :
			mMap.setBackgroundResource(R.drawable.background_map2_area3);
			break;
		case "������" : 
			mMap.setBackgroundResource(R.drawable.background_map2_area4);
			break;
		}
	}
	
	@Override
	public void didRangeBeaconsInRegion(Collection<RECOBeacon> beacons,
			RECOBeaconRegion regions) {
		// TODO Auto-generated method stub
		float markerX;
		float markerY;
		
		/** ���� ��ġ ǥ���ϴ� �� 285*285 ���� �� INVISIBLE ó��. **/
		mMarker.setLayoutParams(new AbsoluteLayout.LayoutParams(285, 285, 0, 0));
		mMarker.setVisibility(View.INVISIBLE);
		
		/** ������ ȿ�� ���� **/
		Drawable alpha = mMarker.getDrawable();
		alpha.setAlpha(100);
		
		ArrayList<BeaconInfo> mTemp = new ArrayList<BeaconInfo>();
		mTemp = mBeaconHelper.getBeaconInfo(beacons);
		
		if(mTemp.get(0) != null && (-75 <= mTemp.get(0).getRssi()) && (mTemp.get(0).getRssi() <= -5)) {
			markerX = 104;
			markerY = 100+407; // 100+ ���ִ� ���� -> ���� ���̾ƿ� ���� 50dp ������
			mMarker.setX(markerX);
			mMarker.setY(markerY);
			mMarker.setVisibility(View.VISIBLE);
		}
		else if(mTemp.get(1) != null && (-75 <= mTemp.get(1).getRssi()) && (mTemp.get(1).getRssi() <= -5)) {
			markerX = 474;
			markerY = 100+407;
			mMarker.setX(markerX);
			mMarker.setY(markerY);
			mMarker.setVisibility(View.VISIBLE);
		}
		else if(mTemp.get(2) != null  && (-75 <= mTemp.get(2).getRssi()) && (mTemp.get(2).getRssi() <= -5)) {
			markerX = 844;
			markerY = 100+407;
			mMarker.setX(markerX);
			mMarker.setY(markerY);
			mMarker.setVisibility(View.VISIBLE);
		}
		else if(mTemp.get(3) != null  && (-75 <= mTemp.get(3).getRssi()) && (mTemp.get(3).getRssi() <= -5)) {
			markerX = 1215;
			markerY = 100+407;
			mMarker.setX(markerX);
			mMarker.setY(markerY);
			mMarker.setVisibility(View.VISIBLE);
		}
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		dismiss();
	}


}
