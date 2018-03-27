package onlineTheatreManagementAndTicketPurchasingWebApp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import onlineTheatreManagementAndTicketPurchasingWebApp.model.Movies;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Review;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Users;
import onlineTheatreManagementAndTicketPurchasingWebApp.DB.MoviesDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.DB.ReviewsDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.DB.UsersDB;
/**
 * Servlet implementation class CustomerReview
 */
public class CustomerReview extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerReview() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();	

		int userId = (int) session.getAttribute("userId");

		String movieTitle = request.getParameter("movieName");	
		String review = request.getParameter("review");
		int rating = (int) Integer.parseInt(request.getParameter("rating"));

		int reviewStatus = 0; //0 for no error, 1 for rating error, 2 for review error
		boolean errorExist = false;
		
		if(rating < 0 || rating > 5){
			reviewStatus = 1;
			errorExist = true;
		}

		if(review.length() > 255){
			reviewStatus = 2;
			errorExist = true;
		}


		if(!errorExist){
			MoviesDB aMovieDB = new MoviesDB();
			int movieId = aMovieDB.getMovieIdByName(movieTitle);


			Review aReview = new Review();
			Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date now = new Date();
			aReview.setReviewDate(formatter.format(now));
			aReview.setRating(rating);
			aReview.setReview(review);

			ReviewsDB submitReview = new ReviewsDB();
			submitReview.commitReview(aReview, movieId, userId);
			UsersDB aUserDB = new UsersDB();
			Users aUser = new Users();
			aUser = aUserDB.getUsersByUserId(userId);
			
			//send back data via AJAX to update customer review 
			String reviewUpdate = "<h4><a href=\"#1000\" data-toggle=\"collapse\">" + aUser.getFirstName() + " " + aUser.getLastName() + "</a></h4> <div id=\"1000\" class=\"collapse\"> <br>" + formatter.format(now)  + "<br> Rating: " + rating + "/5 <br> " + review + "";
			PrintWriter out = response.getWriter(); 
			out.println(reviewUpdate);
		} else {
			PrintWriter out = response.getWriter(); 
			out.println(reviewStatus);
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
