package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	public Connection openConnection() {
		Connection connection=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver loaded successfully");
			 connection= DriverManager.getConnection("group7@//localhost:1521:xe", "group7", "password");
			System.out.println("Connection obtained");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}	
}
