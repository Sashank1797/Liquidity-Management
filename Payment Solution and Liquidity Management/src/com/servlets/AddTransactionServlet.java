package com.servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserTransactionDetailsDAO;
import com.logic.UserTransactionDetailsDAOImpl;
import com.pojo.UserTransactionDetails;

/**
 * Servlet implementation class AddTransactionServlet
 */
@WebServlet("/recordtransaction")
public class AddTransactionServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		long transactionID=Long.parseLong(request.getParameter("transactionID"));
		long userAccountNo=Long.parseLong(request.getParameter("userAccountNo"));
		long counterpartyAccountNo=Long.parseLong(request.getParameter("counterpartyAccountNo"));
		//Date date=Date()request.getParameter("date");
		Date date = null;
		String currency=request.getParameter("currency");
		double amount=Double.parseDouble(request.getParameter("amount"));
		UserTransactionDetailsDAO transaction=new UserTransactionDetailsDAOImpl();
		boolean row=transaction.addTransaction(transactionID, userAccountNo, counterpartyAccountNo, date, amount, currency);
		if(row) {
			RequestDispatcher dispatcher=request.getRequestDispatcher(".jsp");//front end
			dispatcher.forward(request, response);
		}
		else {
			RequestDispatcher dispatcher=request.getRequestDispatcher(".jsp");//front end
			dispatcher.forward(request, response);
		}
	}

	

}
