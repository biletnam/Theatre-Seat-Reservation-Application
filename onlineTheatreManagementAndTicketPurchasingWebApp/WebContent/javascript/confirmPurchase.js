/**
 * Function to confirm purchase and credit card is billed through another bank web application
 */

function confirm_function() {
	var totalCost = $("#totalCost").val();
	var cardHolderFullName = $("#cardHolderFullName").val();
	var cardType = $("#cardType").val();
	var cardNumber  = $("#cardNumber").val();
	var securityCode  = $("#securityCode").val();
	var expirationDate  = $("#expirationDate").val();
	
    // Sending request to another app named "BankWebApp"
    // Before sending request, run the app "BankWebApp" on server first
    $.post("../BankWebApp/Bank", {totalCost:totalCost, cardHolderFullName:cardHolderFullName, cardType:cardType, cardNumber:cardNumber, securityCode:securityCode, expirationDate:expirationDate}, function(data,status) {
    	
    	if(data != 0){
    		$("#results").show();
        	$("#loginForm").hide();
    	}
    	
    	if(data == 0){
    		place_order_function();
	    } else if (data == 1) {
	    	$("h1").html("Transaction Failed");
	    	$("#results").html("<h2><b>Transaction was not successful.</b><h2> <br>Reason: Bank information not valid.</b><h2>");
	    } else if (date == 2){
	    	$("h1").html("Transaction Failed");
	    	$("#results").html("<h2><b>Transaction was not successful.</b><h2> <br>Reason: Bank balance not sufficient.");
	    }
    		
	});
}