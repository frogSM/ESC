package com.esc.CustomerNotice;

import java.util.ArrayList;

public class Notice {
	
	String number;
	String logo;
	String title;
	String date;
	ArrayList<String> contents;
	
	
	public Notice( String number, String logo, String title, String date, ArrayList<String> contents) {
		this.number = number;
		this.logo = logo;
		this.title = title;
		this.date = date;
		this.contents = contents;
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
	
	public void setContents(Object contents) {
		this.contents = (ArrayList<String>) this.contents;
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
	
	public ArrayList<String> getContent( ) {
		return this.contents;
	}
	 

}
