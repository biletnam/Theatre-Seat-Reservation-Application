<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jstl/core_rt" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Order</title>

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
        		<a class="nav-link" href="UpdateShoppingCart">Shopping Cart</a>
      		</li>
      
      		<li class="nav-item">
       			<a class="navbar-brand" href="Logout">Logout</a>
      		</li>
    	</ul>
  	</div>
</nav>

<c:set var="list" value="${shoppingOrderItemList}"/>
	<c:if test="${fn:length(list) == 0}">
		<center>
			You have 0 items in your order.
		</center>
	</c:if>
	
	<c:if test="${fn:length(list) > 0}">
		<center>
			<h1>Order Number:<c:out value="${orderNum}"/></h1>
			<table border="1"  class="table table-bordered table-striped table-hover">
				<thead>
					<tr>
						<th>Movie name</th>
						<th>Ticket quantity</th>
						<th>Total price</th>
						<th>Theatre name</th>
						<th>Number</th>
						<th>Showtime</th>
						<th>Date</th>
					</tr>
				</thead>
				
				<c:forEach items="${shoppingOrderItemList}" var="item" varStatus="status">
				<tbody id="select">
					<tr> 
					<th class="text-center"><c:out value="${item.aMovieShowing.aMovie.movieTitle}"/></th>
					<th class="text-center"><c:out value="${item.quantity}"/></th>
					<th class="text-center">$<c:out value="${item.totalPrice}"/></th>
					<th class="text-center"><c:out value="${item.aMovieShowing.aShowroom.aTheatre.theatreName}"/></th>
					<th class="text-center"><c:out value="${item.aMovieShowing.aShowroom.showroomId}"/></th>
					<c:set var="startTimeDate" value="${item.aMovieShowing.startTime}"/>
					<th class="text-center"><c:out value="${fn:substring(startTimeDate,11,19)}"/></th>
					<th class="text-center"><c:out value="${fn:substring(startTimeDate,0,10)}"/></th>
					<c:set var="beforeDate" value="${item.aMovieShowing.compareDate}"/>	
					<th>
						<c:if test="${beforeDate}">
							<form action="CancelOrder" method="post">
								<input type="hidden" name="orderNum" value="<c:out value="${orderNum}"/>">
								<input type="hidden" name="startTime" value="<c:out value="${item.aMovieShowing.startTime}"/>">
								<input type="hidden" name="movieShowingId" value="<c:out value="${item.aMovieShowing.id}"/>">
								<input type="hidden" name="movieName" value="<c:out value="${item.aMovieShowing.aMovie.movieTitle}"/>">
								<input type="hidden" name="totalPrice" value="<c:out value="${item.totalPrice}"/>">
								<input type="hidden" name="quantity" value="<c:out value="${item.quantity}"/>">
								<input type="hidden" name="theatreName" value="<c:out value="${item.aMovieShowing.aShowroom.aTheatre.theatreName}"/>">
								<input class="btn btn-info "type="submit" value="Cancel">
							</form>
						</c:if>
					</th>	
				</tr>
				</tbody>
				</c:forEach> 	
			</table>
			<br>
		
			<table border="1" class="table table-bordered table-striped table-hover">
				<thead>
					<tr>
						<th>Order Total</th>
						<th>Ordered Date</th>
					</tr>
				</thead>
				
				<tbody>
					<tr>
						<th>$${orderTotalCost}</th>
						<th>${orderDate}</th>
					</tr>
				</tbody>
			</table>
			
			<br> <br>
		
		</center>
	</c:if>
	
<center>
	<br>Any enquiries? Contact us at <b>${initParam.user}</b>
</center>

</body>
</html>