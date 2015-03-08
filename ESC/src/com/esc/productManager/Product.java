package com.esc.productManager;

public class Product {

	String name;
	String price;
	String type;
	String x;
	String y;
	
	public Product( String name, String price, String type, String x, String y) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.price = price;
		this.type = type;
		this.x = x;
		this.y = y;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	public void setLocation(String x, String y) { 
		this.x = x;
		this.y = y;
	}
	

	public String getName() {
		return name;
	}
	
	public String getPrice() {
		return price;
	}
	public String getType( ) { 
		return type;
	}
	public String getX() { 
		return x;
	}
	public String getY() { 
		return y;
	}
}
