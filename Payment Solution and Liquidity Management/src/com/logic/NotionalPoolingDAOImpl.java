package com.logic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Scanner;

import com.database.DatabaseConnection;

public class NotionalPoolingDAOImpl {

	public double poolingFunction() {
		DatabaseConnection conObj=new DatabaseConnection();
		double netAmount=0.0d;
		try {
			int curr_id=0;
		    Date date=new Date(23,06,1997);
		    String query="select id from rates";
			PreparedStatement ps=conObj.openConnection().prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				if(rs.getInt(1)>curr_id) {
					curr_id=rs.getInt(1);
				}
			}
		 ps=conObj.openConnection().prepareStatement(query);
		 System.out.println("Current id: "+curr_id);
		 query="Select * from rates where id=?";
		 ps=conObj.openConnection().prepareStatement(query);
		 ps.setInt(1, curr_id);
		 rs=ps.executeQuery();
		 while(rs.next()) {
			//double EUR_inflow=0.0d,USD_inflow=0.0d,GBP_inflow=0.0d;
			//double EUR_outflow=0.0d,USD_outflow=0.0d,GBP_outflow=0.0d;
			
			double EUR_balance=0.0d,USD_balance=0.0d,GBP_balance=0.0d;
			
			//double EUR_borrow_interesrRate=0.3d,USD_borrow_interesrRate=0.9d,GBP_borrow_interesrRate=1.0d;
			double EUR_borrow_interesrRate=0.3d,USD_borrow_interesrRate=0.9d,GBP_borrow_interesrRate=1.15d;
			
			//double EUR_lend_interesrRate=0.20d,USD_lend_interesrRate=0.80d,GBP_lend_interesrRate=0.85d;
			double EUR_lend_interesrRate=0.20d,USD_lend_interesrRate=0.75d,GBP_lend_interesrRate=0.85d;
			
			//double EUR_USD_bid_rate=1.12d,EUR_USD_ask_rate=1.22d;
			double EUR_USD_bid_rate=1.12d,EUR_USD_ask_rate=1.15d;
			
			//double EUR_GBP_bid_rate=0.0d,EUR_GBP_ask_rate=0.0d;
			double EUR_GBP_bid_rate=0.0d,EUR_GBP_ask_rate=0.0d;
			
			//double GBP_USD_bid_rate=1.3d;
			//double GBP_USD_ask_rate=1.4d;
			
			double GBP_USD_bid_rate=1.23d;
			double GBP_USD_ask_rate=1.39d;
			
			// EUR/GBP bid and ask calculation
			
			EUR_GBP_bid_rate= EUR_USD_bid_rate/GBP_USD_ask_rate;
			EUR_GBP_ask_rate=EUR_USD_ask_rate/GBP_USD_bid_rate;
			System.out.println("EUR/GBP bid:"+EUR_GBP_bid_rate);
			System.out.println("EUR/GBP ask:"+EUR_GBP_ask_rate);
//			
			Scanner scan=new Scanner(System.in) ;
			
			System.out.println("Enter balance in EUR:");
			EUR_balance=scan.nextDouble();
			System.out.println("Enter balance in USD:");
			USD_balance=scan.nextDouble();
			System.out.println("Enter balance in GPB:");
			GBP_balance=scan.nextDouble();
			
			//  calculate total balance in EUR
			
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
			
		//  calculate total balance in USD
			
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
			
			//  calculate total balance in GBP
				
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
				
				System.out.println("EUR total balance:"+total_EUR_balance);
				System.out.println("USD total balance:"+total_USD_balance);
				System.out.println("GBP total balance:"+total_GBP_balance);
				
				// Overnight rate calculation
				
				double EUR_overNightRate=0.0d,USD_overNightRate=0.0d,GBP_overNightRate=0.0d;
				
				if(total_EUR_balance>0) {
					EUR_overNightRate=(total_EUR_balance*EUR_lend_interesrRate*0.01)/365;
				}
				else {
					EUR_overNightRate=(total_EUR_balance*EUR_borrow_interesrRate*0.01)/365;
				}
				if(total_USD_balance>0) {
					USD_overNightRate=(total_USD_balance*USD_lend_interesrRate*0.01)/365;
				}
				else {
					USD_overNightRate=(total_USD_balance*USD_borrow_interesrRate*0.01)/365;
				}
				if(total_GBP_balance>0) {
					GBP_overNightRate=(total_GBP_balance*GBP_lend_interesrRate*0.01)/365;
				}
				else {
					GBP_overNightRate=(total_GBP_balance*GBP_borrow_interesrRate*0.01)/365;
				}
				System.out.println();
				System.out.println("EUR Over-night Rate:"+EUR_overNightRate);
				System.out.println("USD Over-night Rate:"+USD_overNightRate);
				System.out.println("GBP Over-night Rate:"+GBP_overNightRate);
				
				// converting over-night rates into single base currency i.e. USD
				
				double EUR_to_USD_ONR=0.0d,GBP_to_USD_ONR=0.0d;
				if(EUR_overNightRate>0) {
					EUR_to_USD_ONR=EUR_overNightRate*EUR_USD_bid_rate;
				}
				else {
					EUR_to_USD_ONR=EUR_overNightRate*EUR_USD_ask_rate;
				}
				if(GBP_overNightRate>0) {
					GBP_to_USD_ONR=GBP_overNightRate*GBP_USD_bid_rate;
				}
				else {
					GBP_to_USD_ONR=GBP_overNightRate*GBP_USD_ask_rate;
				}
		 
				System.out.println();
				System.out.println("Over-night rates in USD:\n1. EUR :"+EUR_to_USD_ONR+" USD");
				System.out.println("2. USD :"+USD_overNightRate);
				System.out.println("3. GBP :"+GBP_to_USD_ONR+" USD");
				System.out.println();
				if(total_EUR_balance>0 && total_GBP_balance>0 && total_USD_balance>0) {
					if(EUR_to_USD_ONR>USD_overNightRate) {
						if(EUR_to_USD_ONR>GBP_to_USD_ONR) {
							System.out.println("You can lend in EUR");
						}
						else
							System.out.println("You can lend in GBP");
					}
					else {
						if(USD_overNightRate>GBP_to_USD_ONR) {
							System.out.println("You can lend in USD");
						}
						else
							System.out.println("You can lend in GBP");
					}
				}
				else {
					if(EUR_to_USD_ONR>USD_overNightRate) {
						if(EUR_to_USD_ONR>GBP_to_USD_ONR) {
							System.out.println("You can borrow in EUR");
						}
						else
							System.out.println("You can borrow in GBP");
					}
					else {
						if(USD_overNightRate>GBP_to_USD_ONR) {
							System.out.println("You can borrow in USD");
						}
						else
							System.out.println("You can borrow in GBP");
					}
				}
		 }
				
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
			return netAmount;
	}
}

