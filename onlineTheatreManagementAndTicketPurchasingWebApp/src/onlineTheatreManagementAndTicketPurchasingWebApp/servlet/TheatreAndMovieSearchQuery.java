package onlineTheatreManagementAndTicketPurchasingWebApp.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import onlineTheatreManagementAndTicketPurchasingWebApp.DB.MovieShowingDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.MovieShowing;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Users;

/**
 * Servlet implementation class TheatreAndMovieSearchQuery
 */
public class TheatreAndMovieSearchQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TheatreAndMovieSearchQuery() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean infoExist = true;
		String searchQuery = request.getParameter("movieSearch");
		String searchDate = request.getParameter("movieDate");
		String searchTheatre = request.getParameter("movieTheatre");
		
		MovieShowingDB aMovieShowingDB = new MovieShowingDB();
		ArrayList<MovieShowing> movieList = new ArrayList<MovieShowing>();
		
		HttpSession session = request.getSession();
		
		ArrayList<MovieShowing> movieShowingList = 
				(ArrayList<MovieShowing>)session.getAttribute("movieSearchList");
		
		movieShowingList = new ArrayList<MovieShowing>();
		session.setAttribute("movieSearchList", movieShowingList);
		
		if(searchQuery==null){
			infoExist = false;
		}else{
			movieList = aMovieShowingDB.searchQuery(searchQuery, searchDate, searchTheatre);
		}
		
		if(movieList.isEmpty()){
			infoExist = false;
		}
		
		if(!infoExist){
			//String path = request.getHeader("referer");
			int error = 1;
			session.setAttribute("searchError", error);
			RequestDispatcher dispatcher =
				      request.getRequestDispatcher("CustomerHomePage.jsp");
				    dispatcher.forward(request, response);
		}else{
			for(MovieShowing ms: movieList){
				MovieShowing newMovieShowing = new MovieShowing();
				newMovieShowing.setaMovie(ms.getaMovie());
				newMovieShowing.setaShowroom(ms.getaShowroom());
				newMovieShowing.setStartTime(ms.getStartTime());
				newMovieShowing.setEndTime(ms.getEndTime());
				newMovieShowing.setPrice(ms.getPrice());
				movieShowingList.add(newMovieShowing);
			}
			
			int error = 0;
			session.setAttribute("searchError", error);
			RequestDispatcher dispatcher =
				      request.getRequestDispatcher("MovieSearchResults.jsp");
				    dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
