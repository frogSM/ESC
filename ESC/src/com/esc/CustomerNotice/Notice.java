package com.esc.CustomerNotice;

import java.util.ArrayList;

public class Notice {
	
	private String number;
	private String logo;
	private String title;
	private String date;
	private ArrayList<String> contents;
	
	
	public Notice( String number, String logo, String title, String date, ArrayList<String> contents  ) {
		this.number = number;
		this.logo = logo;
		this.title = title;
		this.date = date;
		this.contents = contents;
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
	
	public String GetNumber( ) { 
		return this.number;
	}
	
	public ArrayList<String> GetContent( ) {
		return this.contents;
	}
	 

}
