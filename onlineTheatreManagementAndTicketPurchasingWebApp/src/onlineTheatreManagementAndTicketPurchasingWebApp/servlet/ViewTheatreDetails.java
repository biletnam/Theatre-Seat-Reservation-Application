package onlineTheatreManagementAndTicketPurchasingWebApp.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import onlineTheatreManagementAndTicketPurchasingWebApp.DB.TheatresDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.DB.UsersDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Theatres;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Users;

/**
 * Servlet implementation class ViewTheatreDetails
 */
public class ViewTheatreDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewTheatreDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int ownerID = (int) session.getAttribute("ownerId");
		
		//int i = 1;
		TheatresDB aTheatresDB= new TheatresDB();
		ArrayList<Theatres> TheatresList = new ArrayList<Theatres>();
		TheatresList = aTheatresDB.fetchTheatreForOwner(ownerID);
		session.setAttribute("TheatresList", TheatresList);
		RequestDispatcher dispatcher =
			      request.getRequestDispatcher("ViewTheatreBuildingDetails.jsp");
			    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
