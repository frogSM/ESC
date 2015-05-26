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
	
	/** 소켓 및 Json 도우미**/
	private SocketHelper mSocketHelper;
	private JsonHelper mJsonHelper;
	
	/** 리스트에 보여줄 상품들 이름 **/
	private String[] mProductsName;
	
	/** DB에서 불러올 전체 상품 **/
	private ArrayList<Product> products;
	
	/** SearchView에 입력하여 필터링된 상품들 **/
	private ArrayList<Product> searchProducts;
	
	/** 15.04.29 ArrayAdapter -> SearchListAdaptor로 변경작업 중 **/
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
		mProgressDialog = ProgressDialog.show(mContext, "Loding..", "데이터를 받는 중입니다....");
		mSocketHelper.sendMessage(mHandler, str_json);
		
		return mView;
	}
	
	/** Socket에서 받아온 JSON형식 문장을 파싱하여 객체에 저장하는 핸들러**/
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
	
	/** DB에서 가져온 데이터를 Activity에 초기화하는 메소드 **/
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
	
	/** SearchProducts 배열 갱신시키는 메소드 **/
	public void updateSearchProducts(SearchListAdapter updateAdapter) {
		for(int i=0 ; i<updateAdapter.getCount() ; i++) {
			for(int j=0 ; j<products.size() ; j++) {
				if(updateAdapter.getItem(i).getName() == products.get(j).getName())
					searchProducts.add(products.get(j));
			}
		}
		
		for(Product p : searchProducts) {
			Log.e("SearchFragment", "이름 : " +  p.getName() + " 가격 : " + p.getPriceNow());
		}
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		// TODO Auto-generated method stub
		return false;
	}

	/** SearchView의 변화를 감지하는 메소드 **/
	@Override
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub
		if (TextUtils.isEmpty(newText)) {
			
			adapter.getFilter().filter(newText, new FilterListener() {

				/** 필터링이 완료 된후 갱신된 Adapter , 중복처리된거 알고있음 **/
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

				/** 필터링이 완료 된후 갱신된 Adapter **/
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

	/** SearchView의 각 아이템 클릭할때 발생하는 이벤트 **/
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		SearchItemDialog dialog = new SearchItemDialog(getActivity(), searchProducts, position);
		dialog.show();
	}
	
}
