package onlineTheatreManagementAndTicketPurchasingWebApp.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import onlineTheatreManagementAndTicketPurchasingWebApp.DB.OrdersDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Orders;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.ShoppingCart;

/**
 * Servlet implementation class ViewOrders
 */
public class ViewOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewOrders() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		int userId = (int) session.getAttribute("userId");
		
		ArrayList<Orders> orderList = 
				(ArrayList<Orders>)session.getAttribute("shoppingOrderList");
		
		orderList = new ArrayList<Orders>();
		session.setAttribute("shoppingOrderList", orderList);
		
		ArrayList<Orders> orderListTemp = new ArrayList<Orders>();
		OrdersDB aOrderDB = new OrdersDB();
		
		orderListTemp = aOrderDB.retrieveOrder(userId);
		
		for(Orders o: orderListTemp){
			orderList.add(o);
		}
		
		RequestDispatcher dispatcher =
			      request.getRequestDispatcher("ViewOrders.jsp");
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
