package onlineTheatreManagementAndTicketPurchasingWebApp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import onlineTheatreManagementAndTicketPurchasingWebApp.DB.MovieShowingDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.DB.MoviesDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.DB.OrdersDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.DB.TheatresDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.MovieShowing;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Movies;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.OrderItems;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Orders;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.ShoppingCart;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Showroom;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Theatres;

/**
 * Servlet implementation class PlaceOrder
 */
public class PlaceOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String billingStreetAddress = request.getParameter("billingStreetAddress");
		String billingAptSuite = request.getParameter("billingAptSuite");
		String billingState = request.getParameter("billingState");
		String billingCity = request.getParameter("billingCity");
		String billingCountry = request.getParameter("billingCountry");		
		String billingZip = request.getParameter("billingZip");
		String billingAddress = billingStreetAddress + " " + billingAptSuite + "\n" + billingCity + ", " + billingState + "\n"
				+ billingCountry + ", " + billingZip;
		String cardNumber = request.getParameter("cardNumber");
		double totalCost = (double) session.getAttribute("shoppingCartTotal");
		
		Orders aOrder = new Orders();
		aOrder.setCustomerId((int)request.getSession().getAttribute("userId"));
		aOrder.setBillingAddress(billingAddress);
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		aOrder.setOrderDate(formatter.format(now));
		aOrder.setCreditCardNumber(cardNumber);
		aOrder.setTotalCost(totalCost);
		
		OrdersDB aOrderDB = new OrdersDB();
		aOrderDB.addOrder(aOrder);
		int orderId = aOrderDB.getOrderId(aOrder);
		session.setAttribute("orderId", orderId);
		ArrayList<ShoppingCart> cartList = 
				(ArrayList<ShoppingCart>)request.getSession().getAttribute("shoppingCartList");
		
		ArrayList<OrderItems> orderList =
				(ArrayList<OrderItems>)request.getSession().getAttribute("shoppingOrderList");
		
		orderList = new ArrayList<OrderItems>();
	    session.setAttribute("shoppingOrderList", orderList);
		
		for(int i=0; i<cartList.size();i++){
			Movies aMovie = new Movies();
			aMovie.setMovieTitle(cartList.get(i).getMovieName());
			MovieShowing aMovieShowing = new MovieShowing();
			aMovieShowing.setaMovie(aMovie);
			aMovieShowing.setStartTime(cartList.get(i).getShowtimeStart());
			Theatres aTheatre = new Theatres();
			aTheatre.setTheatreName(cartList.get(i).getTheatreName());
			TheatresDB aTheatresDB = new TheatresDB();
			int theatreId = aTheatresDB.getTheatreId(cartList.get(i).getTheatreName());
			int showroomNumber = cartList.get(i).getShowroomId();
			Showroom aShowroom = new Showroom();
			aShowroom.setaTheatre(aTheatre);
			aShowroom.setShowroomId(showroomNumber);
			aMovieShowing.setaShowroom(aShowroom);
			aMovieShowing.setPrice(cartList.get(i).getPrice());
			
			MoviesDB aMovieDB = new MoviesDB();
			int movieId = aMovieDB.getMovieIdByName(cartList.get(i).getMovieName());
			MovieShowingDB aMovieShowingDB = new MovieShowingDB();
			int showroomId = aMovieShowingDB.getShowroomId(showroomNumber, theatreId);
			int movieShowingId = aMovieShowingDB.getMovieShowingId(aMovieShowing, movieId, showroomId);
			int quantity = cartList.get(i).getRequestedTicketQuantity();
			int totalPrice = cartList.get(i).getPrice();
			System.out.println(orderId + " " + movieShowingId + " " + quantity);
			aOrderDB.addOrderItem(orderId, movieShowingId, quantity, totalPrice);
			
			aMovieShowingDB.updateNumberPurchasedByMovieShowingId(movieShowingId, quantity);
			
			OrderItems aOrderItem = new OrderItems();
			aOrderItem.setaOrder(aOrder);
			aOrderItem.setaMovieShowing(aMovieShowing);
			aOrderItem.setQuantity(quantity);
			
			orderList.add(aOrderItem);
		}
		
		session.removeAttribute("shoppingCartList");
		
		PrintWriter out = response.getWriter(); 
		out.println(orderId);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
