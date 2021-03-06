package com.esc.printLocation;

import java.util.ArrayList;
import java.util.Collection;

import com.esc.R;
import com.esc.getDiscountInfo.GreenDialog;
import com.esc.getDiscountInfo.RedDialog;
import com.esc.getDiscountInfo.WhiteDialog;
import com.esc.getDiscountInfo.YellowDialog;
import com.perples.recosdk.RECOBeacon;
import com.perples.recosdk.RECOBeaconManager;
import com.perples.recosdk.RECOBeaconRegion;
import com.perples.recosdk.RECORangingListener;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MapFragment extends Fragment implements RECORangingListener {
	
	/** inflater한 fragment레이아웃 정보 **/
	private View mView;
	
	/** 데이터 조작을 위한 객체 **/
	private RECOBeaconManager mBeaconManager;
	private BeaconHelper mBeaconHelper;
//	private PositionCalculation mPositionCalculation;
	
	/** 이전 안드로이드 X, Y 값 **/
	private double NowPosition_X;
	private double NowPosition_Y;
	
	/** 갱신 후 보정된 안드로이드 X, Y 값 **/
	private double RevisionPosition_X;
	private double RevisionPosition_Y;
	private double RevisionPosition_Z;
	
	/** 최대 넓이와 높이 제한 **/
	private double maxWidth;
	private double maxHeight;

	/** 맵 관련 전역변수 **/
	private AbsoluteLayout mapLayout;		//지도 레이아웃
	private ImageView mMarker;				//마커 그림
	private float screenWidth = 0;			// mImage 뷰 가로 길이
	private float screenHeight = 0;			// mImage 뷰 세로 길이
	
	// 영역별 한번만 출력할 수 있게끔 하는 변수
	private static int greenCnt = 0, yellowCnt = 0, redCnt = 0, whiteCnt = 0 ;
	
	/** 애니메이션 전역변수 **/
	private TranslateAnimation animation;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mView = inflater.inflate(R.layout.fragment_map, container, false);
		
		mBeaconHelper = new BeaconHelper(getActivity().getApplicationContext());
		mBeaconManager = mBeaconHelper.getBeaconManager();
		mBeaconManager.setRangingListener(this);
		
//		mPositionCalculation = new PositionCalculation();
		mapLayout = (AbsoluteLayout)mView.findViewById(R.id.AbsoluteLayout1);
		mMarker = (ImageView)mView.findViewById(R.id.iv_productpicture);
		
		drawMaker();
		
		return mView;
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mBeaconHelper = new BeaconHelper(getActivity().getApplicationContext());
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mBeaconHelper.stopAndUnbind();
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mBeaconHelper.stopAndUnbind();
	}
	
	@Override
	public void didRangeBeaconsInRegion(Collection<RECOBeacon> beacons,
			RECOBeaconRegion regions) {
		// TODO Auto-generated method stub
//		Log.i("NavigationActivity",
//				"didRangeBeaconsInRegion() region: "
//						+ regions.getUniqueIdentifier()
//						+ ", number of beacons ranged: " + beacons.size());
//		
//		NowPosition_X = mPositionCalculation.getAndroidPosition_X();
//		NowPosition_Y = mPositionCalculation.getAndroidPosition_Y();
//		
//		/** 칼만 보정 값 **/
//		for(int revisionCnt=0 ; revisionCnt<5 ; revisionCnt++) {
//			mPositionCalculation.setAndroidPosition(mBeaconHelper.getBeaconInfo(beacons));
//			mPositionCalculation.setCalmanRevision(revisionCnt);
//		}
//		
//		RevisionPosition_X = mPositionCalculation.getAndroidPosition_X();
//		RevisionPosition_Y = mPositionCalculation.getAndroidPosition_Y();
//		RevisionPosition_Z = mPositionCalculation.getAndroidPosition_Z();
//		maxWidth = mPositionCalculation.getDistanceGY();
//		maxHeight = mPositionCalculation.getDistanceGR();
//		
//		if ((RevisionPosition_X > 0 && RevisionPosition_X <= maxWidth)
//				&& (RevisionPosition_Y > 0 && RevisionPosition_Y <= maxHeight)) {
//			moveImage((float)(screenWidth / maxWidth * NowPosition_X), (float)(screenWidth / maxWidth * RevisionPosition_X),
//					(float)(screenHeight / maxHeight * NowPosition_Y), (float)(screenHeight / maxHeight * RevisionPosition_Y));
//		}
//		
//		Log.i("PositionCalculation", "[RevisionAfter] X="+ RevisionPosition_X+" Y="+RevisionPosition_Y +" Z="+ RevisionPosition_Z);
//		TextView txtX = (TextView)mView.findViewById(R.id.txtViewX);
//		txtX.setText("[X] : " +RevisionPosition_X);
//		TextView txtY = (TextView)mView.findViewById(R.id.txtViewY);
//		txtY.setText("[Y] : " + RevisionPosition_Y);
		
		float markerX;
		float markerY;
		
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		mMarker.setLayoutParams(new AbsoluteLayout.LayoutParams(99, 117, 0, 0));
		mMarker.setVisibility(View.INVISIBLE);
		
//		/** 반투명 효과 적용 **/
//		Drawable alpha = mMarker.getDrawable();
//		alpha.setAlpha(100);
		
		ArrayList<BeaconInfo> mTemp = new ArrayList<BeaconInfo>();
		mTemp = mBeaconHelper.getBeaconInfo(beacons);
		
		if(mTemp.get(0) != null && (-75 <= mTemp.get(0).getRssi()) && (mTemp.get(0).getRssi() <= -5)) {
//			if (greenCnt != 1) { 
//				GreenDialog green = new GreenDialog(getActivity().getApplicationContext());
//				green.show();
//				greenCnt++;
//			}
			markerX = 129;
			markerY = 459;
			mMarker.setX(markerX);
			mMarker.setY(markerY);
			mMarker.setVisibility(View.VISIBLE);
		}
		else if(mTemp.get(1) != null && (-75 <= mTemp.get(1).getRssi()) && (mTemp.get(1).getRssi() <= -5)) {
			if (yellowCnt != 1) {
				YellowDialog yellow = new YellowDialog(getActivity().getApplicationContext());
				yellow.show();
				yellowCnt++;
			}
			markerX = 573;
			markerY = 459;
			mMarker.setX(markerX);
			mMarker.setY(markerY);
			mMarker.setVisibility(View.VISIBLE);
		}
		else if(mTemp.get(2) != null  && (-75 <= mTemp.get(2).getRssi()) && (mTemp.get(2).getRssi() <= -5)) {
//			if (redCnt != 1) {
//				RedDialog red = new RedDialog(getActivity().getApplicationContext());
//				red.show();
//				redCnt++;
//			}
			markerX = 1023;
			markerY = 459;
			mMarker.setX(markerX);
			mMarker.setY(markerY);
			mMarker.setVisibility(View.VISIBLE);
		}
		else if(mTemp.get(3) != null  && (-75 <= mTemp.get(3).getRssi()) && (mTemp.get(3).getRssi() <= -5)) {
//			if (whiteCnt != 1) {
//				WhiteDialog white = new WhiteDialog(getActivity().getApplicationContext());
//				white.show();
//				whiteCnt++;
//			}
			markerX = 1473;
			markerY = 459;
			mMarker.setX(markerX);
			mMarker.setY(markerY);
			mMarker.setVisibility(View.VISIBLE);
		}
	}

	/** 현재 Map을 그린 레이아웃 정보 받기 **/
	public void drawMaker() {
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		mapLayout.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
		this.screenWidth = mapLayout.getMeasuredWidth() / dm.density;
		this.screenHeight = mapLayout.getMeasuredHeight() / dm.density;
		
		Log.i("NavigationActivity", "Width = " + screenWidth + ", Height = " + screenHeight);
		Log.i("NavigationActivity", "ScreenWidth = " + dm.widthPixels + ", ScreenHeight = " + dm.heightPixels);
	}
	
	/** Map Marker 이동 함수 **/
	public void moveImage(float fromX, final float toX, float fromY, final float toY) {
		animation = new TranslateAnimation(fromX, toX, fromY, toY);
		animation.setDuration(500);
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
			}
		});
		
//		Log.e("NavigationActivity", "[Device]  X: " +mAndroidToBeacon.getAndroidX() + ", Y: " + mAndroidToBeacon.getAndroidY());
		animation.setFillEnabled(true);
		animation.setFillAfter(true);
		mMarker.startAnimation(animation);
	}
}
