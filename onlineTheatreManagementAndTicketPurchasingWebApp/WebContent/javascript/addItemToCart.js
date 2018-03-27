/**
 *  Function that add user's selection to shopping cart with AJAX
 */

function getData() {
		var addToCart = $("#addToCart").val();
		var quantity = $("#ticketNumber").val();
		var movieName  = $("#movieName").val();
		var theatreName  = $("#theatreName").val();	
		var showroomId  = $("#showroomId").val();
		var startTime  = $("#startTime").val();
		var price  = $("#price").val();
		var imageURL  = $("#imageURL").val();
		var availableSeats  = $("#availableSeats").val();
				
		// Sending request to servlet of the same app
		$.get("UpdateShoppingCart", {addToCart:addToCart, ticketNumber:quantity, movieName:movieName, theatreName:theatreName, showroomId:showroomId, startTime:startTime, price:price, imageURL:imageURL, availableSeats:availableSeats}, function(data,status) {
			    
			if(data == 0){
				alert("Item added to cart");
			} else if (data == 1) {
				alert("Item cannot be added to the cart: Exceeded theatre capacity")
			} 
			    		
		});
}