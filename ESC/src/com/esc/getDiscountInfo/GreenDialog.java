package com.esc.getDiscountInfo;

/** GreenDialog Ŭ����
 * 		�� Ŭ������ BeaconMonitoringService ���񽺸� ���� �������� 
 * 		Green ���� �����ȿ� ������ �Ǿ����� ȣ��Ǵ� ���̾�α��̸�
 * 		���� ���񽺴ܿ��� ���̾�α׸� ���°� ��ü�� ������������
 * 		�Ŵ��佺Ʈ�� SYSTEM_ALERT_WINDOW �۹̼��� �ο��ϰ�
 * 		getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT); �� �̿��Ͽ�
 * 		���̾�α׸� �ֻ��� �ܿ� �÷��ֹǷν� �ذ��Ͽ���.
 * **/

import com.esc.R;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class GreenDialog extends Dialog implements OnClickListener{

	/** ���̾�α� ���̾ƿ� ���� **/
	private View mView;
	
	/** �����ư **/
	private Button mButton_end;
	
	/** �̹��� ���� �̹����� **/
	private ImageView mImageView;
	
	public GreenDialog(Context context) {
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
		mImageView.setImageResource(R.drawable.img_greenarea);
		
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
