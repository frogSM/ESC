package com.esc.productManager;

public class Product {

	String name;
	String price;
	String type;
	String x;
	String y;
	String description;
	String manufacturer;
	
	public Product( String name, String price, String type, String x, String y, String description, String manufacturer) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.price = price;
		this.type = type;
		this.x = x;
		this.y = y;
		this.description = description;
		this.manufacturer = manufacturer;
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
	
	public void setDescription( String description ) {
		this.description = description;
	}
	
	public void setManufacturer ( String manufacturer ) {
		this.manufacturer = manufacturer;
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
	
	public String getDescription( ) {
		return this.description;
	}
	
	public String getManufacturer ( ) {
		return this.manufacturer;
	}
}
