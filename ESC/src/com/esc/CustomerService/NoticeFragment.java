package com.esc.CustomerService;

import java.util.ArrayList;

import com.esc.R;
import com.esc.Connection.JsonHelper;
import com.esc.productManager.Product;
import com.esc.productManager.ProductListAdaptor;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class NoticeFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_customernotice, container, false);

	    		
		return view;
	}

}
