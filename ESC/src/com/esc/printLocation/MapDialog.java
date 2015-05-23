package com.esc.printLocation;

import com.esc.R;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;

public class MapDialog extends Dialog implements OnClickListener{
	
	/** 다이얼로그 레이아웃 정보 **/
	private View mView;

	public MapDialog(Context context) {
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
		
		mView = getLayoutInflater().inflate(R.layout.dialog_map, null);
		setContentView(mView);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
