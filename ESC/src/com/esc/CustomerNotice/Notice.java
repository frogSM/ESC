package com.esc.CustomerNotice;

import java.util.ArrayList;

public class Notice {
	
	String number;
	String logo;
	String title;
	String date;
	String content;

	public Notice( String number, String logo, String title, String date, String content) {
		this.number = number;
		this.logo = logo;
		this.title = title;
		this.date = date;
		this.content = content;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public void setContents(String content) {
		this.content = content;
	}

	public String getNumber() {
		return this.number;
	}
	
	public String getLogo( ) { 
		return this.logo;
	}
	
	public String getTitle( ) {
		return this.title;
	}
	
	public String getDate( ) { 
		return this.date;
	}
	
	public String getContent( ) {
		return this.content;
	}
	 

}
