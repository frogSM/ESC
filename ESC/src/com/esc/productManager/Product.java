package com.esc.productManager;

public class Product {

	String name;
	String priceNow;
	String priceBeforeOne;
	String priceBeforeTwo;
	String priceBeforeThree;
	String priceBeforeSix;
	String description;
	String manufacturer;
	String imgURL;
	String type;
	String x;
	String y;
	
	public Product( String name, String price_now, String priceBeforeOne, String priceBeforeTwo, String priceBeforeThree,
			String priceBeforeSix, String description, String manufacturer, String imgURL, String type, String x, String y) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.priceNow = price_now;
		this.priceBeforeOne = priceBeforeOne;
		this.priceBeforeTwo = priceBeforeTwo;
		this.priceBeforeThree = priceBeforeThree;
		this.priceBeforeSix = priceBeforeSix;
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
	
	public void setPriceNow(String price_now) {
		this.priceNow = price_now;
	}
	
	public void setPriceBeforeOne(String priceBeforeOne) {
		this.priceBeforeOne = priceBeforeOne;
	}
	
	public void setPriceBeforeTwo(String priceBeforeTwo) {
		this.priceBeforeTwo = priceBeforeTwo;
	}
	
	public void setPriceBeforeThree(String priceBeforeThree) {
		this.priceBeforeThree = priceBeforeThree;
	}
	
	public void setPriceBeforeSix(String price_before_six) {
		this.priceBeforeSix = price_before_six;
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

	public String getPriceNow() {
		return priceNow;
	}
	
	public String getPriceBeforeOne() {
		return priceBeforeOne;
	}
	
	public String getPriceBeforeTwo() {
		return priceBeforeTwo;
	}
	
	public String getPriceBeforeThree() {
		return priceBeforeThree;
	}
	
	public String getPriceBeforeSix() {
		return priceBeforeSix;
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
