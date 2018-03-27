<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration page for DT Theatre</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script>
			function validateForm() {
			    var x = document.forms["registrationForm"]["phoneNumber"].value;
			    var y = document.forms["registrationForm"]["emailAddress"].value;
			    var z = document.forms["registrationForm"]["zipCode"].value;

			    if (x == "") {
			        alert("Phone Number Field must be filled out");
			        return false;
			    }
			    
			    if (y == "") {
			        alert("Email Address Field must be filled out");
			        return false;
			    } 
			    
			    if (z == ""){
			    	alert("Postal Code Field must be filled out");
			    	return false;
			    }
			    
			    var phone_regex = /^(\([0-9]{3}\) |[0-9]{3}-)[0-9]{3}-[0-9]{4}/;
			    if(!(phone_regex.test(x)))
			    {
			   	alert("Phone Number format is wrong");
			    return false;
			    }
			   
			    var email_regex = /^(([^<>()[\]{}'^?\\.,!|//#%*-+=&;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/;
				if(!(email_regex.test(y)))
				{
				alert("Email format is wrong");
				return false;
				}
				
				var zip_regex = /(^\d{5}$)|(^\d{5}-\d{4}$)/;
				if(!(zip_regex.test(z)))
				{
				alert("Postal Code format is wrong");
				return false;
				}
			}
</script>
</head>

<body>

	<center>
		<h1 class="form-signin-heading">DT THEATRE's REGISTRATION FORM</h1> <br>

		<form action=Register method="post" name="registrationForm" onsubmit="return validateForm()">

			First Name: <input type=text name=firstName><br> <br>
			Last Name: <input type=text name=lastName><br> <br>
			User Name: <input type=text name=userName><br> <br>
			Password: <input type=password name=password><br> <br>
			Street: <input type=text name=street><br> <br>
			City: <input type=text name=city><br> <br>
			State: <input type=text name=state><br> <br>
			Postal Code: <input type=text name=zipCode><br> <br>
			
			Phone number: <input type=text name=phoneNumber placeholder="xxx-xxx-xxxx"><br> <br>
			Email Address: <input name=emailAddress> <br><br>
			<input type=submit value=Register class="btn btn-success btn-xs"> <br>

		</form>

		<br> <br>

		<!-- Navigation links -->
		<a href="Login.jsp"> Login Page </a> <br>

		<br>Any enquiries? Contact us at <b>${initParam.user}</b>
	</center>
</body>
</html>