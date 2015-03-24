package com.esc.CustomerNotice;

import java.util.ArrayList;

public class Notice {
	
	String number;
	String logo;
	String title;
	String date;
	ArrayList<String> content;
	
	
	public Notice( String number, String logo, String title, String date, ArrayList<String> content ) {
		this.number = number;
		this.logo = logo;
		this.title = title;
		this.date = date;
		this.content = content;
	}
	
	public String GetTitle( ) {
		return this.title;
	}
	
	public String GetLogo( ) { 
		return this.logo;
	}
	
	public String GetDate( ) { 
		return this.date;
	}
	
	public ArrayList<String> GetContent( ) { 
		return this.content;
	}
	
	public String GetNumber( ) { 
		return this.number;
	}
	

}
