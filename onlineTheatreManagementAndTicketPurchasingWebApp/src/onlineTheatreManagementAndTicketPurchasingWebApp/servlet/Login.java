package onlineTheatreManagementAndTicketPurchasingWebApp.servlet;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineTheatreManagementAndTicketPurchasingWebApp.DB.UsersDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Users;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		String referer = request.getHeader("referer");
		
		if(referer.contains("Login")){
			
			ServletContext context = getServletContext();
	
			synchronized(this) {
				if ((context.getAttribute("numAccess") == null)) {
					int numAccess = 1;
					context.setAttribute("numAccess", numAccess);
				}else{
					int numAccess = (int) context.getAttribute("numAccess");
					numAccess++;
					context.setAttribute("numAccess", numAccess);
				}
			}
	
			UsersDB user = new UsersDB();
			Users aUser = new Users(userName, password);
			user.validateUser(aUser, request, response);
			
		} else {
			
			UsersDB user = new UsersDB();
			Users aUser = new Users(userName, password);
			Users aOriginalUser = user.getUsersByUserId((int)request.getSession().getAttribute("userId"));
			int code = user.reauthenticateUser(aOriginalUser, aUser);
			
			if(code == 1){
				request.getSession().invalidate();	
			}
			
			PrintWriter out = response.getWriter(); 
			out.println(code);
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
