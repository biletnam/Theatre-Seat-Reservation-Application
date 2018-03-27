<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jstl/core_rt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movie Search Results</title>
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
        	<a class="nav-link" href="UpdateShoppingCart">Shopping Cart</a>
      	</li>
      	
       	<li class="nav-item">
       		<a class="navbar-brand" href="Logout">Logout</a>
      	</li>
    </ul>
  </div>
  
</nav>
	<h1>Search Results</h1>
	<table border="1" class="table table-bordered table-striped table-hover">
		<thead>
			<tr>
				<th class="text-center">Movie name</th>
				<th class="text-center">Names of the theatre building showing the movie </th>
				<th class="text-center">Theatre number in the building showing the movie</th>
				<th class="text-center">Show time of the movie</th>
				<th class="text-center">Number of available seats</th>
				<th class="text-center">Price per ticket</th>
				<th class="text-center">Thumbnail of the movie poster</th>
				<th class="text-center">Detail</th>
			</tr>
		</thead>
		<c:forEach items="${movieSearchList}" var="movie">
			<tbody>
				<tr> 
				<th class="text-center"><c:out value="${movie.aMovie.movieTitle}"/> </th>
				<th class="text-center"><c:out value="${movie.aShowroom.aTheatre.theatreName}"/></th>
				<th class="text-center"><c:out value="${movie.aShowroom.showroomId}"/></th>
				<th class="text-center"><c:out value="${movie.startTime}"/></th>
				<th class="text-center"><c:out value="${movie.aShowroom.availableSeats}"/></th>
				<th class="text-center"><c:out value="${movie.price}"/></th>
				<th class="text-center"><img src=<c:out value="${movie.aMovie.imageURL}"/> style="width: 100px"></th>
				<th class="text-center"><form action="MovieSearchResult" method="post">
				<input type="hidden" name="movieName" value="<c:out value="${movie.aMovie.movieTitle}"/>">
				<input type="hidden" name="movieGenre" value="<c:out value="${movie.aMovie.genre}"/>">				
				<input type="hidden" name="imageURL" value="<c:out value="${movie.aMovie.imageURL}"/>">
				<input type="hidden" name="theatreName" value="<c:out value="${movie.aShowroom.aTheatre.theatreName}"/>">
				<input type="hidden" name="showroomId" value="<c:out value="${movie.aShowroom.showroomId}"/>">
				<input type="hidden" name="startTime" value="<c:out value="${movie.startTime}"/>">
				<input type="hidden" name="endTime" value="<c:out value="${movie.endTime}"/>">
				<input type="hidden" name="availableSeats" value="<c:out value="${movie.aShowroom.availableSeats}"/>">
				<input type="hidden" name="price" value="<c:out value="${movie.price}"/>">
					
						<input   class="btn btn-info " type="submit" value="View Details">
					</form></th>
			</tr>
		</tbody>
		</c:forEach>
	</table>
	<br>

<center>
<br>Any enquiries? Contact us at <b>${initParam.user}</b>
<br>
</center>
</body>
</html>