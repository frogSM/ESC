package com.esc.searchProduct;

import java.util.ArrayList;

import com.esc.R;
import com.esc.productManager.Product;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Filter.FilterListener;
import android.widget.SearchView.OnQueryTextListener;

public class SearchFragment extends Fragment implements OnQueryTextListener, OnItemClickListener {
	
	private SearchView mSearchView;
	private ListView mListView;
	private ArrayAdapter<String> adapter;
	
	/** 리스트에 보여줄 상품들 이름 **/
	private String[] mProductsName;
	/** DB에서 불러올 전체 상품 **/
	private ArrayList<Product> products;
	/** SearchView에 입력하여 필터링된 상품들 **/
	private ArrayList<Product> searchProducts;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_searchproduct, container, false);
		
		mSearchView = (SearchView)v.findViewById(R.id.searchView1);
		mListView = (ListView)v.findViewById(R.id.listView1);
		
		dataLoding();
		
		adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mProductsName);
		mListView.setAdapter(adapter);
		mListView.setTextFilterEnabled(true);
		mListView.setOnItemClickListener(this);
		
		mSearchView.setOnQueryTextListener(this);
		
		return v;
	}
	
	/** 임시데이터 가정 **/
	public void dataLoding() {
//		products = new ArrayList<Product>();
//		Product p1 = new Product( "과자1", 	"1000", "과자류", "100", "100", "질소과자", "질소회사");
//		Product p2 = new Product( "과자2", 	"2000", "과자류", "100", "100", "외국과자", "외국회사");
//		Product p3 = new Product( "과자3", 	"3000", "과자류", "100", "100", "질소과지", "질소회사");
//		Product p4 = new Product( "과자4", 	"4000", "과자류", "100", "100", "외국과자", "외국회사");
//		Product p5 = new Product( "과자5", 	"5000", "과자류", "100", "100", "질소과지", "질소회사");
//		Product p6 = new Product( "과자6", 	"6000", "과자류","100", "100", "질소과지", "질소회사");
//		Product p7 = new Product( "과자7", 	"7000", "과자류", "100", "100", "질소과지", "질소회사");
//		Product p8 = new Product( "과자8", 	"8000", "과자류","100", "100", "질소과지", "질소회사");
//		Product p9 = new Product( "과자9", 	"9000", "과자류", "100", "100", "질소과지", "질소회사");
//		Product p10 = new Product( "과자10", 	"10000", "과자류", "100", "100", "질소과지", "질소회사");
//		
//		products.add(p1);
//		products.add(p2);
//		products.add(p3);
//		products.add(p4);
//		products.add(p5);
//		products.add(p6);
//		products.add(p7);
//		products.add(p8);
//		products.add(p9);
//		products.add(p10);
//		
//		searchProducts = new ArrayList<Product>();
//		
//		mProductsName = new String[products.size()];
//		for(int i=0 ; i<products.size() ; i++) {
//			mProductsName[i] = products.get(i).getName();
//			searchProducts.add(products.get(i));
//		}
	}

	/** SearchView의 변화를 감지하는 메소드 **/
	@Override
	public boolean onQueryTextChange(String newText) {
		if (TextUtils.isEmpty(newText)) {
			
			adapter.getFilter().filter(null);
			
		} else {
			
			adapter.getFilter().filter(newText, new FilterListener() {

				/** 필터링이 완료 된후 갱신된 Adapter **/
				@Override
				public void onFilterComplete(int count) {
					// TODO Auto-generated method stub
					searchProducts.clear();
					for (int i = 0; i < adapter.getCount(); i++) {
						Log.e("SearchFragment", adapter.getItem(i));
					}
					updateSearchProducts(adapter);
				}
			});
		}
		return true;
	}
	
	@Override
	public boolean onQueryTextSubmit(String query) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/** SearchProducts 배열 갱신시키는 메소드 **/
	public void updateSearchProducts(ArrayAdapter<String> updateAdapter) {
		for(int i=0 ; i<updateAdapter.getCount() ; i++) {
			for(int j=0 ; j<products.size() ; j++) {
				if(updateAdapter.getItem(i) == products.get(j).getName())
					searchProducts.add(products.get(j));
			}
		}
		
		for(Product p : searchProducts) {
			Log.e("SearchFragment", "이름 : " +  p.getName() + " 가격 : " + p.getPrice());
		}
	}
	
	/** SearchView의 각 아이템 클릭할때 발생하는 이벤트 **/
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		SearchItemDialog dialog = new SearchItemDialog(getActivity(), searchProducts, position);
		dialog.show();
	}
	
}
