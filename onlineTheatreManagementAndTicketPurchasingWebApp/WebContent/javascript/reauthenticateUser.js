/**
 * Function to reauthenticate user as a security measure
 */

function reauthenticateFunction(){
	var userName = $("#userName").val();
	var password = $("#password").val();
	
	$.post("Login", {userName:userName, password:password}, function(data,status) {
        
		if(data == 0){
			confirm_function();
		} else {
			alert("Incorrect username or password!");
			window.location.href = "http://localhost:8080/onlineTheatreManagementAndTicketPurchasingWebApp/Login.jsp";
		}
    	
	});
}