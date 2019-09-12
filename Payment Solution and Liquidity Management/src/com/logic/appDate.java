package com.logic;
import java.time.LocalDate;

public class appDate {
	
	public static String date="1";
	
	public String setIncrementDate(String date) {
		if(date==null) {
			date="2019-09-11";
		}
		else {
	    date= LocalDate.parse(date).plusDays(1)
	    						.toString();
		}
	    return date;
	}

	public static String getDate() {
		return date;
	}

	public static void setDate(String date) {
		appDate.date = date;
	}
}