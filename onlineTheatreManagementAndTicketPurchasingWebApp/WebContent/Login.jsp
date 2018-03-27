<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DuTos Ticket Purchasing Application</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script>
			function validateForm() {
			    var x = document.forms["userForm"]["userName"].value;
			    var y = document.forms["userForm"]["password"].value;
			    if (x == "") {
			        alert("Username must be filled out");
			        return false;
			    }
			    
			    if (y == "") {
			        alert("Password must be filled out");
			        return false;
			    }
			}
</script>
</head>

<body>

	<center>
	<br>
		 <h1 class="form-signin-heading">Welcome</h1>

<br>
<div id="userForm">
		<form  name="userForm" action=Login onsubmit="return validateForm()"
			method="post">
             <label for="name" class="sr-only">Username</label>
			 <input type="text" id="name"name=userName placeholder="Username" value="${cookie.rememberMe.value}"><br> 
			 <br>
			 <input type=password name=password placeholder="Password"><br> 
			<br>
			<input type=checkbox name=rememberMe> Remember Me <br><br>
			<input type=submit value=Login class="btn btn-success btn-xs"> <br>
		</form>
</div>
		<br> <br>

		<!-- Navigation links -->
		<a href="Registration.jsp"> Register to join now! </a> <br>

		<br>Any enquiries? Contact us at <b>${initParam.user}</b>
	</center>


	
</body>


</html>