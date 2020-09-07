$(document).ready(function(){
	$('#passRepeat').on('blur', function(){
		if($('#pass').val()!=$('#passRepeat').val()){
			$('#message').text("La password inserita non coincide!");
			$('#passRepeat').val("");
		}else{
			$('#message').text("");
		}
	})	
})

