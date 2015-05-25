package com.esc.searchProduct;

public class RecommendProduct {

	String imgURI;
	String name;
	String price;

	public RecommendProduct(String imgURI, String name, String price) {
		this.imgURI = imgURI;
		this.name = name;
		this.price = price;
	}
	
	public void setImgURI(String imgURI) {
		this.imgURI = imgURI;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}

	public String getImgURI() {
		return imgURI;
	}

	public String getName() {
		return name;
	}

	public String getPrice() {
		return price;
	}


}