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
import onlineTheatreManagementAndTicketPurchasingWebApp.model.MovieShowing;

/**
 * Servlet implementation class ManageShowtimes
 */
public class ManageShowtimes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageShowtimes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		int showRoomNumber = Integer.parseInt(request.getParameter("showroomId"));		
		int theatreNumber = Integer.parseInt(request.getParameter("theatreBuildingNumber"));
		
		ArrayList<MovieShowing> listShowing = new ArrayList<MovieShowing>();
		MovieShowingDB aMovieShowingDB= new MovieShowingDB();
		
		int shoWRoomId = aMovieShowingDB.getShowroomId(showRoomNumber, theatreNumber);
		session.setAttribute("showroomIdDB", shoWRoomId);
		
		listShowing = aMovieShowingDB.getMovieShowingList(shoWRoomId);
		session.setAttribute("movieShowingList", listShowing);
		
		RequestDispatcher dispatcher =
			      request.getRequestDispatcher("ManageShowtimes.jsp");
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
