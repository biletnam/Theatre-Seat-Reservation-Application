package onlineTheatreManagementAndTicketPurchasingWebApp.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import onlineTheatreManagementAndTicketPurchasingWebApp.DB.MoviesDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.DB.ReviewsDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.DB.TheatresDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.MovieShowing;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Movies;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Review;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Showroom;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Theatres;

/**
 * Servlet implementation class MovieSearchResult
 */
public class MovieSearchResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieSearchResult() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		//int userId = (int) session.getAttribute("userId"); maybe use this for thumbnail cookie
		String movieName = request.getParameter("movieName");
		String movieGenre = request.getParameter("movieGenre");
		MoviesDB aMovieDB = new MoviesDB();
		String movieDescription = aMovieDB.getMovieDescriptionByName(movieName);
		String movieRating = aMovieDB.getMovieRatingByName(movieName);
		
		String movieImageURL = request.getParameter("imageURL");
		String movieImageURLCookie = aMovieDB.getMovieURLByGenre(movieGenre, movieName);

		Cookie thumbnailCookie = new Cookie("targetAd", movieImageURLCookie);
		thumbnailCookie.setMaxAge(10 * 365 * 24 * 60 * 60);
		response.addCookie(thumbnailCookie);
		
		String theatreName = request.getParameter("theatreName");
		int showroomId = Integer.parseInt(request.getParameter("showroomId"));
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		int availableSeats = Integer.parseInt(request.getParameter("availableSeats"));
		int price = Integer.parseInt(request.getParameter("price"));
		
		MovieShowing movieShowingDetails = 
				(MovieShowing)session.getAttribute("movieDetail");
		
		movieShowingDetails = new MovieShowing();
		session.setAttribute("movieDetail", movieShowingDetails);
		
		Movies aMovie = new Movies();
		aMovie.setMovieTitle(movieName);
		aMovie.setDescription(movieDescription);
		aMovie.setMPAARating(movieRating);
		aMovie.setImageURL(movieImageURL);
		aMovie.setGenre(movieGenre);
		Showroom aShowroom = new Showroom();
		aShowroom.setShowroomId(showroomId);
		aShowroom.setAvailableSeats(availableSeats);
		Theatres aTheatre = new Theatres();
		aTheatre.setTheatreName(theatreName);
		aShowroom.setaTheatre(aTheatre);
		movieShowingDetails.setaMovie(aMovie);
		movieShowingDetails.setaShowroom(aShowroom);
		movieShowingDetails.setStartTime(startTime);
		movieShowingDetails.setEndTime(endTime);
		movieShowingDetails.setPrice(price);
		
		ArrayList<Review> reviewList = new ArrayList<Review>();
		ReviewsDB aReviewDB = new ReviewsDB();
		reviewList = aReviewDB.fetchReview(movieName);
		
		movieShowingDetails.setReviewList(reviewList);
		
		RequestDispatcher dispatcher =
			      request.getRequestDispatcher("MovieDetailsAndSelection.jsp");
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
