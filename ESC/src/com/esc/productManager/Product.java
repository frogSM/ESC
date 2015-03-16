package com.esc.productManager;

public class Product {

	String name;
	String price;
	String description;
	String manufacturer;
	String imgURL;
	String type;
	String x;
	String y;
	
	public Product( String name, String price, String description, String manufacturer, String imgURL,
			String type, String x, String y) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.price = price;
		this.description = description;
		this.manufacturer = manufacturer;
		this.imgURL = imgURL;
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
	
	public void setDescription( String description ) {
		this.description = description;
	}
	
	public void setManufacturer ( String manufacturer ) {
		this.manufacturer = manufacturer;
	}
	
	public void setImgURL( String imgURL ) {
		this.imgURL = imgURL;
	}
	
	public void setType( String type ) {
		this.type = type;
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
	
	public String getDescription( ) {
		return this.description;
	}
	
	public String getManufacturer ( ) {
		return this.manufacturer;
	}
	
	public String getImgURL( ) {
		return this.imgURL;
	}

	public String getType( ) { 
		return this.type;
	}
	
	public String getX() { 
		return this.x;
	}
	
	public String getY() { 
		return this.y;
	}
}
