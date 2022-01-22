$(document).ready(function(){
	$('a.popup-btn').click(function () {
		// Getting the variable's value from a link 
		var boxSelecter = $(this).attr('href');
		tdPopup(boxSelecter);
		return false;
	});
	// When clicking on the button close or the mask layer the popup closed
	$('a.close, #mask').live('click', function () {
		$('#mask , .popup').fadeOut(300, function () {
		    $('#mask').remove();
		});
		return false;
	});
});
function tdPopup(boxSelecter){
	//Fade in the Popup and add close button
	$(boxSelecter).fadeIn(300);
	
	//Set the center alignment padding + border
	var popMargTop = ($(boxSelecter).height() + 24) / 2;
	var popMargLeft = ($(boxSelecter).width() + 24) / 2;
	$(boxSelecter).css({
	    'margin-top': -popMargTop,
	    'margin-left': -popMargLeft
	});
	// Add the mask to body
	$('body').append('<div id="mask"></div>');
	$('#mask').fadeIn(300);
}