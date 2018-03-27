<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jstl/core_rt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Transaction Page</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="//geodata.solutions/includes/countrystate.js"></script>

</head>
<body>
<nav class="navbar navbar-toggleable-md navbar-light bg-faded">
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
   <c:choose>
	 <c:when test="${userInfo.userName != null}">
	   	<a class="navbar-brand">Hi ${userInfo.userName}!</a>
	 </c:when>
	 <c:when test="${userInfo.userName == null}">
	    <c:redirect url="Login.jsp"/>
	 </c:when>
  </c:choose>
  <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
 	<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
      <li class="nav-item">
        <a class="nav-link" href="CustomerHomePage.jsp">Home</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="ViewOrders.jsp">View Orders</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="UpdateShoppingCart">Shopping Cart</a>
      </li>
      <li class="nav-item">
       <a class="navbar-brand" href="Logout">Logout</a>
      </li>
    </ul>
  </div>
</nav>
	<center>
		<h1>Confirm Purchase</h1>
		<br>
		<table border="1">
			<thead>
				<tr>
					<th>Movie Name</th>
					<th>Ticket Quantity</th>
					<th>Total Price</th>
					<th>Theatre Name</th>
				</tr>
			</thead>
			<c:forEach items="${shoppingCartList}" var="cart" varStatus="status">
			<tbody>
				<tr> 
				<th class="text-center"><c:out value="${cart.movieName}"/> </th>
				<th class="text-center"><c:out value="${cart.requestedTicketQuantity}"/></th>
				<th class="text-center">$<c:out value="${cart.price}"/></th>
				<th class="text-center"><c:out value="${cart.theatreName}"/></th>
			</tr>
		</tbody>
		</c:forEach>
		</table>
		<br>
		<p style="font-size: 20px">
			<b>TOTAL COST: $<c:out value="${shoppingCartTotal}"/></b>
		</p>
		<br>

		<form action="View&CheckoutShoppingCart.jsp" method="post">
			<input class="btn-primary btn-sm" type="submit" value="Cancel Payment">
		</form>
		<br>
	</center>

	<hr>


	<form action="CustomerTransactionConfirmation" method="post">
		<br> <b> Card Holder's Full Name: </b> <input type="text"
			name="cardHolderFullName">
			<br> <br> <b> Card Type: </b> <select name="cardType">
			<option>Visa</option>
			<option>Master</option>
			<option>Discover</option>
		</select> <br> <br> <b> Card Number: </b> <input type="text"
			name="cardNumber"> <br> <br> <b> Security Code:
		</b> <input type="text" name="securityCode"> <i>(Card
			Verification Value - The last 3 digits on the back of your card) </i></i><br>
		<br> <b> Expiration Date: </b> <input type="text" name="expirationDate">
		
		<hr>

		<p style="font-size: 20px">
			<b> Billing Address </b> <br>
		</p>
		<br> <b> Country: </b> <select name="billingCountry"
			class="countries order-alpha" id="countryId">
			<option value="">Select Country</option>
		</select> <br> <br> <b> First Name: </b> <input type="text"
			name="billingFirstName"> <br> <br> <b> Last
			Name: </b> <input type="text" name="billingLastName"> <br> <br>
		<b> Street Address </b> <input type="text" name="billingStreetAddress">
		<br> <br> <b> Apt/Suite </b> <input type="text"
			name="billingAptSuite"> <br> <br> <b>
			State/Province </b> <input type="text" name="billingState"> <br>
		<br> <b> City </b> <input type="text" name="billingCity">
		<br> <br> <b> Zip/Postal Code </b> <input type="text"
			name="billingZip"> <br> <br> <b> Telephone </b> <input
			type="text" name="billingTelephone"> <br> <br> <b>
			Email Address </b> <input type="text" name="billingEmailAddress">
		<br> <br>

		<hr>

			<input class="btn-primary btn-sm" type="submit" value="Confirm Payment">
	</form>
	<br>

</body>

</html>