$(document).ready(function() { 

	$(document).on('click','#make_call',function(e){
		var delmitter, error_code, myUrl;  // Declare variables at top of the function, prevent hoisting
		e.preventDefault();
		
		delmitter='?'
		error_code = $.trim($('#error_code').val());	//jQuery CSS Selectors - findng by ID
		myUrl = $('#url').val();

		if ($.trim(myUrl)===''){
			myUrl='/';
		}
		
		if (error_code!==''){
			if (myUrl.indexOf(delmitter)!==-1){
				delmitter='&'
			}
			myUrl += delmitter +"httpErrorCode="+error_code;
		}

		// jQuery Ajax Call
		$.ajax({
		  type: $('#verb option:selected').text(),
		  url: myUrl,
		  data: $('#request_body_data').val()
		}).done(function( msg ) {
			$("#results").append("<p>"+JSON.stringify(msg)+"</p>");
		}).error(function(msg){
			console.log('ERROR:');
			console.log(msg);
		});
		
	});

});