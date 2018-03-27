<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jstl/core_rt" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cancel Order</title>
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
		       <a class="navbar-brand" href="Logout">Logout</a>
		    </li>
		</ul>
	</div>
</nav>

<center>
	<h1>Cancel Order Page</h1>
		<table border="1" class="table table-bordered table-striped table-hover">
			<thead>
				<tr>
					<th  class="text-center">Order Number</th>
					<th  class="text-center">Movie name</th>
					<th  class="text-center">Ticket quantity</th>
					<th  class="text-center">Total price</th>
					<th  class="text-center">Theater name</th>
					<th  class="text-center">Show time</th>
					<th  class="text-center">Date</th>

				</tr>
			</thead>
			
			<tbody>
				<tr>
					<th  class="text-center"><c:out value="${cancelItem.aOrder.orderId}"/></th>
					<th  class="text-center"><c:out value="${cancelItem.aMovieShowing.aMovie.movieTitle}"/></th>
					<th  class="text-center"><c:out value="${cancelItem.quantity}"/></th>
					<th  class="text-center">$<c:out value="${cancelItem.totalPrice}"/></th>
					<th  class="text-center"><c:out value="${cancelItem.aMovieShowing.aShowroom.aTheatre.theatreName}"/></th>
					<c:set var="startTimeDate" value="${cancelItem.aMovieShowing.startTime}"/>
					<th class="text-center"><c:out value="${fn:substring(startTimeDate,11,19)}"/></th>
					<th class="text-center"><c:out value="${fn:substring(startTimeDate,0,10)}"/></th>
				</tr>
			</tbody>
		</table>
		<br> <br>
		
		<form action="CancelOrderTransaction" method="post">
		 	<input type="hidden" name="orderNum" value="<c:out value="${cancelItem.aOrder.orderId}"/>">
			<input type="hidden" name="startTime" value="<c:out value="${cancelItem.aMovieShowing.startTime}"/>">
			<input type="hidden" name="movieShowingId" value="<c:out value="${cancelItem.aMovieShowing.id}"/>">
			<input type="hidden" name="totalPrice" value="<c:out value="${cancelItem.totalPrice}"/>">
			<input class="btn-primary btn-sm" type="submit" value="Comfirm Cancellation">
		</form>
		<br>
		
		<form action="CustomerHomePage.jsp">
			<input class="btn-primary btn-sm" type="submit" value="Discard Cancellation">
		</form>
		
</center>
	
<center>
	<br>Any enquiries? Contact us at <b>${initParam.user}</b>
</center>
	
</body>
</html>