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
import onlineTheatreManagementAndTicketPurchasingWebApp.model.MovieShowing;

/**
 * Servlet implementation class CancelPassing
 */
public class CancelPassing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelPassing() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String MoviePlaying = request.getParameter("movieTitle");
		String StartTime = request.getParameter("startTime");
		String EndTime = request.getParameter("endTime");
		String TicketPurchased = request.getParameter("numberPurchased");
		int ShowroomID = Integer.parseInt(request.getParameter("showroomID"));
		
		MovieShowing aMovieShowing = new MovieShowing();
		MoviesDB aMovieDB = new MoviesDB();
		
		aMovieShowing.setaMovie(aMovieDB.GetMovieInDetails(MoviePlaying));
		aMovieShowing.setStartTime(StartTime);
		aMovieShowing.setEndTime(EndTime);
		aMovieShowing.setNumberPurchased(Integer.parseInt(TicketPurchased));
		aMovieShowing.setShowroomID(ShowroomID);
		
		session.setAttribute("aMovieShowing", aMovieShowing);
		
		RequestDispatcher dispatcher =
			      request.getRequestDispatcher("CancelShowing.jsp");
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
