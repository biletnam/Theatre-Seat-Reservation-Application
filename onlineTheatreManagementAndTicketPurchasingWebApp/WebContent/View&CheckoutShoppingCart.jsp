<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jstl/core_rt" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View & Checkout Shopping Cart</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
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
        		<a class="nav-link" href="AccountSettings.jsp">Account Settings</a>
      		</li>
      		
      		<li class="nav-item">
        		<a class="nav-link" href="ViewOrders">View Orders</a>
      		</li>
	  		
	  		<li class="nav-item">
        		<a class="nav-link" href="MovieDetailsAndSelection.jsp">Back</a>
      		</li>
     	 	
     	 	<li class="nav-item">
        		<a class="nav-link" href="#">Shopping Cart</a>
      		</li>
      
      		<li class="nav-item">
       			<a class="navbar-brand" href="Logout">Logout</a>
      		</li>
    	</ul>
  	</div>
</nav>

<center>
	<h1> Checkout Page </h1>
</center>

<c:set var="list" value="${shoppingCartList}"/>
	<c:if test="${fn:length(list) == 0}">
		<center>
			You have 0 items in your shopping cart.
		</center>
	</c:if>
	
	<c:if test="${fn:length(list) > 0}">
		<table border="1" class="table table-bordered table-striped table-hover">
			<thead>
				<tr>
					<th class="text-center">Movie Name</th>
					<th class="text-center">Thumbnail of the movie poster</th>
					<th class="text-center">Theatre Name</th>
					<th class="text-center">Theatre Number</th>
					<th class="text-center">Showtime and Date</th>
					<th class="text-center">Number of ticket being bought</th>
					<th class="text-center">Total price</th>
				</tr>
			</thead>
		
			<c:forEach items="${shoppingCartList}" var="cart" varStatus="status">
				<tbody id="select">
					<tr> 
						<th class="text-center"><c:out value="${cart.movieName}"/> </th>
						<th class="text-center">
						<img src=<c:out value="${cart.imageURL}"/> style="width: 100px"> </th>	
						<th class="text-center"><c:out value="${cart.theatreName}"/></th>
						<th class="text-center"><c:out value="${cart.showroomId}"/></th>
						<th class="text-center"><c:out value="${cart.showtimeStart}"/></th>
						<th class="text-center"><c:out value="${cart.requestedTicketQuantity}"/></th>
						<th class="text-center"><c:out value="${cart.price}"/></th>
						
						<th class="text-center">
						<form action="UpdateShoppingCart" method="post">
						<input type="hidden" name="cartIndex" value="${status.index}">
						<button class ="btn btn-warning btn-lg">
						Remove
						</button>
						</form>
						</th>
					</tr>
				</tbody>
			</c:forEach>
		</table>
	
		<br> <br>	
		<div align="center">
			<b>Total Cost: $<c:out value="${shoppingCartTotal}"/></b>  <br> 
			<form action = "ConfirmOrder.jsp" method="post">
 				<input class="btn-primary btn-sm" type="submit" value ="Checkout">
			</form> <br>
		</div>
	</c:if>

	<center>
		<br>Any enquiries? Contact us at <b>${initParam.user}</b>
	</center>
	
</body>

</html>