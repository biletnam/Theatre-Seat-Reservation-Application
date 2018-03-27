package bank.DB;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bank.DB.DataCred;
import bank.DB.DatabaseConnection;
import bank.model.BankModel;

public class BankDB {

	public void updateCreditCard(BankModel aBank, java.math.BigDecimal totalCost){
		//open connection
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		
		java.math.BigDecimal balance = null;
		
		String query = "SELECT Balance "
				+ "FROM CreditCard "
				+ "WHERE CreditCardNumber = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, aBank.getCreditCardNumber());
			rs = ps.executeQuery();
			if(rs.next()){
				balance = rs.getBigDecimal("Balance");
			} 
		rs.close();
		ps.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		System.out.println("Previous Balance is " + balance);
		balance = balance.subtract(totalCost);
		System.out.println("Updated Balance is " + balance);
		query = "UPDATE CreditCard SET Balance = ? "
				+ "WHERE CreditCardNumber = ?";
		ps = null;
		rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setBigDecimal(1, balance);
			ps.setString(2, aBank.getCreditCardNumber());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		
	}
	//for credit card validation - returns the error number
		//0 is success, 1 if incorrect information, 2 if balance is insufficient
		public int cardNumberAndBalanceValidation(BankModel aBank, java.math.BigDecimal totalCost) {
			//open connection
			Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
			String cardHolderName = "";
			String cardType = "";
			java.math.BigDecimal balance = null;
			String cvv = "";
			String expirationDate = "";
			
			int error = 0;
			String query = "SELECT CardHolderName, Balance, CardType, CVV, ExpirationDate "
					+ "FROM CreditCard "
					+ "WHERE CreditCardNumber = ?";
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = conn.prepareStatement(query);
				ps.setString(1, aBank.getCreditCardNumber());
				rs = ps.executeQuery();
				if(rs.next()){
					cardHolderName = rs.getString("CardHolderName");
					cardType = rs.getString("CardType");
					balance = rs.getBigDecimal("Balance");
					cvv = rs.getString("CVV");
					expirationDate = rs.getString("ExpirationDate");
				} 
			rs.close();
			ps.close();
			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}

			conn = DatabaseConnection.CloseConnection(rs, ps, conn);
			
			if(!cardHolderName.equals(aBank.getCardHolderName()) || !cardType.equals(aBank.getCardType()) || !cvv.equals(aBank.getCvv()) || !expirationDate.equals(aBank.getExpirationDate())){
				error = 1;
				return error;
			} else if(balance.subtract(totalCost).compareTo(BigDecimal.valueOf(0.00)) < 0){
				error = 2;
				return error;
			}

			return error;
		}
	
}
