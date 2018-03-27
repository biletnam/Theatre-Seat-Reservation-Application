/**
 *  Function that add user's review to a particular movie with AJAX
 */

function addReview() {
	var movieName = $("#movieName").val();
	var rating = $("#rating").val();
	var review  = $("#review").val();
	var overallRating = $("#overallRating").val();		
	var totalRatingCount = $('#totalRatingCount').val();
	
	if(isNaN(parseFloat(overallRating))){
		overallRating = 0;
		totalRatingCount = 0;
	}
	
	var updatedOverallRating = (parseFloat(overallRating)+parseInt(rating))/(parseInt(totalRatingCount)+1);
	
	// Sending request to servlet of the same app
	$.get("CustomerReview", {movieName:movieName, rating:rating, review:review}, function(data,status) {
				    
		if (data == 1) {
			alert("Rating must be between 1 to 5. Please try again.");
		} else if (data == 2) {
			alert("Review must be under 255 characters. Please try again.");
		} else {
			alert("Review Successfully Added!");
			$("#reviewDisplay").html(data);
			$('#overallReview').html(updatedOverallRating)
		}
				    		
	});
}