package com.logic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONObject;

import com.dao.NotionalPoolingDAO;
import com.dao.RandomRateGeneratorDAO;
import com.database.DatabaseConnection;

import jdk.nashorn.api.scripting.JSObject;

public class NotionalPoolingDAOImpl implements NotionalPoolingDAO {

	public JSONObject poolingFunction() {
		
		// ************************Variable declarations*******************
		JSONObject response = new JSONObject();
		double netAmount=0.0d;
		double EUR_balance=0.0d,USD_balance=0.0d,GBP_balance=0.0d;
		
		double EUR_borrow_interestRate=0.3d,USD_borrow_interestRate=0.9d,GBP_borrow_interestRate=1.15d;
		double EUR_lend_interestRate=0.20d,USD_lend_interestRate=0.75d,GBP_lend_interestRate=0.85d;
		
		double EUR_USD_bid_rate=1.12d,EUR_USD_ask_rate=1.15d;
		double EUR_GBP_bid_rate=0.0d,EUR_GBP_ask_rate=0.0d;	
		double GBP_USD_bid_rate=1.23d;
		double GBP_USD_ask_rate=1.39d;
		
		//*****************Getting new date******************
		
		
		
		//******************get net balance in all three currency****************
		JSONObject net_balance = new JSONObject();
        List<Double> netBalance=calculateNetBalance();
        
        EUR_balance=netBalance.get(0);
        EUR_balance=(Math.round(EUR_balance*10000))/10000;
        USD_balance=netBalance.get(1);
        USD_balance=(Math.round(USD_balance*10000))/10000;
        GBP_balance=netBalance.get(2);
        GBP_balance=(Math.round(GBP_balance*10000))/10000;
        
        net_balance.put("usd", USD_balance);
        net_balance.put("eur", EUR_balance);
        net_balance.put("gbp", GBP_balance);
		System.out.println(EUR_balance);
		
        //***********************Get all three FX-Rates and interest rates from rates DB*****************
        
		RandomRateGeneratorDAO generatorObj =new RandomRateGeneratorDAOImpl();
        List<Double> rates=generatorObj.getRatesFromDB("09-SEP-19");
        JSONObject fx_rates = new JSONObject();
        JSONObject interest_rates = new JSONObject();
        
        USD_lend_interestRate=rates.get(0);
        USD_borrow_interestRate=rates.get(1);
        GBP_lend_interestRate=rates.get(2);
        GBP_borrow_interestRate=rates.get(3);
        EUR_lend_interestRate=rates.get(4);
        EUR_borrow_interestRate=rates.get(5);
        GBP_USD_bid_rate=rates.get(6);
		GBP_USD_ask_rate=rates.get(7);
        EUR_USD_bid_rate=rates.get(8);
        EUR_USD_ask_rate=rates.get(9);
        EUR_GBP_bid_rate=rates.get(10);
        EUR_GBP_ask_rate=rates.get(11);
        
        interest_rates.put("eur_lend", EUR_lend_interestRate);
        interest_rates.put("eur_borrow", EUR_borrow_interestRate);
        interest_rates.put("usd_lend", USD_lend_interestRate);
        interest_rates.put("usd_borrow", USD_borrow_interestRate);
        interest_rates.put("gbp_lend", GBP_lend_interestRate);
        interest_rates.put("gbp_borrow", GBP_borrow_interestRate);
        
        fx_rates.put("gbp_usd_bid", GBP_USD_bid_rate);
        fx_rates.put("gbp_usd_ask", GBP_USD_ask_rate);
        fx_rates.put("eur_usd_bid", EUR_USD_bid_rate);
        fx_rates.put("eur_usd_ask", EUR_USD_ask_rate);
        fx_rates.put("eur_gbp_bid", EUR_GBP_bid_rate);
        fx_rates.put("eur_gbp_ask", EUR_GBP_ask_rate);
		
		//System.out.println("EUR/GBP bid:"+EUR_GBP_bid_rate);
		//System.out.println("EUR/GBP ask:"+EUR_GBP_ask_rate);
		
//		Scanner scan=new Scanner(System.in) ;
//		
//		System.out.println("Enter balance in EUR:");
//		EUR_balance=scan.nextDouble();
//		System.out.println("Enter balance in USD:");
//		USD_balance=scan.nextDouble();
//		System.out.println("Enter balance in GPB:");
//		GBP_balance=scan.nextDouble();
		
		//*************calculate pooled balance in EUR******************
		
		double USD_to_EUR=0.0d,GBP_to_EUR=0.0D,total_EUR_balance=0.0d;
		
		if(USD_balance>0) {
			USD_to_EUR= USD_balance/EUR_USD_ask_rate;
		}
		else {
			USD_to_EUR= USD_balance/EUR_USD_bid_rate;
		}
		
		if(GBP_balance>0) {
			GBP_to_EUR= GBP_balance/EUR_GBP_ask_rate;
		}
		else {
			GBP_to_EUR= GBP_balance/EUR_GBP_bid_rate;
		}
		
		total_EUR_balance=EUR_balance+USD_to_EUR+GBP_to_EUR;
		
	//*************calculate pooled balance in USD******************
			JSONObject pooled_balance = new JSONObject();

			double EUR_to_USD=0.0d,GBP_to_USD=0.0D,total_USD_balance=0.0d;
			
			if(EUR_balance>0) {
				EUR_to_USD= EUR_balance*EUR_USD_bid_rate;
			}
			else {
				EUR_to_USD= EUR_balance*EUR_USD_ask_rate;
			}
			
			if(GBP_balance>0) {
				GBP_to_USD= GBP_balance* GBP_USD_bid_rate;
			}
			else {
				GBP_to_USD= GBP_balance* GBP_USD_ask_rate;
			}
			
			total_USD_balance=USD_balance+EUR_to_USD+GBP_to_USD;
			
		
		//*************calculate pooled balance in GBP******************
			
			double EUR_to_GBP=0.0d,USD_to_GBP=0.0D,total_GBP_balance=0.0d;
			
			if(EUR_balance>0) {
				EUR_to_GBP=EUR_balance * EUR_GBP_bid_rate;
			}
			else {
				EUR_to_GBP=EUR_balance*EUR_GBP_ask_rate;
			}
			
			if(USD_balance>0) {
				USD_to_GBP=USD_balance/GBP_USD_ask_rate;
			}
			else {
				USD_to_GBP=USD_balance/GBP_USD_bid_rate;
			}
			
			total_GBP_balance=GBP_balance+USD_to_GBP+EUR_to_GBP;
			
			 //*********** CHANGE TO 4 DECIMAL PLACE*******************
	        total_EUR_balance=(Math.round(total_EUR_balance*10000))/10000;
	        total_USD_balance=(Math.round(total_USD_balance*10000))/10000;
	        total_GBP_balance=(Math.round(total_GBP_balance*10000))/10000;
	        
			System.out.println("EUR total balance:"+total_EUR_balance);
			System.out.println("USD total balance:"+total_USD_balance);
			System.out.println("GBP total balance:"+total_GBP_balance);
			
	        pooled_balance.put("usd", total_USD_balance);
	        pooled_balance.put("eur", total_EUR_balance);
	        pooled_balance.put("gbp", total_GBP_balance);
			
			//**************************Overnight interest amount calculation*****************************
			JSONObject interest_amount = new JSONObject();
			
			double EUR_overNightAmount=0.0d,USD_overNightAmount=0.0d,GBP_overNightAmount=0.0d;
			
			if(total_EUR_balance>0) {
				EUR_overNightAmount=(total_EUR_balance*EUR_lend_interestRate*0.01)/365;
			}
			else {
				EUR_overNightAmount=(total_EUR_balance*EUR_borrow_interestRate*0.01)/365;
			}
			if(total_USD_balance>0) {
				USD_overNightAmount=(total_USD_balance*USD_lend_interestRate*0.01)/365;
			}
			else {
				USD_overNightAmount=(total_USD_balance*USD_borrow_interestRate*0.01)/365;
			}
			if(total_GBP_balance>0) {
				GBP_overNightAmount=(total_GBP_balance*GBP_lend_interestRate*0.01)/365;
			}
			else {
				GBP_overNightAmount=(total_GBP_balance*GBP_borrow_interestRate*0.01)/365;
			}
			
			 //*********** CHANGE TO 4 DECIMAL PLACE*******************
	        EUR_overNightAmount=(Math.round(EUR_overNightAmount*10000))/10000;
	        USD_overNightAmount=(Math.round(USD_overNightAmount*10000))/10000;
	        GBP_overNightAmount=(Math.round(GBP_overNightAmount*10000))/10000;
	        
			System.out.println();
			System.out.println("EUR Over-night Rate:"+EUR_overNightAmount);
			System.out.println("USD Over-night Rate:"+USD_overNightAmount);
			System.out.println("GBP Over-night Rate:"+GBP_overNightAmount);
			
			interest_amount.put("usd", USD_overNightAmount);
			interest_amount.put("gbp", GBP_overNightAmount);
			interest_amount.put("eur", EUR_overNightAmount);
			
			//*******************converting over-night rates into single base currency i.e. USD*********************
			JSONObject interest_amount_usd = new JSONObject();
			
			double EUR_to_USD_ONR=0.0d,GBP_to_USD_ONR=0.0d;
			if(EUR_overNightAmount>0) {
				EUR_to_USD_ONR=EUR_overNightAmount*EUR_USD_bid_rate;
			}
			else {
				EUR_to_USD_ONR=EUR_overNightAmount*EUR_USD_ask_rate;
			}
			if(GBP_overNightAmount>0) {
				GBP_to_USD_ONR=GBP_overNightAmount*GBP_USD_bid_rate;
			}
			else {
				GBP_to_USD_ONR=GBP_overNightAmount*GBP_USD_ask_rate;
			}
			
			 //*********** CHANGE TO 4 DECIMAL PLACE*******************
	        EUR_to_USD_ONR=(Math.round(EUR_to_USD_ONR*10000))/10000;
	        GBP_to_USD_ONR=(Math.round(GBP_to_USD_ONR*10000))/10000;
	        
			interest_amount_usd.put("usd", USD_overNightAmount);
			interest_amount_usd.put("gbp", GBP_to_USD_ONR);
			interest_amount_usd.put("eur", EUR_to_USD_ONR);
			
			System.out.println();
			System.out.println("Over-night rates in USD:\n1. EUR :"+EUR_to_USD_ONR+" USD");
			System.out.println("2. USD :"+USD_overNightAmount);
			System.out.println("3. GBP :"+GBP_to_USD_ONR+" USD");
			System.out.println();
			
			String suggestion_type=null, currency_type=null;
			
			if(total_EUR_balance>0 && total_GBP_balance>0 && total_USD_balance>0) {
				if(EUR_to_USD_ONR>USD_overNightAmount) {
					if(EUR_to_USD_ONR>GBP_to_USD_ONR) {
						suggestion_type="LEND";
						currency_type="EUR";
						System.out.println("You can lend in EUR");
					}
					else {
						suggestion_type="LEND";
						currency_type="GBP";
						System.out.println("You can lend in GBP");
					}
						
				}
				else {
					if(USD_overNightAmount>GBP_to_USD_ONR) {
						suggestion_type="LEND";
						currency_type="USD";
						System.out.println("You can lend in USD");
					}
					else {
						suggestion_type="LEND";
						currency_type="GBP";
						System.out.println("You can lend in GBP");
					}
				}
			}
			else {
				if(EUR_to_USD_ONR>USD_overNightAmount) {
					if(EUR_to_USD_ONR>GBP_to_USD_ONR) {
						suggestion_type="BORROW";
						currency_type="EUR";
						System.out.println("You can borrow in EUR");
					}
					else {
						suggestion_type="BORROW";
						currency_type="GBP";
						System.out.println("You can borrow in GBP");
					}
						
				}
				else {
					if(USD_overNightAmount>GBP_to_USD_ONR) {
						suggestion_type="BORROW";
						currency_type="USD";
						System.out.println("You can borrow in USD");
					}
					else {
						suggestion_type="BORROW";
						currency_type="GBP";
						System.out.println("You can borrow in GBP");
					}
						
				}
			}
//			*******************JSON***********************************
			JSONObject data = new JSONObject();
			
			data.put("net_balance", net_balance);
			data.put("pooled_balance", pooled_balance);
			data.put("interest_rates", interest_rates);
			data.put("fx_rates", fx_rates);
			data.put("interest_amount", interest_amount);
			data.put("interest_amount_usd", interest_amount_usd);
			data.put("suggestion_currency", currency_type);
			data.put("suggestion_type", suggestion_type);
			response.put("error", false);
			response.put("message", "success");
			response.put("data", data);
			System.out.println(response);
			return response;
	}

