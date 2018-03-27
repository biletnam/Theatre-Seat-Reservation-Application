package onlineTheatreManagementAndTicketPurchasingWebApp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import onlineTheatreManagementAndTicketPurchasingWebApp.DB.UsersDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Users;

/**
 * Servlet implementation class ChangePassword
 */
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();	
		
		int userId = (int) session.getAttribute("userId");
		
		String currentPassword = request.getParameter("currentPassword");	
		String newPassword = request.getParameter("newPassword");
		String retypePassword = request.getParameter("retypePassword");
		
		UsersDB aUserDB = new UsersDB();
		Users aUser = new Users();
		
		aUser = aUserDB.getUsersByUserId(userId);
		String saltValue = aUser.getSaltValue();
		//if code equals 0, no errors
		//if code equals 1, new password not the same
		//if code equals 2, current password does not match with database
		int code = 0;
		try {
			if(aUser.getHashedAndSaltedPassword().trim().equals(Users.hashAndSaltPassword(currentPassword, saltValue))){
				if(newPassword.trim().equals(retypePassword)){
					aUserDB.updatePassword(userId, newPassword);
				} else {
					code = 1;
				}
			} else {
				code = 2;
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter(); 
		out.println(code);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
