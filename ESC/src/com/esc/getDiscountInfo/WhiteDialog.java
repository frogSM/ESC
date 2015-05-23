package com.esc.getDiscountInfo;

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
import android.widget.Button;
import android.widget.ImageView;

public class WhiteDialog extends Dialog implements OnClickListener {
	/** 다이얼로그 레이아웃 정보 **/
	private View mView;

	/** 종료버튼 **/
	private Button mButton_end;

	/** 이미지 띄우는 이미지뷰 **/
	private ImageView mImageView;

	public WhiteDialog(Context context) {
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
		mImageView.setImageResource(R.drawable.img_whitearea);

		mButton_end = (Button) mView.findViewById(R.id.btn_exit);
		mButton_end.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_exit:
			dismiss();
			break;
		}
	}
}