	@Override
	public List<Double> calculateTotalBalance_from_TRANSACTIONS_table(String currency1,String currency2,String currency3) {
		
		List<Double> balance=new ArrayList<>();
		double balance_EUR=0.0d, balance_USD=0.0d, balance_GBP=0.0d;
		
		String GET_BALANCE_by_CURRENCY="SELECT SUM(AMOUNT) FROM TRANSACTIONS WHERE CURRENCY=? ";
	
		
		DatabaseConnection connection= new DatabaseConnection();
		PreparedStatement ps;
		
		try {
			
			// getting total EUR_balance from TRANSACTIONS TABLE
			
			ps=connection.openConnection().prepareStatement(GET_BALANCE_by_CURRENCY);
			ps.setString(1, currency1);
			ResultSet set=ps.executeQuery();
			
			while(set.next())
			{
				balance_EUR=set.getDouble("SUM(AMOUNT)");
				//System.out.println(balance_EUR);
		    }
		
			// getting total USD_balance from TRANSACTIONS TABLE
			
			ps.setString(1, currency2);
			set=ps.executeQuery();
			
			while(set.next())
			{
				balance_USD=set.getDouble("SUM(AMOUNT)");
				//System.out.println(balance_USD);
		    }
			
			// getting total GBP_balance from TRANSACTIONS TABLE
			
			ps.setString(1, currency3);
		    set=ps.executeQuery();
			
			while(set.next())
			{
				balance_GBP=set.getDouble("SUM(AMOUNT)");
				//System.out.println(balance_GBP);
		    }
			
			// PUT BALANCE IN LIST
			
			balance.add(balance_EUR);
			balance.add(balance_USD);
			balance.add(balance_GBP);
			
		} catch (SQLException e) {
		
		    e.printStackTrace();
		}
		return balance;
	}
	
	
	
