package com.esc.MainAdvertise;

/** ESC∏Ù Frontø° ∫∏ø©¡÷±‚ ¿ß«— ±§∞ÌªÛ«∞ ∞¥√º(Product ∞¥√º ¿ÃøÎ X) **/
public class AdProduct {

	private String imgURL;
	private String title;
	private String price;

	public AdProduct(String imgURL, String title, String price) {
		// TODO Auto-generated constructor stub
		this.imgURL = imgURL;
		this.title = title;
		this.price = price;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getImgURL() {
		return this.imgURL;
	}

	public String getTitle() {
		return this.title;
	}

	public String getPrice() {
		return this.price;
	}
}
