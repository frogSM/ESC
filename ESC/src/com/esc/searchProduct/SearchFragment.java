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
	
	/** ����Ʈ�� ������ ��ǰ�� �̸� **/
	private String[] mProductsName;
	/** DB���� �ҷ��� ��ü ��ǰ **/
	private ArrayList<Product> products;
	/** SearchView�� �Է��Ͽ� ���͸��� ��ǰ�� **/
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
	
	/** �ӽõ����� ���� **/
	public void dataLoding() {
//		products = new ArrayList<Product>();
//		Product p1 = new Product( "����1", 	"1000", "���ڷ�", "100", "100", "���Ұ���", "����ȸ��");
//		Product p2 = new Product( "����2", 	"2000", "���ڷ�", "100", "100", "�ܱ�����", "�ܱ�ȸ��");
//		Product p3 = new Product( "����3", 	"3000", "���ڷ�", "100", "100", "���Ұ���", "����ȸ��");
//		Product p4 = new Product( "����4", 	"4000", "���ڷ�", "100", "100", "�ܱ�����", "�ܱ�ȸ��");
//		Product p5 = new Product( "����5", 	"5000", "���ڷ�", "100", "100", "���Ұ���", "����ȸ��");
//		Product p6 = new Product( "����6", 	"6000", "���ڷ�","100", "100", "���Ұ���", "����ȸ��");
//		Product p7 = new Product( "����7", 	"7000", "���ڷ�", "100", "100", "���Ұ���", "����ȸ��");
//		Product p8 = new Product( "����8", 	"8000", "���ڷ�","100", "100", "���Ұ���", "����ȸ��");
//		Product p9 = new Product( "����9", 	"9000", "���ڷ�", "100", "100", "���Ұ���", "����ȸ��");
//		Product p10 = new Product( "����10", 	"10000", "���ڷ�", "100", "100", "���Ұ���", "����ȸ��");
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

	/** SearchView�� ��ȭ�� �����ϴ� �޼ҵ� **/
	@Override
	public boolean onQueryTextChange(String newText) {
		if (TextUtils.isEmpty(newText)) {
			
			adapter.getFilter().filter(null);
			
		} else {
			
			adapter.getFilter().filter(newText, new FilterListener() {

				/** ���͸��� �Ϸ� ���� ���ŵ� Adapter **/
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
	
	/** SearchProducts �迭 ���Ž�Ű�� �޼ҵ� **/
	public void updateSearchProducts(ArrayAdapter<String> updateAdapter) {
		for(int i=0 ; i<updateAdapter.getCount() ; i++) {
			for(int j=0 ; j<products.size() ; j++) {
				if(updateAdapter.getItem(i) == products.get(j).getName())
					searchProducts.add(products.get(j));
			}
		}
		
		for(Product p : searchProducts) {
			Log.e("SearchFragment", "�̸� : " +  p.getName() + " ���� : " + p.getPrice());
		}
	}
	
	/** SearchView�� �� ������ Ŭ���Ҷ� �߻��ϴ� �̺�Ʈ **/
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		SearchItemDialog dialog = new SearchItemDialog(getActivity(), searchProducts, position);
		dialog.show();
	}
	
}
