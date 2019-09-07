package com.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserPersonalDetails;
import com.dao.UserPersonalDetailsDAO;
import com.dao.UserPersonalDetailsDAOImpl;
import com.dao.UserLogin;
import com.dao.UserLoginDAO;
import com.dao.UserLoginDAOImpl;

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
		String name=req.getParameter("name");
		String contact=req.getParameter("contact");
		String emailId=req.getParameter("emailId");
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		
		UserPersonalDetailsDAO daoUser = new UserPersonalDetailsDAOImpl();
		UserLoginDAO daoLogin = new UserLoginDAOImpl();
		
		int rowsUsers = daoUser.addUser(new UserPersonalDetails(name, contact, emailId));	//What if only one gets added
		int rowsLogin = daoLogin.addUserCredentials(new UserLogin(username, password));		//Also login has dependency on users
		if(rowsUsers>0 && rowsLogin>0) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("");	//Front end defined
			dispatcher.forward(req, resp);
		}
		else {
			String message = "Error";
			req.setAttribute("message", message);			
			RequestDispatcher dispatcher = req.getRequestDispatcher("");	//Front end defined
			dispatcher.forward(req, resp);
		}
//		response.getWriter().append("Served at: ").append(request.getContextPath());  //??
	}
}
