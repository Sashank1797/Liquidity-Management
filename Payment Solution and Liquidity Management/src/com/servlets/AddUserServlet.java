package com.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserPersonalDetailsDAO;
import com.logic.UserPersonalDetailsDAOImpl;
import com.dao.UserLoginDAO;
import com.logic.UserLoginDAOImpl;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/signup")				//changed from addUser to signup as we should never reveal our servlet name, security risk
public class AddUserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

//    public AddUserServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		String contact=request.getParameter("contact");
		String emailId=request.getParameter("emailId");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		UserPersonalDetailsDAO daoUser = new UserPersonalDetailsDAOImpl();
		UserLoginDAO daoLogin = new UserLoginDAOImpl();
		
		boolean rowsUsers = daoUser.addUser(name, contact, emailId);	//What if only one gets added
		boolean rowsLogin = daoLogin.addUserCredentials(username, password);		//Also login has dependency on users
		if(rowsUsers && rowsLogin) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("");	//Front end defined
			dispatcher.forward(request, response);
		}
		else {
			String message = "Error";
			request.setAttribute("message", message);			
			RequestDispatcher dispatcher = request.getRequestDispatcher("");	//Front end defined
			dispatcher.forward(request, response);
		}
//		response.getWriter().append("Served at: ").append(request.getContextPath());  //??
	}
}
