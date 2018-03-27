package onlineTheatreManagementAndTicketPurchasingWebApp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import onlineTheatreManagementAndTicketPurchasingWebApp.DB.MovieShowingDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.DB.MoviesDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.DB.OrdersDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.MovieShowing;

/**
 * Servlet implementation class CancelShowing
 */
public class CancelShowing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelShowing() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MovieShowingDB aDB = new MovieShowingDB();
		MoviesDB  aMovie = new MoviesDB();
		String movieName = request.getParameter("movieTitle");
		int movieID = aMovie.getMovieIdByName(movieName);		
		int ShowroomID = Integer.parseInt(request.getParameter("showroomID"));
		
		String startTime = request.getParameter("startTime");
		MovieShowing aMovieShowing = new MovieShowing();
		aMovieShowing.setStartTime(startTime);
		
		int movieShowingId = aDB.getMovieShowingId(aMovieShowing, movieID, ShowroomID);
		OrdersDB aOrdersDB = new OrdersDB();
		
		aOrdersDB.refund(movieShowingId);
		aDB.DeleteShowtime(ShowroomID, movieID , startTime);
		
		RequestDispatcher dispatcher =
			      request.getRequestDispatcher("CancellationConfirmationShowtime.jsp");
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
