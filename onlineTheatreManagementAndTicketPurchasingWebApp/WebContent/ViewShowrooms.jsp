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
<title>View Showroom</title>
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
</nav>

<h1> View Showroom </h1>
<br>
<center>
<table border="1">
		<thead>
			<tr>
				<th class="text-center"> Show Room Number</th>
				<th class="text-center"> Avalible Seats</th>
				<th class="text-center"> Theatre Number</th>
			</tr>
		</thead>
		<c:forEach items="${viewShowRoom}" var="viewShowrooms" varStatus="status">
			<tbody id="select">
				<tr> 
				<th class="text-center"><c:out value="${viewShowrooms.showroomId}"/> </th>
				<th class="text-center"><c:out value="${viewShowrooms.availableSeats}"/> </th>
				<th class="text-center"><c:out value="${viewShowrooms.theatreBuilding}"/> </th>
				<th class="text-center">
				<form action="ManageShowtimes" method="post">
				<input type="hidden" name="showroomId" value=<c:out value="${viewShowrooms.showroomId}"/>>
				
				<input type="hidden" name="availableSeats" value=<c:out value="${viewShowrooms.availableSeats}"/>>
				<input type="hidden" name="theatreBuildingNumber" value=<c:out value="${viewShowrooms.theatreBuilding}"/>>
				<input  class="btn btn-info "type="submit" value="Manage ShowTimes">
				</form></th>
			</tr>
		</tbody>
		</c:forEach>
</table>
</center>
<br>
</body>
</html>