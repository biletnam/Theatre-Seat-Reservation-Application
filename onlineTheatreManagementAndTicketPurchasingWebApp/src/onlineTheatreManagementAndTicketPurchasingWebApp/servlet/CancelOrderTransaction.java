package onlineTheatreManagementAndTicketPurchasingWebApp.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import onlineTheatreManagementAndTicketPurchasingWebApp.DB.CreditCardsDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.DB.OrdersDB;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.OrderItems;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Transactions;

/**
 * Servlet implementation class CancelOrderTransaction
 */
public class CancelOrderTransaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelOrderTransaction() {
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
		
		OrdersDB aOrderDB = new OrdersDB();
		aOrderDB.deleteOrderItem(orderId, movieShowingId, startTime);
		HttpSession session = request.getSession();
		int customerId = (int) session.getAttribute("userId");
		
		CreditCardsDB aCreditCardDB = new CreditCardsDB();
		String creditCardNumber  = aCreditCardDB.getCreditCardNumByOrder(orderId, customerId);
		Transactions aTransaction = new Transactions();
		aTransaction.setCreditCardNumber(creditCardNumber);
		double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
		double refund = totalPrice*-1;
		aCreditCardDB.updateCreditCard(aTransaction, BigDecimal.valueOf(refund));
		
		OrderItems cancelOrderItem = 
				(OrderItems) session.getAttribute("cancelItem");
		
		RequestDispatcher dispatcher =
			      request.getRequestDispatcher("CancellationConfirmation.jsp");
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
