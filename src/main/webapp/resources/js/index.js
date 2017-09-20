 $("#login-button").click(function(event){
		 event.preventDefault();
	 
	 $('form').fadeOut(500);
	 $('.wrapper').addClass('form-success');
	
	 setTimeout(function(){  document.getElementById("form_id").submit();},1500);
	
			
	 
});