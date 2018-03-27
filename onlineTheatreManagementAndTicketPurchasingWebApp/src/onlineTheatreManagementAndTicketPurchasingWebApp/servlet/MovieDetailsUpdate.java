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
 * Servlet implementation class MovieDetailsUpdate
 */
public class MovieDetailsUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieDetailsUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String movieName = request.getParameter("movieName");
		String movieDescription = request.getParameter("movieDescription");
		String movieGenre = request.getParameter("movieGenre");
		
		String movieRating = request.getParameter("movieRating");
		MoviesDB update = new MoviesDB();
		
		update.UpdateMovies(movieDescription, movieGenre, movieRating, movieName);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("OwnerHomePage.jsp");
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
