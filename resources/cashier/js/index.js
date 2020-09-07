$(document).ready(function(){
	// Sidebar
	$("#menu-toggle").click(function(e) {
	    e.preventDefault();
	    $("#wrapper").toggleClass("toggled");
	});
	$('#job').on('click',function(){
		window.location.assign("/guidafederico/view/personalarea/cashier/job/");
	})
	$('#data').on('click', function(){
		$.ajax({
			type: "GET",
			url: "/guidafederico/view/personalarea/update",
			data: {action: "userData"},
			dataType: "json",
			success: function (data) {
				$('#imgDiv').html("");
				$('#title').text("Modifica account!");
				var cnt ="<div  id=\"userData\" class=\"text-center border rounded-0 shadow-sm profile-box\" style=\"width: 100%;height: 575px;background-color: #ffffff;margin: 5px;\">"+
			    "<div style=\"height: 50px;background-image: url('"+contextPath+"/resources/personalarea/img/bg-pattern.png');background-color: rgba(54,162,177,0);\"></div>"+
			    "<div><h4>"+data.email[0]+"</h4></div><div style=\"height: 80px;padding: 22px;\">"+
			    "<div><p style=\"font-size: 18px;\">Nome: "+data.name[0]+"</p></div><div><p style=\"font-size: 18px;\">Cognome: "+data.surname[0]+"</p></div>"+
			    "<div><p style=\"font-size: 18px;\">Et&agrave;: "+data.age[0]+"</p></div><div>"+
			    "<p style=\"font-size: 18px;\">Data iscrizione: "+data.time[0]+"</p><button id=\"changeEmail\" class=\"btn btn-primary\" type=\"button\">Modifica Email</button>" +
			    "<button id=\"changePassword\" class=\"btn btn-primary\" type=\"button\" style=\"margin-bottom: 0px;margin-left: 18px;\">Modifica Password</button></div></div></div>";
				$('#content').html(cnt);
				jQuery.validator.setDefaults({
				    onfocusout: function (e) {
				        this.element(e);
				    },
				    onkeyup: false,
				
				    highlight: function (element) {
				        jQuery(element).closest('.form-control').addClass('is-invalid');
				    },
				    unhighlight: function (element) {
				        jQuery(element).closest('.form-control').removeClass('is-invalid');
				        jQuery(element).closest('.form-control').addClass('is-valid');
				    },
				
				    errorElement: 'div',
				    errorClass: 'invalid-feedback',
				    errorPlacement: function (error, element) {
				        if (element.parent('.input-group-prepend').length) {
				            $(element).siblings(".invalid-feedback").append(error);
				            //error.insertAfter(element.parent());
				        } else {
				            error.insertAfter(element);
				        }
				    },
				});
				$('#changeEmail').on('click',function(){
					$(this).hide();
					$('#changePassword').hide();
					var formEmail="<form method=\"POST\" id=\"changeEmailForm\">" +
							"<div class=\"form-group\"><label for=\"email\">Nuova email</label>" +
							"<input id=\"email\" type=\"text\" name=\"email\" class=\"form-control\">" +
							"</div>" +
							"<div class=\"form-group\"><label for=\"confirmEmail\">Conferma email</label>" +
							"<input id=\"confirmEmail\" type=\"text\" name=\"confirmEmail\" class=\"form-control\">" +
							"</div>" +
							"<div class=\"form-group\">" +
							"<input type=\"submit\" name=\"emailSubmit\" class=\"btn btn-primary\">" +
							"</div></form><button id=\"hideFormEmail\" class=\"btn btn-primary\">Annulla</button>";
					$('#changeEmail').after(formEmail);
					$('#changeEmailForm').validate({
						rules : {
							email : {
								required : true,
								pattern: /(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/
							},
							confirmEmail : {
								required : true,
								equalTo : "#email"
							}
						},
						messages: {
							email : {
								required : "Inserisci email",
								pattern : "Email non valida!"
							},
							confirmEmail : {
								required : "Reinserisci email",
								equalTo : "Le email inserite non corrispondono"
							}
						},
						
						submitHandler: function(form) {
							 $.ajax({
						            url: "/guidafederico/view/personalarea/update?action=email",
						            type: form.method,
						            data: $(form).serialize(),
						            success: function(response) {
						            	var message;
						            	if(response=="false")
						            		message="</br></br><div class=\"alert alert-danger\" role=\"alert\">"+
	            				 						"L'email inserita non pu&ograve; essere utilizzata"+
		            				 		        "</div>";
						            	else if (response=="true")
						            		message="</br></br><div class=\"alert alert-success\" role=\"alert\">"+
			            								"Email aggiornata con successo!</br>"+
			            								"A breve verrai reinderizzato nella pagina di login!<br>"+
			            								"Effettua nuovamente il login per usufruire dei servizi!"+
			            							"</div>";
						            	else
						            		message="</br></br><div class=\"alert alert-danger\" role=\"alert\">"+
				 										"Errore nella richiesta"+
    				 						        "</div>";
						                $('#changePassword').after(message);
						                $('#changeEmailForm').hide();
						                $('#hideFormEmail').hide();
										$('#changeEmail').show();
										$('#changePassword').show();
										setTimeout(function(){
										       location.reload();
										   },3000);
						            }            
						        });
				        }
					})
					$('#hideFormEmail').on('click',function(){
						$('#changeEmailForm').hide();
						$('#changeEmail').show();
						$('#changePassword').show();
						$(this).remove();
					})
				})
				$('#changePassword').on('click',function(){
					$(this).hide();
					$('#changeEmail').hide();
					var formEmail="<form method=\"POST\" id=\"changePasswordForm\" >" +
							"<div class=\"form-group\"><label for=\"password\">Nuova password</label>" +
							"<input id=\"password\" type=\"password\" name=\"password\" class=\"form-control\">" +
							"</div>" +
							"<div class=\"form-group\"><label for=\"confirmPassword\">Conferma password</label>" +
							"<input id=\"confirmPassword\" type=\"password\" name=\"confirmPassword\" class=\"form-control\">" +
							"</div>" +
							"<div class=\"form-group\">" +
							"<input type=\"submit\" name=\"passwordSubmit\" class=\"btn btn-primary\">" +
							"</div></form><button id=\"hideFormPassword\" class=\"btn btn-primary\">Annulla</button>";
					$('#changePassword').after(formEmail);
					$('#hideFormPassword').on('click',function(){
						$('#changePasswordForm').hide();
						$('#changeEmail').show();
						$('#changePassword').show();
						$(this).remove();
					})		
					$('#changePasswordForm').validate({
						rules : {
							password : {
								required : true
							},
							confirmPassword : {
								required : true,
								equalTo : "#password"
							}
						},
						messages: {
							password : "Inserisci la password",
							confirmPassword : {
								required : "Reinserisci la password",
								equalTo : "Le password inserite non corrispondono"
							}
						},
						
						submitHandler: function(form) {
							$.ajax({
					            url: "/guidafederico/view/personalarea/update?action=password",
					            type: form.method,
					            data: $(form).serialize(),
					            success: function(response) {
					            	var message;
					            	if(response=="false")
					            		message="</br></br><div class=\"alert alert-danger\" role=\"alert\">"+
            				 						"La password corrisponde a quella attuale"+
            				 					"</div>";
					            	else if (response=="true")
					            		message="</br></br><div class=\"alert alert-success\" role=\"alert\">"+
		            								"Password aggiornata con successo!</br>"+
		            								"A breve verrai reinderizzato nella pagina di login!<br>"+
		            								"Effettua nuovamente il login per usufruire dei servizi!"+
		            							"</div>";
					            	else
					            		message="</br></br><div class=\"alert alert-danger\" role=\"alert\">"+
        				 							"Errore nella richiesta"+
        				 						"</div>";
					            	$('#changePassword').after(message);
					                $('#changePasswordForm').hide();
					                $('#hideFormPassword').hide();
									$('#changeEmail').show();
									$('#changePassword').show();
									setTimeout(function(){
									       location.reload();
									   },3000);
					            }          
					        });
				        }
					})
				})
			}
		});
	})
})