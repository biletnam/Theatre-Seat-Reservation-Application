package onlineTheatreManagementAndTicketPurchasingWebApp.servlet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineTheatreManagementAndTicketPurchasingWebApp.model.EmailUtility;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Users;
import onlineTheatreManagementAndTicketPurchasingWebApp.DB.UsersDB;
/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String host;
	private String port;
	private String username;
	private String pass;

	public void init() {
		// reads SMTP server setting from web.xml file
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		username = context.getInitParameter("user");
		pass = context.getInitParameter("pass");
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String hashedAndSaltedPassword = "";
		String salt = Users.generateSalt();
		try{
			hashedAndSaltedPassword = Users.hashAndSaltPassword(password, salt);
		} catch (NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String postalCode = request.getParameter("zipCode");
		String phoneNumber = request.getParameter("phoneNumber");
		
		UsersDB user = new UsersDB();

		//First check whether the user already exists via methods from UsersDB class
		if(user.userExist(userName)){
			response.sendRedirect("Registration.jsp");
		} else {
			
			String recipient = request.getParameter("emailAddress");
			
			Users aUser = new Users(firstName, lastName, userName, hashedAndSaltedPassword, salt, street, city, state, postalCode, recipient, phoneNumber);
			
			if(aUser.isValidEmail(recipient) && aUser.isValidPhone(phoneNumber) && aUser.isValidZip(postalCode)){
				
				
				// Register the Users object
				user.registerUser(aUser);
				
				String subject = "[DuTosTicketPurchasingApp] Registration is successful";
				String content = "Hi " + userName + "!\n"
						+ "Thank you for using our service! We will keep on improving to satisfy customers like you!\n"
						+ "Username: " + userName + "\n" 
						+ "Please do respond to us if you have any questions. We're happy to help!";

				String resultMessage = "";

				try {
					EmailUtility.sendEmail(host, port, username, pass, recipient, subject,
							content);
					resultMessage = "Registration is complete!";
				} catch (Exception ex) {
					ex.printStackTrace();
					resultMessage = "There were an error: " + ex.getMessage();
				} finally {
					request.setAttribute("Message", resultMessage);
					RequestDispatcher dispatcher =
							request.getRequestDispatcher("RegistrationConfirmation.jsp");
					dispatcher.forward(request, response);
				}
			} else {
				response.sendRedirect("Registration.jsp");
			}
		} 
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
