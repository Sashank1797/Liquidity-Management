package com.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserLoginDAO;
import com.logic.UserLoginDAOImpl;

/**
 * Servlet implementation class AuthenticateUserServlet
 */
@WebServlet("/login")
public class AuthenticateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserLoginDAO dao = new UserLoginDAOImpl();
		int userId = dao.authenticateUser(username, password);	//Use for all other APIs
		if(userId>0) {
			HttpSession httpSession = request.getSession();
//			httpSession.setAttribute("username", name);
			//ALL APIs here fetch
			RequestDispatcher dispatcher = request.getRequestDispatcher("");	//Front end defined - JSON response or page
			dispatcher.forward(request, response);
		}
		else {
			String message = "Error";
			request.setAttribute("message", message);			
			RequestDispatcher dispatcher = request.getRequestDispatcher("");	//Front end defined
			dispatcher.forward(request, response);
		}
//		doGet(request, response);
	}
}
