<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jstl/core_rt" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Owner Home Page</title>
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
		<a class="navbar-brand" href="Logout">Logout</a>
		
    	<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
      		<li class="nav-item active">
        		<a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
      		</li>
 
      </ul>
  	
  	</div>
</nav>

<center>

<h1> Welcome Theatre Owner! </h1>

<form action = "MovieSearchResultOwner"  method="post">
	Search movie in database: <input type = "text" name="movieSearch"> 
	<input  class="btn btn-info " type="submit" value ="Search Movie">
</form>

<br>

<form action = "ViewTheatreDetails"  method="post">
	<input  class="btn btn-info " type="submit" value ="View Your Theatre Buildings"> <br><br>
</form>

<form action = "AddMovie.jsp"  method="post">
	<input  class="btn btn-info "type="submit" value ="Add a new movie"> <br><br>
</form>

</center>

</body>
</html>