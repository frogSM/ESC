package com.esc.productManager;

public class Product {

	String name;
	String price;
	String type;
	double x;
	double y;
	
	public Product( String name, String price, String type, double x, double y) {
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
	
	public void setLocation(double x, double y) { 
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
	public double getX() { 
		return x;
	}
	public double getY() { 
		return y;
	}
}
