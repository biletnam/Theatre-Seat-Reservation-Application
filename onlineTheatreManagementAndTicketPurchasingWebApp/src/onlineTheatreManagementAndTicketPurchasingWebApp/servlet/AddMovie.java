package onlineTheatreManagementAndTicketPurchasingWebApp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineTheatreManagementAndTicketPurchasingWebApp.DB.MoviesDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Movies;



/**
 * Servlet implementation class AddMovie
 */
public class AddMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMovie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String moviename = request.getParameter("movieName");
	    String imageURL = request.getParameter("ImageURL");
	    String genre = request.getParameter("movieGenre");
		String discription = request.getParameter("movieDescription");
		String rating = request.getParameter("movieRating");

		MoviesDB aMovieDB = new MoviesDB();
		aMovieDB.addMovie(moviename, discription, genre, rating, imageURL);
		response.sendRedirect("OwnerHomePage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
