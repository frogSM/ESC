package com.esc.shoppingBasket;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import android.app.ListFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.esc.R;
import com.esc.productManager.Product;

public class ShoppingBasketFragment extends ListFragment implements Observer {
	
	/** ������ ���� **/
	private Observable mObservable;
	
	/** �����׸�Ʈ ���̾ƿ� ���� **/
	private View mView;
	private ListView mListView;
	private TextView mGoalPrice;
	private TextView mPredictPrice;
	private TextView mGoalSumPredict_Title;
	private TextView mGoalSumPredict;
	
	/** BasketManager �ν��Ͻ�**/
	private BasketManager mBasketManager;
	
	/** ���� ��ٱ��Ͽ� ������ִ� ��ǰ ��� **/
	private ArrayList<Product> mBasket;
	
	/** ��ٱ��� �� ���� �ݾ� **/
	private int mSumPrice = 0;
	
	private ShoppingBasketListAdaptor mAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mView = inflater.inflate(R.layout.fragment_shoppingbasket, container, false);
		mListView = (ListView)mView.findViewById(android.R.id.list);
		mGoalPrice = (TextView)mView.findViewById(R.id.tv_goalprice);
		mPredictPrice = (TextView)mView.findViewById(R.id.tv_predictprice);
		mGoalSumPredict_Title = (TextView)mView.findViewById(R.id.tv_goalsumpredict_title);
		mGoalSumPredict = (TextView)mView.findViewById(R.id.tv_goalsumpredict);
		
		mBasketManager = BasketManager.getInstance();
		
		// ������ ��� ����
		this.mObservable = mBasketManager;
		mObservable.addObserver(this);
		
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
		                        	mSumPrice -= Integer.parseInt(mAdapter.getItem(position).getPriceNow());
		                        	mPredictPrice.setText(String.valueOf(mSumPrice));
		                        	
		                            mAdapter.remove(mAdapter.getItem(position));
		                        }
		                        mAdapter.notifyDataSetChanged();
		                    }
		                });
		mListView.setOnTouchListener(touchListener);
		mListView.setOnScrollListener(touchListener.makeScrollListener());
		
		mGoalPrice.setText(String.valueOf(mBasketManager.getGoalPrice()));
		
		for(int i=0 ; i<mAdapter.getCount() ; i++) {
			mSumPrice += Integer.parseInt(mAdapter.getItem(i).getPriceNow());
		}
		mPredictPrice.setText(String.valueOf(mSumPrice));
		
		int sumprice = mBasketManager.getGoalPrice()-mSumPrice;
		if(sumprice > 0 ) {
			mGoalSumPredict_Title.setText("�ܿ��ݾ� : ");
			mGoalSumPredict.setTextColor(Color.BLUE);
		} else {
			mGoalSumPredict_Title.setText("�ʰ��ݾ� : ");
			mGoalSumPredict.setTextColor(Color.RED);
		}
		mGoalSumPredict.setText(String.valueOf(Math.abs(sumprice)));
	}

	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		if(observable instanceof BasketManager) {
			BasketManager basketManager = (BasketManager)data;
			mGoalPrice.setText(String.valueOf(mBasketManager.getGoalPrice()));
			
			int sumprice = mBasketManager.getGoalPrice()-mSumPrice;
			if(sumprice > 0 ) {
				mGoalSumPredict_Title.setText("�ܿ��ݾ� : ");
				mGoalSumPredict.setTextColor(Color.BLUE);
			} else {
				mGoalSumPredict_Title.setText("�ʰ��ݾ� : ");
				mGoalSumPredict.setTextColor(Color.RED);
			}
			mGoalSumPredict.setText(String.valueOf(Math.abs(sumprice)));
		}
	}
}
