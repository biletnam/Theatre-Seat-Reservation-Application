package onlineTheatreManagementAndTicketPurchasingWebApp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import onlineTheatreManagementAndTicketPurchasingWebApp.DB.MovieShowingDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.DB.MoviesDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.DB.TheatresDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.MovieShowing;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.ShoppingCart;

/**
 * Servlet implementation class UpdateShoppingCart
 */
public class UpdateShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateShoppingCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int addingCartStatus = 0; //0 for no error, 1 for exceeded quantity
		String addToCart = request.getParameter("addToCart"); //if its 1, it indicate that its from add to cart button
		if(addToCart == null){
			addToCart = "0";
		}
		String referer = request.getHeader("referer");
		if((referer.contains("MovieSearchResult") && addToCart.trim().equals("1")) || (referer.contains("MovieDetailsAndSelection") && addToCart.trim().equals("1"))){
			int ticketQuantity = Integer.parseInt(request.getParameter("ticketNumber"));
			MovieShowing aMovieShowing = new MovieShowing();
			if(!aMovieShowing.validTicketQuantity(ticketQuantity)){
				response.sendRedirect("MovieDetailsAndSelection.jsp");
			} else {
				String movieName = request.getParameter("movieName");
				String movieImageURL = request.getParameter("imageURL");
				String theatreName = request.getParameter("theatreName");
				int showroomNum = Integer.parseInt(request.getParameter("showroomId"));
				String startTime = request.getParameter("startTime");
				int price = Integer.parseInt(request.getParameter("price"));
				int availableSeats = Integer.parseInt(request.getParameter("availableSeats"));

				MoviesDB aMovieDB = new MoviesDB();
				int movieId = aMovieDB.getMovieIdByName(movieName);
				TheatresDB aTheatreDB = new TheatresDB();
				int theatreId = aTheatreDB.getTheatreId(theatreName);
				MovieShowingDB aMovieShowingDB = new MovieShowingDB();
				int showroomId =  aMovieShowingDB.getShowroomId(showroomNum, theatreId);

				int numberPurchased = aMovieShowingDB.getNumberPurchased(movieId, showroomId, startTime);
				
				HttpSession session = request.getSession();
				
				ArrayList<ShoppingCart> cartListQuantity = 
						(ArrayList<ShoppingCart>) session.getAttribute("shoppingCartList");

				double totalQuantity = 0;
				
				if(cartListQuantity != null) {
		
					for(ShoppingCart sc: cartListQuantity){
						if(sc.getTheatreName().equals(theatreName) && sc.getMovieName().equals(movieName) && sc.getShowtimeStart().equals(startTime))
						totalQuantity += sc.getRequestedTicketQuantity();
					}
				}
				
				System.out.println(totalQuantity + "+" + numberPurchased + "+" + ticketQuantity + " ? " + availableSeats);
				if((totalQuantity + numberPurchased + ticketQuantity) > availableSeats){
					addingCartStatus = 1;
					PrintWriter out = response.getWriter(); 
					out.println(addingCartStatus);
					//response.sendRedirect("MovieDetailsAndSelection.jsp");
				} else {
					ArrayList<ShoppingCart> cartList = 
							(ArrayList<ShoppingCart>)session.getAttribute("shoppingCartList");

					if(cartList == null) {
						cartList = new ArrayList<ShoppingCart>();
						session.setAttribute("shoppingCartList", cartList);
					}

					ShoppingCart aItem = new ShoppingCart();
					aItem.setRequestedTicketQuantity(ticketQuantity);
					aItem.setMovieName(movieName);
					aItem.setImageURL(movieImageURL);
					aItem.setTheatreName(theatreName);
					aItem.setShowroomId(showroomNum);
					aItem.setShowtimeStart(startTime);
					aItem.setPrice(price);

					cartList.add(aItem);

					PrintWriter out = response.getWriter(); 
					out.println(addingCartStatus);
					/*
					double totalCost = 0;
					for(ShoppingCart sc: cartList){
						totalCost += sc.getPrice();
					}

					session.setAttribute("shoppingCartTotal", totalCost);

					RequestDispatcher dispatcher =
							request.getRequestDispatcher("View&CheckoutShoppingCart.jsp");
					dispatcher.forward(request, response);
					*/
				}
			}
		} else if(referer.contains("UpdateShoppingCart")){
			HttpSession session = request.getSession();
			int cartIndex = Integer.parseInt(request.getParameter("cartIndex"));
			ArrayList<ShoppingCart> cartList = 
					(ArrayList<ShoppingCart>) session.getAttribute("shoppingCartList");

			cartList.remove(cartIndex);

			double totalCost = 0;
			for(ShoppingCart sc: cartList){
				totalCost += sc.getPrice();
			}

			session.setAttribute("shoppingCartTotal", totalCost);

			RequestDispatcher dispatcher =
					request.getRequestDispatcher("View&CheckoutShoppingCart.jsp");
			dispatcher.forward(request, response);
		} else {
			HttpSession session = request.getSession();

			ArrayList<ShoppingCart> cartList = 
					(ArrayList<ShoppingCart>) session.getAttribute("shoppingCartList");

			if(cartList == null) {
				cartList = new ArrayList<ShoppingCart>();
				session.setAttribute("shoppingCartList", cartList);
			} else {

				double totalCost = 0;
				for(ShoppingCart sc: cartList){
					totalCost += sc.getPrice();

					session.setAttribute("shoppingCartTotal", totalCost);
				}
			}


			RequestDispatcher dispatcher =
					request.getRequestDispatcher("View&CheckoutShoppingCart.jsp");
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
