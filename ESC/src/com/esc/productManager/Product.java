package com.esc.productManager;

public class Product {

	int number;
	String name;
	String priceNow;
	String priceBeforeOne;
	String priceBeforeTwo;
	String priceBeforeThree;
	String priceBeforeFour;
	String priceBeforeFive;
	String priceBeforeSix;
	String score;
	String description;
	String manufacturer;
	String imgURL;
	String type;
	String x;
	String y;

	public Product(int number, String name, String price_now,
			String priceBeforeOne, String priceBeforeTwo,
			String priceBeforeThree, String priceBeforeFour,
			String priceBeforeFive, String priceBeforeSix, String score,
			String description, String manufacturer, String imgURL,
			String type, String x, String y) {
		// TODO Auto-generated constructor stub

		this.number = number;
		this.name = name;
		this.priceNow = price_now;
		this.priceBeforeOne = priceBeforeOne;
		this.priceBeforeTwo = priceBeforeTwo;
		this.priceBeforeThree = priceBeforeThree;
		this.priceBeforeFour = priceBeforeFour;
		this.priceBeforeFive = priceBeforeFive;
		this.priceBeforeSix = priceBeforeSix;
		this.score = score;
		this.description = description;
		this.manufacturer = manufacturer;
		this.imgURL = imgURL;
		this.type = type;
		this.x = x;
		this.y = y;
	}

	public void setNumber(int number) {
		this.number = number;
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

	public void setPriceBeforeFour(String priceBeforeFour) {
		this.priceBeforeFour = priceBeforeFour;
	}

	public void setPriceBeforeFive(String priceBeforeFive) {
		this.priceBeforeFive = priceBeforeFive;
	}

	public void setPriceBeforeSix(String price_before_six) {
		this.priceBeforeSix = price_before_six;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setLocation(String x, String y) {
		this.x = x;
		this.y = y;
	}

	public int getNumber() {
		return number;
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

	public String getPriceBeforeFour() {
		return priceBeforeFour;
	}

	public String getPriceBeforeFive() {
		return priceBeforeFive;
	}

	public String getPriceBeforeSix() {
		return priceBeforeSix;
	}

	public String getScore() {
		return score;
	}

	public String getDescription() {
		return this.description;
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public String getImgURL() {
		return this.imgURL;
	}

	public String getType() {
		return this.type;
	}

	public String getX() {
		return this.x;
	}

	public String getY() {
		return this.y;
	}

}
