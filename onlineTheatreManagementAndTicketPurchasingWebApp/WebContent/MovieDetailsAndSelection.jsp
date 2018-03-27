<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jstl/core_rt" %> 
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movie Details And Selection</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="javascript/addItemToCart.js"></script>
<script src="javascript/addReview.js"></script>

<script>
			function validateForm() {
			    var x = document.forms["searchForm"]["ticketNumber"].value;
			    if (x == "") {
			        alert("Ticket Number must be filled out");
			        return false;
			    } else if (x <= 0){
			    	alert("Ticket Number must be more than 0");
			    	return false;
			    }
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
	        	<a class="nav-link" href="MovieSearchResults.jsp">Back</a>
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
	<h1> ${movieDetail.aMovie.movieTitle} </h1>
</center>

<center>
	<div class="container">
		<img src=<c:out value="${movieDetail.aMovie.imageURL}"/>>
	</div>

	<h4><a href="#movie" data-toggle="collapse"> Movie Details and Information </a></h4>
	<div id="movie" class="collapse">

		<u>Movie Name</u> <br> 
		<b>${movieDetail.aMovie.movieTitle}</b> <br> 
		<u>Movie Rating</u> <br> 
		<b>${movieDetail.aMovie.MPAARating}</b> <br> 
		<u>Theatre Name & Showroom Number</u> <br> 
		<b>${movieDetail.aShowroom.aTheatre.theatreName} - No. ${movieDetail.aShowroom.showroomId}</b> <br>
		<u>Showtime</u> <br> 
		<b>${movieDetail.startTime}</b> <br>
		<u>Price per seat</u> <br> 
		<b>${movieDetail.price}</b> <br> 
		<u>Available seats</u> <br>
		<b>${movieDetail.aShowroom.availableSeats}</b> <br>
		
		 <br> <u>Movie Description</u> <br>
		 <b> ${movieDetail.aMovie.description}</b>

	</div>

</center>
<br>

<div align=center>
	Numbers of tickets: <input type="text" id="ticketNumber" placeholder = "0">
	<input type="hidden" id="addToCart" value="1"/>
	<input type="hidden" name="movieName" id="movieName" value="<c:out value="${movieDetail.aMovie.movieTitle}"/>">
	<input type="hidden" name="theatreName" id="theatreName" value="<c:out value="${movieDetail.aShowroom.aTheatre.theatreName}"/>">
	<input type="hidden" id="showroomId" value="<c:out value="${movieDetail.aShowroom.showroomId}"/>">
	<input type="hidden" id="startTime" value="<c:out value="${movieDetail.startTime}"/>">
	<input type="hidden" id="price" value="<c:out value="${movieDetail.price}"/>">
	<input type="hidden" id="imageURL" value="<c:out value="${movieDetail.aMovie.imageURL}"/>">
	<input type="hidden" id="availableSeats" value="<c:out value="${movieDetail.aShowroom.availableSeats}"/>">
	<br><br>
	<input class="btn btn-success btn-xs" type="button" value="Add to cart" onClick="getData()">
		
	<br><br>

<!-- Update shopping cart using AJAX -->
<form action="UpdateShoppingCart" method="post">
	<input class="btn btn-success btn-xs" type="submit" value="Checkout">
</form>
<br><br>

<hr>

<b> Viewer Reviews </b> <br> 
Overall Rating: <span id="overallReview"> ${movieDetail.overallReview} </span>/5 stars <br>

<hr>
	<c:forEach items="${movieDetail.reviewList}" var="review" varStatus="status">
		
		<h4><a href="#${status.count}" data-toggle="collapse"> <c:out value="${review.aUser.firstName}"/> <c:out value="${review.aUser.lastName}"/></a></h4>
		<div id="${status.count}" class="collapse">
			<br> <c:out value="${review.reviewDate}"/> <br> Rating: <c:out value="${review.rating}"/>/5 <br>
			<c:out value="${review.review}"/>
		</div>
		
		<input type="hidden" id="totalRatingCount" value="<c:out value="${status.count}"/>">
		
		<hr>
	</c:forEach>
		
	<div id="reviewDisplay">
	
	<center><h3><b> Review Submission for <c:out value="${movieDetail.aMovie.movieTitle}"/></b></h3></center>
	
	<hr>
	
	<input type="hidden" id="overallRating" value="<c:out value="${movieDetail.overallReview}"/>">
	
	<b>Rating: </b>
	<input type="text" name="rating" id="rating" placeholder="out of 5">/5 <br>
	<br>
		
	<b>Review: </b> <i>limit to 255 characters</i><br>
	<textarea rows="10" cols="60" name="review" id="review"> </textarea> <br>
	<br>
		
	<input class="btn btn-success btn-xs" type="button" value="Submit" onClick="addReview()">
	</div>
</div>
	
<center>
	<br>Any enquiries? Contact us at <b>${initParam.user}</b>
</center>

</body>
</html>