	@Override
public List<Double> getBalance_from_ACCOUNTS_table(String currency1,String currency2,String currency3){
		
		List<Double> balance=new ArrayList<>();
		double balance_EUR=0.0d, balance_USD=0.0d, balance_GBP=0.0d;
		
		String GET_BALANCE_by_ACCOUNT_TYPE="SELECT balance FROM ACCOUNTS WHERE ACCOUNT_TYPE=? ";
		
			
			DatabaseConnection connection= new DatabaseConnection();
			PreparedStatement ps1;
			
			try {
				
				// getting  EUR_balance from ACCOUNTS TABLE
				
				ps1=connection.openConnection().prepareStatement(GET_BALANCE_by_ACCOUNT_TYPE);
				ps1.setString(1, currency1);
				ResultSet set=ps1.executeQuery();
				
				while(set.next())
				{
					balance_EUR=set.getDouble("BALANCE");
					//System.out.println(balance_EUR);
			    }
			
				// getting  USD_balance from ACCOUNTS TABLE

         		ps1.setString(1, currency2);
                set=ps1.executeQuery();
			
				while(set.next())
					{
						balance_USD=set.getDouble("BALANCE");
						//System.out.println(balance_USD);
				    }
				
				// getting  GBP_balance from ACCOUNTS TABLE
				
				
				ps1.setString(1, currency3);
				set=ps1.executeQuery();
				
				while(set.next())
				{
					balance_GBP=set.getDouble("BALANCE");
					//System.out.println(balance_GBP);
			    }
				
				// PUT BALANCE IN LIST
				
				balance.add(balance_EUR);
				balance.add(balance_USD);
				balance.add(balance_GBP);
				
			} catch (SQLException e) {
			
			    e.printStackTrace();
			}
		return balance;
	}

	@Override
    public List<Double> calculateNetBalance() {
		
		List<Double> netBalance=new ArrayList<>();
		List<Double> transactionBalance=new ArrayList<>();
		List<Double> accountBalance=new ArrayList<>();
		
		transactionBalance=calculateTotalBalance_from_TRANSACTIONS_table("EUR", "USD", "GBP");
		accountBalance=getBalance_from_ACCOUNTS_table("EUR", "USD", "GBP");
		
		for(int i=0;i<transactionBalance.size();i++)
		{
			netBalance.add(transactionBalance.get(i)+accountBalance.get(i));
			//System.out.println(netBalance.get(i));
		}
		
		return netBalance;
	}
}
