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
import onlineTheatreManagementAndTicketPurchasingWebApp.model.OrderItems;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Orders;

/**
 * Servlet implementation class ManageOrder
 */
public class ManageOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int orderId = Integer.parseInt(request.getParameter("orderNum"));
		BigDecimal totalCost = new BigDecimal(request.getParameter("totalCost"));
		String orderDate = request.getParameter("orderDate");
		
		HttpSession session = request.getSession();
		
		session.setAttribute("orderNum", orderId);
		session.setAttribute("orderTotalCost", totalCost);
		session.setAttribute("orderDate", orderDate);
		
		ArrayList<OrderItems> orderItemList = 
				(ArrayList<OrderItems>)session.getAttribute("shoppingOrderItemList");
		
		orderItemList = new ArrayList<OrderItems>();
		session.setAttribute("shoppingOrderItemList", orderItemList);
      
		
		OrdersDB aOrderDB = new OrdersDB();
		ArrayList<OrderItems> orderItemListTemp = aOrderDB.retrieveOrderItem(orderId);
		
		for(OrderItems oi: orderItemListTemp){
			orderItemList.add(oi);
		}
		
		RequestDispatcher dispatcher =
			      request.getRequestDispatcher("ManageOrder.jsp");
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
