package com.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class AuthenticateUserServlet
 */
//@WebServlet("/AuthenticateUser")
@Path("/send")
public class AuthenticateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AuthenticateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Connection getConnection() {
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver loaded successfully");
			//jdbc:data_base:install_server:port/database
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","abcd123");
			System.out.println("Connection obtained");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
    @Path("/sendInputs")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user=request.getParameter("user");
		String pwd=request.getParameter("pwd");
		JSONObject obj = new JSONObject();

	      obj.put("name", "krutika");
	      obj.put("num", new Integer(100));
		Connection conn=getConnection();
		try {
		String query="select * from login";
		PreparedStatement ps=conn.prepareStatement(query);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			System.out.println("User name:"+rs.getString(2));
			if(rs.getString(2).equals(user) && rs.getString(3).equals(pwd)) {
				System.out.println("Correct");
				RequestDispatcher disp=request.getRequestDispatcher("display.jsp");
				request.setAttribute("Object", obj);
				disp.forward(request, response);
				return;
			}
			else {
				RequestDispatcher disp=request.getRequestDispatcher("login.jsp");
				disp.forward(request, response);
			}
		}
		
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
