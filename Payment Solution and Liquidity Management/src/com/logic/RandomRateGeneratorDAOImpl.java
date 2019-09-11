package com.logic;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.dao.RandomRateGeneratorDAO;
import com.database.DatabaseConnection;

public class RandomRateGeneratorDAOImpl implements RandomRateGeneratorDAO {

	public boolean populateRatesInDB() {
		
		boolean ratespopulated=false;
		// Generate rates
		
		    double eur_usd_bid=(getRandomDoubleBetweenRange(1.1, 1.2));
	        
	        double eur_usd_ask=(Math.round(eur_usd_bid*100+2.0))/100.0;
	      
	        double gbp_usd_bid=getRandomDoubleBetweenRange(1.2, 1.3);
	       
	        double gbp_usd_ask=(Math.round(gbp_usd_bid*100+2))/100.0;
	       
	        double eur_gbp_bid=Math.floor((eur_usd_bid/gbp_usd_ask)*100)/100;
	       
	        double eur_gbp_ask=Math.floor((eur_usd_ask/gbp_usd_bid)*100)/100;
	       
	        double usd_borrow=getRandomDoubleBetweenRange(-1, 1);
	        double usd_lend=(Math.round(usd_borrow*100-2.0))/100.0;
	        double eur_borrow=getRandomDoubleBetweenRange(-1, 1);
	        double eur_lend=Math.floor((eur_borrow-0.02)*100)/100;
	        double gbp_borrow=getRandomDoubleBetweenRange(-1, 1);
	        double gbp_lend=Math.floor((gbp_borrow-0.02)*100)/100;
	             
	        
	        //*************  Inserting into DB***************
	        
	    	DatabaseConnection conObj=new DatabaseConnection();
	    	
	        String RATES_INSERT="insert into rates values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	        
	        int rows_inserted=0;
	        
	        try {
				PreparedStatement ps=conObj.openConnection().prepareStatement(RATES_INSERT);
				
				ps.setInt(1, 1);
				ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));
				ps.setDouble(3, usd_lend);
				ps.setDouble(4, usd_borrow);
				ps.setDouble(5, gbp_lend);
				ps.setDouble(6, gbp_borrow);
				ps.setDouble(7, eur_lend);
				ps.setDouble(8, eur_borrow);
				ps.setDouble(9, gbp_usd_bid);
				ps.setDouble(10, gbp_usd_ask);
				ps.setDouble(11, eur_usd_bid);
				ps.setDouble(12, eur_usd_ask);
				ps.setDouble(13, eur_gbp_bid);
				ps.setDouble(14, eur_gbp_ask);
				
				rows_inserted=ps.executeUpdate();
				System.out.println("Rows="+rows_inserted);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       return ratespopulated; 
	}
	public  double getRandomDoubleBetweenRange(double min, double max){
	    //double x = ((Math.random()*(max-min))+min);
	   double x = (Math.floor(((Math.random()*(max-min))+min)*100))/100;
	    return x;
	}
}
