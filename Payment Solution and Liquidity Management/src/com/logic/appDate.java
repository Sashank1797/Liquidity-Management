package com.logic;
import java.time.LocalDate;

import com.dao.RandomRateGeneratorDAO;

public class appDate {
	
	public static String date="2019-09-11";
	
	public String setIncrementDate(String date1) {
		if(date1==null) {
			date="2019-09-11";
		}
		else {
	    date= LocalDate.parse(date).plusDays(1)
	    						.toString();
		}
		endOfDay(date);
	    return date;
	}

	public static String getDate() {
		return date;
	}

	public static void setDate(String date) {
		appDate.date = date;
	}
	
	public void endOfDay(String current_date) {
		
		//********Insert rates in table for current date*****
		
		RandomRateGeneratorDAO randomRate=new RandomRateGeneratorDAOImpl();
		randomRate.populateRatesInDB(current_date);
		
		// 
	}
}
