<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c"  uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cancel showing</title>
<link rel="stylesheet" href="css/myStyle.css">
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
		<a class="navbar-brand" href="Logout">Logout</a>
		
    	<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
      		<li class="nav-item active">
        		<a class="nav-link" href="OwnerHomePage.jsp">Home <span class="sr-only">(current)</span></a>
      		</li>
 
 			<li class="nav-item active">
        		<a class="nav-link" href="ManageShowtimes.jsp"> Manage Showtime <span class="sr-only">(current)</span></a>
      		</li>
      	</ul>
  	
  	</div>
</nav>
<center>
<h1> Cancel Showing Page </h1>
<table border="1">
		<thead>
			<tr>
			<th class="text-center">Movie Playing</th>
				<th class="text-center">Start Time</th>
				<th class="text-center">End Time</th>
				<th class="text-center">Number of ticket purchased</th>
				<th class="text-center">Movie Details</th>
			</tr>
		</thead>
		<tbody>
			<tr>
			    <th class="text-center"> ${aMovieShowing.aMovie.movieTitle} </th>
				<th class="text-center"> ${aMovieShowing.startTime} </th>				
				<th class="text-center"> ${aMovieShowing.endTime} </th>
				<th class="text-center"> ${aMovieShowing.numberPurchased} </th>
				<th class="text-center"> ${aMovieShowing.aMovie.description}</th>
				<th class="text-center">				
				<input type="hidden" name="startTime" value="${aMovieShowing.startTime}">
				<input type="hidden" name="showroomID" value="${aMovieShowing.showroomID}">
				<input type="hidden" name="endTime" value="${aMovieShowing.endTime}">
				<input type="hidden" name="numberPurchased" value="${aMovieShowing.numberPurchased}">
				<input type="hidden" name="movieTitle" value="${aMovieShowing.aMovie.movieTitle}">				
				</th>
			</tr>
		</tbody>
</table>

<br>

<form action="CancelShowing"  method="post">
<input type="submit" value="Confirm Cancellation">
</form> <br>

<form action="ManageShowtimes.jsp">
<input type="submit" value="Discard Cancellation">
</form> <br>

</center>
</body>
</html>