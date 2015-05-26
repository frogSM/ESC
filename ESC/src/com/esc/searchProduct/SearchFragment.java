package com.esc.searchProduct;

import java.util.ArrayList;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Filter.FilterListener;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

import com.esc.Constants;
import com.esc.R;
import com.esc.Connection.JsonHelper;
import com.esc.Connection.SocketHelper;
import com.esc.productManager.Product;

public class SearchFragment extends Fragment implements OnItemClickListener, OnQueryTextListener {
	
	private View mView;
	private Context mContext;
	private SearchView mSearchView;
	private ListView mListView;
	private ProgressDialog mProgressDialog;
	
	/** ���� �� Json �����**/
	private SocketHelper mSocketHelper;
	private JsonHelper mJsonHelper;
	
	/** ����Ʈ�� ������ ��ǰ�� �̸� **/
	private String[] mProductsName;
	
	/** DB���� �ҷ��� ��ü ��ǰ **/
	private ArrayList<Product> products;
	
	/** SearchView�� �Է��Ͽ� ���͸��� ��ǰ�� **/
	private ArrayList<Product> searchProducts;
	
	/** 15.04.29 ArrayAdapter -> SearchListAdaptor�� �����۾� �� **/
	private SearchListAdapter adapter;
	
	
	public SearchFragment(Context context) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mView = inflater.inflate(R.layout.fragment_searchproduct, container, false);
		
		mSearchView = (SearchView) mView.findViewById(R.id.searchView1);
		mListView = (ListView) mView.findViewById(R.id.list);

		products = new ArrayList<Product>();

		mSocketHelper = SocketHelper.getInstance(getActivity().getApplicationContext());
		mJsonHelper = JsonHelper.getInstance(getActivity().getApplication().getApplicationContext());

		String str_json = mJsonHelper.makeJsonMessage(Constants.All_Product_Info, null);
		mProgressDialog = ProgressDialog.show(mContext, "Loding..", "�����͸� �޴� ���Դϴ�....");
		mSocketHelper.sendMessage(mHandler, str_json);
		
		return mView;
	}
	
	/** Socket���� �޾ƿ� JSON���� ������ �Ľ��Ͽ� ��ü�� �����ϴ� �ڵ鷯**/
	private Handler mHandler = new Handler() {
		
		@Override
		public void handleMessage(android.os.Message msg)
				throws NullPointerException {
			if (msg.what == Constants.THREAD_MESSAGE) {
				searchProducts = new ArrayList<Product>();
				products = (ArrayList<Product>) mJsonHelper	.parserJsonMessage(msg.obj.toString());
				dataLoding();
				mProgressDialog.dismiss();
			}
		};
	};
	
	/** DB���� ������ �����͸� Activity�� �ʱ�ȭ�ϴ� �޼ҵ� **/
	private void dataLoding() {
		mProductsName = new String[products.size()];
		for(int i=0 ; i<products.size() ; i++) {
			mProductsName[i] = products.get(i).getName();
			searchProducts.add(products.get(i));
		}
		
		adapter = new SearchListAdapter(getActivity().getApplicationContext(), products);
		
		mListView.setAdapter(adapter);
		mListView.setTextFilterEnabled(true);
		
		mListView.setOnItemClickListener(this);
		mSearchView.setOnQueryTextListener(this);
	}
	
	/** SearchProducts �迭 ���Ž�Ű�� �޼ҵ� **/
	public void updateSearchProducts(SearchListAdapter updateAdapter) {
		for(int i=0 ; i<updateAdapter.getCount() ; i++) {
			for(int j=0 ; j<products.size() ; j++) {
				if(updateAdapter.getItem(i).getName() == products.get(j).getName())
					searchProducts.add(products.get(j));
			}
		}
		
		for(Product p : searchProducts) {
			Log.e("SearchFragment", "�̸� : " +  p.getName() + " ���� : " + p.getPriceNow());
		}
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		// TODO Auto-generated method stub
		return false;
	}

	/** SearchView�� ��ȭ�� �����ϴ� �޼ҵ� **/
	@Override
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub
		if (TextUtils.isEmpty(newText)) {
			
			adapter.getFilter().filter(newText, new FilterListener() {

				/** ���͸��� �Ϸ� ���� ���ŵ� Adapter , �ߺ�ó���Ȱ� �˰����� **/
				@Override
				public void onFilterComplete(int count) {
					// TODO Auto-generated method stub
					searchProducts.clear();
					for (int i = 0; i < adapter.getCount(); i++) {
//						Log.e("SearchFragment", adapter.getItem(i));
						Log.e("SearchFragment", adapter.getItem(i).getName());
					}
					updateSearchProducts(adapter);
				}
			});
			
		} else {
			adapter.getFilter().filter(newText, new FilterListener() {

				/** ���͸��� �Ϸ� ���� ���ŵ� Adapter **/
				@Override
				public void onFilterComplete(int count) {
					// TODO Auto-generated method stub
					searchProducts.clear();
					for (int i = 0; i < adapter.getCount(); i++) {
//						Log.e("SearchFragment", adapter.getItem(i));
						Log.e("SearchFragment", adapter.getItem(i).getName());
					}
					updateSearchProducts(adapter);
				}
			});
		}
		return true;
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
