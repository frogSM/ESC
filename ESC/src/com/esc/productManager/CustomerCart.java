package com.esc.productManager;

public class CustomerCart {
	String number;
	String name;
	String price;
	String count;
	String manufacturer;
	String type;
	
	public CustomerCart(String number, String name, String price, String count, String manufacturer, String type) {
		// TODO Auto-generated constructor stub
		this.number = number;
		this.name = name;
		this.price = price;
		this.count = count;
		this.manufacturer = manufacturer;
		this.type = type;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public void setCount(String count) {
		this.count = count;
	}
	
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getNumber() {
		return number;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPrice() {
		return price;
	}
	
	public String getCount() {
		return count;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}
	
	public String getType() {
		return type;
	}
}


