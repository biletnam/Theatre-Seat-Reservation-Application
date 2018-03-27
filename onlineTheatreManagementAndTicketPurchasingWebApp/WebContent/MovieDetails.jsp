<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c"  uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movie Details</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
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
      
      </ul>
  	
  	</div>
</nav> <br>

<h1> Edit Movie Details </h1> <br>

<center>
<form action = "MovieDetailsUpdate" method="post">	
<b> Movie name: </b> ${MovieInDetail.movieTitle}  <br><br>
<input type="hidden" name="movieName" value="${MovieInDetail.movieTitle}"/>
<b> Movie genre: </b> <select name="movieGenre">
            <option value="${MovieInDetail.genre}">${MovieInDetail.genre}</option>
			<option value="Action">Action</option>
			<option value="Adventure">Adventure</option>
			<option value="Comedy">Comedy</option>
			<option value="Drama">Drama</option>
			<option value="Fantasy">Fantasy</option>
			<option value="Fantasy">Horror</option>
			<option value="Political">Political</option>
			<option value="Romance">Romance</option>
			<option value="Musical">Musical</option>
			
			</select> <br><br>
<b> Movie thumbnail, images, videos: </b> <img src="${MovieInDetail.imageURL}" style="width: 100px">
				<br><br>
<b> Movie description: </b> <textarea rows="10" cols="60" name="movieDescription">${MovieInDetail.description} </textarea> <br><br>

<b> MPAA Rating: </b> <select name="movieRating">
            <option value ="${MovieInDetail.MPAARating}">${MovieInDetail.MPAARating}</option>
			<option value ="G">G</option>
			<option value ="PG">PG</option>
			<option value ="PG-13">PG-13</option>
			<option value ="R">R</option>
			</select> 
		
<input type="submit" value ="Update Changes"> <br><br>
</form>
</center>

</body>
</html>