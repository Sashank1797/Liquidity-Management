package com.logic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dao.RandomRateGeneratorDAO;
import com.database.DatabaseConnection;
import com.pojo.UserTransactionDetails;

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
	        
	        // Getting current date
	        
	       // DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    	//Date date = new Date();
	    	//String date_test=dateFormat.format(date);
	    	//System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
	    	// java.sql.Date date1 = new java.sql.Date(0000-00-00);
	    	// date1.currentTimeMillis();
	    	//System.out.println( new java.sql.Date(System.currentTimeMillis()));
	        
	        
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
	
	@Override
	public List<Double> getRatesFromDB(String date) {
		
		List<Double> rates=new ArrayList<>();
        String GET_RATES_BY_DATE="SELECT * FROM RATES WHERE PRESENT_DATE=? ";
	
		DatabaseConnection connection= new DatabaseConnection();
		PreparedStatement ps;
		
		try {
			
			// getting all FX- Rates and interest rates from RATES TABLE
			
			ps=connection.openConnection().prepareStatement(GET_RATES_BY_DATE);
			ps.setString(1, date);
			ResultSet set=ps.executeQuery();
			
			while(set.next())
			{
				Double usd_lend=set.getDouble("USD_BID");
				Double usd_borrow=set.getDouble("USD_ASK");
				Double gbp_lend=set.getDouble("GBP_BID");
				Double gbp_borrow=set.getDouble("GBP_ASK");
				Double eur_lend=set.getDouble("EUR_BID");
				Double eur_borrow=set.getDouble("EUR_ASK");
				Double gbp_usd_bid=set.getDouble("gbp_usd_bid");
				Double gbp_usd_ask=set.getDouble("gbp_usd_ask");
				Double eur_usd_bid=set.getDouble("EUR_USD_BID");
				Double eur_usd_ask=set.getDouble("EUR_USD_ASK");
				Double eur_gbp_bid=set.getDouble("EUR_GBP_BID");
				Double eur_gbp_ask=set.getDouble("EUR_GBP_ASK");
				//System.out.println(usd_lend);
				rates.add(usd_lend);
				rates.add(usd_borrow);
				rates.add(gbp_lend);
				rates.add(gbp_borrow);
				rates.add(eur_lend);
				rates.add(eur_borrow);
				rates.add(gbp_usd_bid);
				rates.add(gbp_usd_ask);
				rates.add(eur_usd_bid);
				rates.add(eur_usd_ask);
				rates.add(eur_gbp_bid);
				rates.add(eur_gbp_ask);
		    }
			
		} catch (SQLException e) {
		
		    e.printStackTrace();
		}
		return rates;
	}

}
