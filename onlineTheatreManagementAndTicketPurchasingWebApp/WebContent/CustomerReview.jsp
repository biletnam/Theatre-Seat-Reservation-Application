<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jstl/core_rt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a Review for a Movie</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script>
			function validateForm() {
				var x = document.forms["reviewForm"]["rating"].value;
			    var y = document.forms["reviewForm"]["review"].value;

			    if (x == "") {
			        alert("Rating Field must be filled out");
			        return false;
			    }
			    
			    if (y == "") {
			        alert("Review Field must be filled out");
			        return false;
			    } 
			    
			    if((x<1) || (x>5))
			    {
			   	alert("Rating should be between 1 and 5");
			    return false;
			    }
			}
</script>
</head>
<body>
 <c:choose>
	 <c:when test="${userInfo.userName != null}">
	   	<a class="navbar-brand">Hi ${userInfo.userName}!</a>
	 </c:when>
	 <c:when test="${userInfo.userName == null}">
	    <c:redirect url="Login.jsp"/>
	 </c:when>
  </c:choose>
  
<center><h1> Review Submission for <c:out value="${movieDetail.aMovie.movieTitle}"/></h1></center>
<hr>
	<form action="CustomerReview" method="post" name="reviewForm" onsubmit="return validateForm()"> 
	
		<b>Review: </b> <i>limit to 255 characters</i><br>
		<textarea rows="10" cols="60" name="review"> </textarea> <br>
		<br>
		<b>Rating: </b>
		<input type="text" name="rating" placeholder="out of 5">/5 <br>
		<br>
		<input class="btn btn-success btn-xs" style="text-align:right" type="submit" value="Submit"> 
		<input type="hidden" name="movieName" value="<c:out value="${movieDetail.aMovie.movieTitle}"/>">
	
		
	</form>
	<br>
	<form action="MovieDetailsAndSelection.jsp"  method="post">
			<input class="btn btn-success btn-xs" type="submit" value="Cancel">
		</form>
		<br>
	
</body>
</html>
