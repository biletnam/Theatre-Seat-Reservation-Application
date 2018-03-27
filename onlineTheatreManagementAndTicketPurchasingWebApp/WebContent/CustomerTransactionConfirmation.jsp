<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Transaction Confirmation</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script>
function printFunction() {
    window.print();
}
</script>
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
      <li class="nav-item active">
     <br> <a href="ViewOrders"> View Orders </a> <br><br>
      </li>
      <li class="nav-item">
      <a href="Logout"> Logout </a> <br><br>
      </li>
    </ul>
  </div>
</nav>

<c:choose>
	<c:when test="${errorCode == 0}">
		<h1> Congratulations! Your order has been placed! </h1>
		
		Please see your order details below.
		<br> <br>
		<b>Order Number: ${orderId}</b>
		
		<br>
		<table border="1" class="table table-bordered table-striped table-hover">
			<thead>
				<tr>
					<th class="text-center">Movie name</th>
					<th class="text-center">Ticket quantity</th>
					<th class="text-center">Total price</th>
					<th class="text-center">Theatre name</th>
					<th class="text-center">Number</th>
					<th class="text-center">Showtime</th>
					<th class="text-center">Date</th>
				</tr>
			</thead>
			<c:forEach items="${shoppingOrderList}" var="order" varStatus="status">
			<tbody id="select">
				<tr> 
				<th class="text-center"><c:out value="${order.aMovieShowing.aMovie.movieTitle}"/> </th>
				<th class="text-center"><c:out value="${order.quantity}"/></th>
				<th class="text-center">$<c:out value="${order.aMovieShowing.price}"/></th>
				<th class="text-center"><c:out value="${order.aMovieShowing.aShowroom.aTheatre.theatreName}"/></th>			
				<th class="text-center"><c:out value="${order.aMovieShowing.aShowroom.showroomId}"/></th>
				<c:set var="startTimeDate" value="${order.aMovieShowing.startTime}"/>
				<th class="text-center"><c:out value="${fn:substring(startTimeDate,11,19)}"/></th>
				<th class="text-center"><c:out value="${fn:substring(startTimeDate,0,10)}"/></th>
			</tr>
			</tbody>
			</c:forEach>
		</table>
		<br> 
		<center>
		
		Billed to: <c:out value="${shoppingOrderList[0].aOrder.billingAddress}"/>
		<form action = "CustomerHomePage.jsp" method="post">
 			<input class="btn btn-info "type="submit" value ="Home">
		</form> <br> <br>
		
		Click the button to print the current page. <br>
		
		<button class="btn btn-info" onclick="printFunction()"> Print </button>
		
  	 </center>  
	</c:when>
	<c:when test="${errorCode == 1}">
	<center>
	Transaction was not successful. <br>
	<h1> Sorry, you entered the wrong credit card information. Please retype it. </h1>
	</center>
	</c:when>
	<c:when test="${errorCode == 2}">
	<center>
	Transaction was not successful. <br>
	<h1> Sorry, your credit card has insufficient balance. The order is not placed. </h1>
	</center>
	</c:when>
</c:choose>

</body>
</html>