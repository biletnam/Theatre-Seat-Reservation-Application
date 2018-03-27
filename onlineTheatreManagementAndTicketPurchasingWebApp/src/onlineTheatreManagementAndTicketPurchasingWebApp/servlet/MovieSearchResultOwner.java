package onlineTheatreManagementAndTicketPurchasingWebApp.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import onlineTheatreManagementAndTicketPurchasingWebApp.DB.MoviesDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.DB.OrdersDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Movies;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Orders;

/**
 * Servlet implementation class MovieSearchResultOwner
 */
public class MovieSearchResultOwner extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieSearchResultOwner() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//HttpSession session = request.getSession();
//		
//		int userId = (int) session.getAttribute("userId");
//		
//		ArrayList<Orders> orderList = 
//				(ArrayList<Orders>)session.getAttribute("shoppingOrderList");
//		
//		orderList = new ArrayList<Orders>();
//		session.setAttribute("shoppingOrderList", orderList);
//		
//		ArrayList<Orders> orderListTemp = new ArrayList<Orders>();
//		OrdersDB aOrderDB = new OrdersDB();
//		
//		orderListTemp = aOrderDB.retrieveOrder(userId);
//		
//		for(Orders o: orderListTemp){
//			orderList.add(o);
//		}
//		
//		RequestDispatcher dispatcher =
//			      request.getRequestDispatcher("ViewOrders.jsp");
//			    dispatcher.forward(request, response);
//		String name = request.getParameter("movieSearch");
//		ArrayList<Movies> movieinformation = new ArrayList<Movies>();
//		MoviesDB getMovieIformation = new MoviesDB(); 
//		// get all the information of the movie base on movie name 
//		movieinformation = getMovieIformation.fetchMovie(name);
//		// need to have the method that can pass the list of object
//		//Movies newEmp = new Movies(movieinformation.get(0).getMovieTitle(),movieinformation.get(0).getDescription(),movieinformation.get(0).getGenre(),movieinformation.get(0).getThumbnail(),movieinformation.get(0).getMPAARating());
//		MoviesDB util = new MoviesDB();
//		
//		HttpSession session = request.getSession();
//		session.setAttribute("newEmp",movieinformation);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("MovieSearchResultOwner.jsp");
//		dispatcher.forward(request, response);
        HttpSession session = request.getSession();
        String name = request.getParameter("movieSearch");
				
		ArrayList<Movies> Movies = 
			(ArrayList<Movies>)session.getAttribute("MoviesList");
		MoviesDB aMovieDB = new MoviesDB();
		Movies = aMovieDB.GetMovieObject(name);
		session.setAttribute("MoviesList", Movies);	
		
		RequestDispatcher dispatcher =
			      request.getRequestDispatcher("MovieSearchResultOwner.jsp");
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
