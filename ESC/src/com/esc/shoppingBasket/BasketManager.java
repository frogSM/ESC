package com.esc.shoppingBasket;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;

import com.esc.productManager.Product;

public class BasketManager extends Observable {
	
	/** Basket �̱��� �ν��Ͻ� **/
	private static BasketManager instance;
	
	/** ���� ��ǥ �ݾ� **/
	private int goalPrice ;
	
	/** ��ٱ��� �� �ݾ�**/
	private int allProductPrice = 0;
	
	/** ��ٱ��Ͽ� ��� ��ǰ�� **/
	private ArrayList<Product> mBasket;

	public static BasketManager getInstance() {
		if (instance == null) {
			instance = new BasketManager();
		}
		return instance;
	}
	
	public BasketManager() {
		// TODO Auto-generated constructor stub
		mBasket = new ArrayList<Product>();
		goalPrice = 0;
	}
	
	public void setGoalPrice(int price) {
		this.goalPrice = price;
		setChanged();
		notifyObservers();
	}
	
	public int getGoalPrice() {
		return goalPrice;
	}
	
	public int getAllProductPrice() {
		return allProductPrice;
	}
	
	private void calculateAllProductPrice() {
		int tempAllProductPrice = 0;
		
		for(int i=0 ; i<mBasket.size() ; i++) {
			tempAllProductPrice += Integer.parseInt(mBasket.get(i).getPriceNow());
		}
		allProductPrice = tempAllProductPrice;
	}
	
	public void addBasketProduct(Product product) {
		mBasket.add(product);
		removeDuplicationElement();
		calculateAllProductPrice();
		
		setChanged();
		notifyObservers();
	}
	
	public void removeBasketProduct(Product product) {
		mBasket.remove(product);
		calculateAllProductPrice();
		
		setChanged();
		notifyObservers();
	}
	
	public ArrayList<Product> getBasket() {
		return mBasket;
	}
	
	private void removeDuplicationElement() {
		/** ArrayList<Product> -> HashSet<Product> -> ArrayList<Product>�� �̿��� �ߺ����� **/
//		HashSet<Product> hs = new HashSet<Product>(mBasket);
//		mBasket = new ArrayList<Product>(hs);
		
		for(int i=0 ; i<mBasket.size() ; i++) {
			for(int j=0 ; j<i ; j++) {
				if(mBasket.get(i).getName().equals(mBasket.get(j).getName())) {
					mBasket.remove(mBasket.get(j));
					i--;
					break;
				}
			}
		}
		
	}
}
