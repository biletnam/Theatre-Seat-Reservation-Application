package onlineTheatreManagementAndTicketPurchasingWebApp.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import onlineTheatreManagementAndTicketPurchasingWebApp.DB.MovieShowingDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.DB.TheatresDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Showroom;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Theatres;

/**
 * Servlet implementation class ViewShowrooms
 */
public class ViewShowrooms extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewShowrooms() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String TheatreID = request.getParameter("number");
		int TheatreIDNumber = Integer.parseInt(TheatreID);
		
		MovieShowingDB MovieShowRoomList = new MovieShowingDB();
		ArrayList<Showroom> ViewAllShowRooms = new ArrayList<Showroom>();
		ViewAllShowRooms =  MovieShowRoomList.getShowRoomList(TheatreIDNumber);
		session.setAttribute("viewShowRoom", ViewAllShowRooms);
		RequestDispatcher dispatcher =
			      request.getRequestDispatcher("ViewShowrooms.jsp");
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
