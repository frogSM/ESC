package com.esc.productManager;

import java.util.ArrayList;
import java.util.List;

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
	
	SocketHelper mSocketHelper;
	subAsyncTask mSubAsyncTask;
	ProductManager productManager;
	
	ProductListAdaptor productListAdapter;
	ArrayList<Product> products;
	ListView productList;
	RenewHandler renewHandler;
	JsonHelper jsonHelper;
	
	public ProductManagerFragment ( ProductManager productManager) {
		this.productManager = productManager;
	}
 	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_productmanager, container, false);

		jsonHelper = JsonHelper.getInstance(getActivity().getApplicationContext());
		mSocketHelper = mSocketHelper.getInstance(getActivity().getApplicationContext());
		
		productList = (ListView)view.findViewById(R.id.productList);
		products = new ArrayList<Product> ();
	    productListAdapter = new ProductListAdaptor(getActivity().getApplicationContext(),products);
	    
	    renewHandler = new RenewHandler ();
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
		@Override
		protected void onPreExecute() {
			productList.setAdapter(productListAdapter);
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		protected Void doInBackground(Void... message) {
			
			while(true) {
				ArrayList<String> taggedUIDs = new ArrayList<String>();
				taggedUIDs = productManager.GetTaggedUIDs();
	
				String str_json = jsonHelper.makeJsonMessage(Constants.Uid_Info, taggedUIDs);
				mSocketHelper.sendMessage(renewHandler, str_json);
//				if(productListAdapter == null) {
//					break;
//				}
			}
		//	return null;
		}
		
	}
	
    // Handler Å¬·¡½º
    class RenewHandler extends Handler {
         
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
             
            switch (msg.what) {
            case Constants.THREAD_MESSAGE:
            	products = (ArrayList<Product>)jsonHelper.parserJsonMessage(msg.obj.toString());
            	productListAdapter.notifyDataSetChanged();

            	break;
            default:
                break;
            }
        }
         
    };
	

}
