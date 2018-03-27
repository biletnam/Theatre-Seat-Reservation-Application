package bank.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.DB.BankDB;
import bank.model.BankModel;

/**
 * Servlet implementation class Bank
 */
public class Bank extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bank() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		double totalCost = Double.parseDouble(request.getParameter("totalCost"));
		String fullName = request.getParameter("cardHolderFullName");
		String cardType = request.getParameter("cardType");
		String cardNumber = request.getParameter("cardNumber");
		String cvv = request.getParameter("securityCode");
		String expirationDate = request.getParameter("expirationDate");
		
		BankModel aBank = new BankModel();
		aBank.setCardHolderName(fullName);
		aBank.setCardType(cardType);
		aBank.setCreditCardNumber(cardNumber);
		aBank.setCvv(cvv);
		aBank.setExpirationDate(expirationDate);
		
		BankDB aBankDB = new BankDB();
		int error = aBankDB.cardNumberAndBalanceValidation(aBank, BigDecimal.valueOf(totalCost));		
		if(error == 0){
			aBankDB.updateCreditCard(aBank, BigDecimal.valueOf(totalCost));
		}
		System.out.println("Error code: " + error);
		PrintWriter out = response.getWriter(); 
		out.println(error);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
