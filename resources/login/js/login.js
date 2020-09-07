$(document).ready(function(){
	$('.forgot').one('click', function(){
			var form = $('.login-clean form');
			form.prop('id','form');
			$('.login-clean .form-group').remove();
			$('.forgot').remove();
			$(form).attr('action','');
			var action = $(form).attr('action');
			$(form).append("<div class=\"form-group\"><input  class=\"form-control\" type=\"email\" name=\"email\" placeholder=\"Email\" required></div>" +
			"<div class=\"form-group\"><button class=\"btn btn-primary btn-block\" type=\"submit\" style=\"background-color: rgb(255,15,0);\">Invia email</button></div>");
			$(document).on('submit', "#form", function(){
					$.ajax({
			            url: contextPath+"/view/personalarea/data?action=password",
			            type: this.method,
			            data: $(form).serialize(),
			            success: function(response) {
			            	form.prop('id','security');
			            }
					});
			})
	})
})