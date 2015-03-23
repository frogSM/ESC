package com.esc.CustomerNotice;

import java.util.ArrayList;

public class Notice {
	
	String title;
	String logo;
	String date;
	String number;
	ArrayList<String> content;
	
	public Notice( String title, String logo, String date, String number, ArrayList<String> content ) {
		this.title = title;
		this.logo = logo;
		this.date = date;
		this.number = number;
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
