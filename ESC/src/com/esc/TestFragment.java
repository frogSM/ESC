package com.esc;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class TestFragment extends Fragment {
	private ListView m_listview;
	private ArrayList	<String> items;
	private ArrayAdapter<String> adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_searchproduct, container, false);
		m_listview = (ListView)v.findViewById(R.id.listview);
		
		items = new ArrayList<String>();
		items.add("01������");
		items.add("02�̼���");
		items.add("03������");
		items.add("04��������");
		items.add("05������");
		items.add("06�̼���");
		items.add("07������");
		items.add("08��������");
		items.add("09������");
		items.add("10�̼���");
		items.add("11������");
		items.add("12��������");
		
		adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
		
		m_listview.setAdapter(adapter);
		
		return v;
	}
}
