/**
 * Javascript function for changing users' password
 */

function changePasswordFunction(){
	var currentPassword = $("#currentPassword").val();
	var newPassword = $("#newPassword").val();
	var retypePassword = $("#retypePassword").val();
	
	$.post("ChangePassword", {currentPassword:currentPassword, newPassword:newPassword, retypePassword:retypePassword}, function(data,status) {
        
		if(data == 0){
			alert("Password Changed Successfully!");
			$("#currentPassword").val("");
			$("#newPassword").val("");
			$("#retypePassword").val("");
		} else if (data == 1){
			alert("New password does not match");
		} else {
			alert("Current password does not match");
		}
    	
	});
}