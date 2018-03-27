/**
 * Function to place user's order and store information to the database
 */

function place_order_function() {
    var cardNumber  = $("#cardNumber").val();
    var billingStreetAddress = $("#billingStreetAddress").val();
    var billingAptSuite = $("billingAptSuite").val();
    if(billingAptSuite == null){
    	billingAptSuite = "";
    }
    var billingCity = $("#billingCity").val();
    var billingState = $("#billingState").val();
    var billingCountry  = $("#countryId").val();
    var billingZip = $("#billingZip").val();
    var billingAddress = billingStreetAddress + " " + billingAptSuite + "<br>" + billingCity + ", " + billingState + "<br>"
	+ billingCountry + ", " + billingZip;
    	
    // Sending request to another app named "FormjQueryResponse"
    // Before sending request, run the app "FormjQueryResponse" on server first
    $.post("PlaceOrder", {cardNumber:cardNumber, billingStreetAddress:billingStreetAddress, billingAptSuite:billingAptSuite, billingCity:billingCity, billingState:billingState, billingCountry:billingCountry, billingZip:billingZip}, function(data,status) {
        
    	$("#results").show();
    	$("#loginForm").hide();
    	
        $("h1").html("Transaction Confirmation");
        $("h1").append("<br> Order Number: " + data);
		$("#results").html("<h2><b> Congratulations! Your order has been placed! </b><h2>" 
		+ "<br>Billed to: " + billingAddress)
		$("#results").append("<br><br> <button class=\"btn btn-info\" onclick=\"printFunction()\"> Print </button>");
        	        				
    });
}