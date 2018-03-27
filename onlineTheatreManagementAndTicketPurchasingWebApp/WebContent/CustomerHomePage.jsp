<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jstl/core_rt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Home Page</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script>
			function validateForm() {
			    var x = document.forms["movieForm"]["movieSearch"].value;
			    var y = document.forms["movieForm"]["movieDate"].value;
			    
			    if (y == "") {
			        alert("Movie Date Field must be filled out");
			        return false;
			    } 
			    
			    var date_regex = /^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
			    if(!(date_regex.test(y)))
			    {
			   	alert("Movie Date format is wrong");
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
    	<a class="navbar-brand" href="Logout">Logout</a>
    
    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
      <li class="nav-item active">
      	<a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link" href="AccountSettings.jsp">Account Settings</a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link" href="ViewOrders">ViewOrders</a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link" href="UpdateShoppingCart">Shopping Cart</a>
      </li>
    </ul>
    
    <form class="form-inline my-2 my-lg-0" name="movieForm" action ="TheatreAndMovieSearchQuery" method="post" onsubmit="return validateForm()">
    
    	<input class="form-control mr-sm-2" type="text"  name="movieSearch" placeholder="Movie Search" >
      
 		<select class="form-control mr-sm-2" name = "movieTheatre">
 		<c:forEach items="${theatreNameList}" var="name">
			<option value="${name}"><c:out value="${name}"/></option>
		</c:forEach>
 		</select>
 		
 	  	<input class="form-control mr-sm-2" id="date" type="date" placeholder="yyyy-mm-dd" name="movieDate">
 		
      	<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    
    </form>
  </div>
</nav>
<center> 
 
<c:choose>
	<c:when test="${searchError == 1}">
		<p style="color:red;"> No Result Found! <p>
	   	Please search again
	</c:when>
</c:choose>
<br>
 	<h1> Welcome! <br> You are in the Customer Home Page </h1> 
 	You can view your recently purchased orders or start with searching a movie you like
<br>
Number of user access: ${numAccess}
</center>
	
<br><br><br>

<center>
<b>Advertisement</b>
<br>

<c:choose>
	 <c:when test="${cookie.targetAd.value == null}">
	   	<img src="images/aliens.jpg" class="img-fluid" alt="Advertisement image">
	 </c:when>
	 <c:when test="${cookie.targetAd.value != null}">
	    <img src="${cookie.targetAd.value}" class="img-fluid" alt="Advertisement image">
	 </c:when>
  </c:choose>
<br><br>

<br>Any enquiries? Contact us at <b>${initParam.user}</b>
<br>
</center>

</body>
</html>