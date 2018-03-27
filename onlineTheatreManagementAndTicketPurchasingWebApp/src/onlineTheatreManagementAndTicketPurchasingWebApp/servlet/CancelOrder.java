package onlineTheatreManagementAndTicketPurchasingWebApp.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import onlineTheatreManagementAndTicketPurchasingWebApp.DB.OrdersDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.MovieShowing;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Movies;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.OrderItems;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Orders;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Showroom;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Theatres;

/**
 * Servlet implementation class CancelOrder
 */
public class CancelOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int orderId = Integer.parseInt(request.getParameter("orderNum"));
		String startTime = request.getParameter("startTime");
		int movieShowingId = Integer.parseInt(request.getParameter("movieShowingId"));
		String movieName = request.getParameter("movieName");
		int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String theatreName = request.getParameter("theatreName");
		HttpSession session = request.getSession();
		
		OrderItems cancelOrderItem = 
					(OrderItems) session.getAttribute("cancelItem");
		
		cancelOrderItem = new OrderItems();
		session.setAttribute("cancelItem", cancelOrderItem);
		
		Orders aOrder = new Orders();
		aOrder.setOrderId(orderId);
		MovieShowing aMovieShowing = new MovieShowing();
		aMovieShowing.setId(movieShowingId);
		aMovieShowing.setStartTime(startTime);
		Movies aMovie = new Movies();
		aMovie.setMovieTitle(movieName);
		aMovieShowing.setaMovie(aMovie);
		Theatres aTheatre = new Theatres();
		aTheatre.setTheatreName(theatreName);
		Showroom aShowroom = new Showroom();
		aShowroom.setaTheatre(aTheatre);
		aMovieShowing.setaShowroom(aShowroom);
		
		cancelOrderItem.setQuantity(quantity);
		cancelOrderItem.setTotalPrice(totalPrice);
		cancelOrderItem.setaOrder(aOrder);
		cancelOrderItem.setaMovieShowing(aMovieShowing);
		
		RequestDispatcher dispatcher =
			      request.getRequestDispatcher("CancelOrder.jsp");
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
