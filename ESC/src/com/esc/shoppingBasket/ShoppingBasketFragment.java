package com.esc.shoppingBasket;

import java.util.ArrayList;
import java.util.HashSet;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.esc.R;
import com.esc.productManager.Product;

public class ShoppingBasketFragment extends ListFragment {
	
	/** 프레그먼트 레이아웃 정보 **/
	private View mView;
	private ListView mListView;
	
	/** BasketManager 인스턴스**/
	private BasketManager mBasketManager;
	
	/** 현재 장바구니에 담겨져있는 상품 목록 **/
	private ArrayList<Product> mBasket;
	
	private ShoppingBasketListAdaptor mAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mView = inflater.inflate(R.layout.fragment_shoppingbasket, container, false);
		mListView = (ListView)mView.findViewById(android.R.id.list);
		
		mBasketManager = BasketManager.getInstance();
		dataLoding();
		
		return mView;
	}
	
	private void dataLoding() {
		
		mAdapter = new ShoppingBasketListAdaptor(getActivity().getApplicationContext());
		mListView.setAdapter(mAdapter);
		
		SwipeDismissListViewTouchListener touchListener =
		        new SwipeDismissListViewTouchListener(
		        		mListView,
		                new SwipeDismissListViewTouchListener.DismissCallbacks() {
		                    @Override
		                    public boolean canDismiss(int position) {
		                        return true;
		                    }

		                    @Override
		                    public void onDismiss(ListView listView, int[] reverseSortedPositions) {
		                        for (int position : reverseSortedPositions) {
		                            mAdapter.remove(mAdapter.getItem(position));
		                        }
		                        mAdapter.notifyDataSetChanged();
		                    }
		                });
		mListView.setOnTouchListener(touchListener);
		mListView.setOnScrollListener(touchListener.makeScrollListener());
	}
}
