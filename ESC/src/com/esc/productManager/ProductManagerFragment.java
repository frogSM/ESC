package com.esc.productManager;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.esc.Constants;
import com.esc.R;
import com.esc.Connection.JsonHelper;
import com.esc.Connection.SocketHelper;

public class ProductManagerFragment extends Fragment{
	ProductListAdaptor productListAdapter;
	
	SocketHelper mSocketHelper;
	subAsyncTask mSubAsyncTask;
	
	ProductManager productManager;
	
	public ProductManagerFragment ( ProductManager productManager) {
		this.productManager = productManager;
	}
 	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_productmanager, container, false);

	 
	    
	    ArrayList<String> test = new ArrayList ( ); 
	    ListView listview = new ListView(getActivity().getApplicationContext());
	    productListAdapter = new ProductListAdaptor(getActivity().getApplicationContext());
	    listview.setAdapter(productListAdapter);
	    
	    //productManager.OpenSerialPort();
	    
	    mSocketHelper = mSocketHelper.getInstance(getActivity().getApplicationContext());
	   
	    mSubAsyncTask = new subAsyncTask();
	    mSubAsyncTask.execute();
	    		
		return view;
	}
	

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	

	private class subAsyncTask extends AsyncTask<Void, Void, Void> {
		
		JsonHelper jsonhelper = JsonHelper.getInstance(getActivity().getApplicationContext());
		@Override
		protected Void doInBackground(Void... message) {
			// TODO Auto-generated method stub
			
			while(true) {
				ArrayList<String> taggedUIDs = new ArrayList<String>();
				taggedUIDs = productManager.GetTaggedUIDs();
	
				/** 처리부분 **/
				String str_json = jsonhelper.makeJsonMessage(Constants.Uid_Info, taggedUIDs);
				//mSocketHelper.sendMessage(mHandler, str_json);
				/**처리부분 **/
			}
			
		}
		
	}
	
	Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			if(msg.what == Constants.THREAD_MESSAGE) {
				ArrayList<Product> test = (ArrayList<Product>)msg.obj;
				// Product객체를 List로 받아온 것을 이용해서 baseAdapter이용해야함.
				
			}
		}
	};
	
	

}
