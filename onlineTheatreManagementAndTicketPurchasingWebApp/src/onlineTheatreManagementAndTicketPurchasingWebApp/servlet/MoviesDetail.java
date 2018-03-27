package onlineTheatreManagementAndTicketPurchasingWebApp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import onlineTheatreManagementAndTicketPurchasingWebApp.DB.MoviesDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Movies;

/**
 * Servlet implementation class MoviesDetail
 */
public class MoviesDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoviesDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String MovieName = request.getParameter("movieTitle");
		String MovieDescription = request.getParameter("description");
		String MovieImageURL = request.getParameter("imageURL");
		MoviesDB aMoviesDB = new MoviesDB();
		Movies aMovie = new Movies();
		aMovie = aMoviesDB.GetMovieInDetails(MovieName);
		
		session.setAttribute("MovieInDetail",aMovie);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("MovieDetails.jsp");
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
