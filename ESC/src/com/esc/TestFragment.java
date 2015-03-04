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
		items.add("01강감찬");
		items.add("02이순신");
		items.add("03김유신");
		items.add("04을지문덕");
		items.add("05강감찬");
		items.add("06이순신");
		items.add("07김유신");
		items.add("08을지문덕");
		items.add("09강감찬");
		items.add("10이순신");
		items.add("11김유신");
		items.add("12을지문덕");
		
		adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
		
		m_listview.setAdapter(adapter);
		
		return v;
	}
}
