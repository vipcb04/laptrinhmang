package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bo.UserBO;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/login")
public class loginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBO user = new UserBO();
	
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String action = request.getParameter("action");
		if(action != null)
			
		{
			HttpSession session = request.getSession(false); 
			if (session != null) {
			    session.invalidate(); 
			    response.sendRedirect("login.jsp"); 
			    return;
			}
		}
		if(user.checkUser(username, password)) {
			HttpSession session = request.getSession();
			try {
				session.setAttribute("userid", user.getIDbyUsername(username));
				System.out.print("userid:"+user.getIDbyUsername(username));
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("index.jsp");	
		}
		else
			
		{
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
