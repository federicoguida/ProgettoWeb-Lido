$(document).ready(function(){
	// Card
	$(".hover").mouseleave(
			  function () {
			    $(this).removeClass("hover");
			  }
	);
	// Sidebar
	$("#menu-toggle").click(function(e) {
	    e.preventDefault();
	    $("#wrapper").toggleClass("toggled");
	});
	
	$('#prenotation').on('click',function(){
		$.ajax({
			type: "POST",
			url: "/guidafederico/view/personalarea/user/management",
			data: {action: "prenotation"},
			dataType: "json",
			success: function (data) {
				$('#title').text("Riepilogo prenotazioni!");
				var table="<div class=\"table-responsive\">"+
		        "<table id=\"prenotationTable\" class=\"table\">"+
		        "<thead>"+
		            "<tr><th>ID Prenotazione</th><th>Posti</th><th>Orario pagamento ordine</th><th>Stato</th></tr>" +
		        "</thead>" +
		        "<tbody>";
				
				$.each( data, function( key, val ) {
					table+="<tr>"
						+ "<td>" + val.idprenotation + "</td>"
						+ "<td>";
					$.each( val.location_idlocation, function( key, val ) {
						table+=val + " ";
					})
					table+="</td>"+
					"<td>"+val.time+"</td>";
					if(val.out=="0")
						table+="<td>Attivo</td></tr>";
					else
						table+="<td>Terminato</td></tr>";
				})
				table+="</tbody></table></div>";
				$('#content').html(table);
				$('#prenotationTable').DataTable({
						 "order": [[ 2, "desc" ]]
				});
			}
		});
	})
	
	$('#order').on('click',function(){
		$.ajax({
			type: "POST",
			url: "/guidafederico/view/personalarea/user/management",
			data: {action: "order"},
			dataType: "json",
			success: function (data) {
				$('#title').text("Riepilogo ordini!");
				var table="<div class=\"table-responsive\">"+
		        "<table id=\"orderTable\" class=\"table\">"+
		        "<thead>"+
		            "<tr><th>ID Ordine</th><th>Orario ordine</th><th>Pagato</th><th>Consegnato</th><th>Maggiori dettagli</th></tr>" +
		        "</thead>" +
		        "<tbody>";
				$.each( data, function( key, val ) {
					table+="<tr><td>"+val.order_id+"</td>"
				     + "<td>"+val.time+"</td>";
				if(val.orderpay=="0")
					 table+= "<td>No</td>";
				else
					 table+= "<td>Si</td>";
				if(val.delivered=="0")
					 table+="<td>No</td>";
				else
					 table+="<td>Si</td>";
				table+="<td id=\"td"+val.order_id+"\"><button id=\""+val.order_id+"\" class=\"btn btn-primary\">Clicca qui</button></td></tr>";
				})
				
				table+="</tbody></table></div>";
				$('#content').html(table);
				$('#orderTable :button').on('click', function(){
					var $id=this.id;
					var button=this;
					$(button).hide();
					$.ajax({
						type: "POST",
						url: "/guidafederico/view/personalarea/user/management",
						data: {action: "info", order_id: $id},
						dataType: "json",
						success: function (data) {
							var infoTable="<div id=\"infoDiv"+$id+"\"class=\"table-responsive\">"+
					        "<table class=\"table\">"+
					        "<thead>"+
					            "<tr><th>Prodotto</th><th>Quantit&agrave;</th></tr>" +
					        "</thead>" +
					        "<tbody>";
							$.each( data, function( key, val ) {
								infoTable+="<tr><td>"+val.name+"</td>"
							     + "<td>"+val.quantity+"</td></tr>";
							})
							infoTable+="</tbody></table><p>Istruzioni cuoco: </br> "+data[0].instruction+"</p></br></br>" +
									"<button id=\"hideInfo"+$id+"\" class=\"btn btn-primary\">Torna indietro</button></div>";
							$('#td'+$id).append(infoTable);
							$("#hideInfo"+$id).on('click',function(){
								$('#infoDiv'+$id).remove();
								$(button).show();
							})
						}
					});
			})
			$('#orderTable').DataTable({
				 "order": [[ 1, "desc" ]]
			});
		}
		})
	})
	
	$('#personal').on('click',function(){
		$('#title').text("Benvenuto nella tua area personale!");
		var cnt ="<div class=\"col-md-6\"><figure class=\"snip1563\">"+
				   "<img src=\""+contextPath+"/resources/personalarea/img/restaurant.jpg\" alt=\"ristorante\" />"+
				     "<figcaption>"+
				     	"<h2>Ristorante</h2><p>Clicca qui per accedere all'area ristorante!</p>"+
				     "</figcaption>"+
				   "<a href=\"/guidafederico/view/personalarea/user/restaurant\"></a>"+
				 "</figure>"+
				    "<p>Acquista i piatti del nostro ristorante<br>risparmiando cos&igrave; lunghe file alla cassa,<br>"+
				    "ritira il tuo ordine una volta pronto!.</p>"+
				"</div>"+
				"<div class=\"col-md-6\"><figure class=\"snip1563\">"+
				  "<img src=\""+contextPath+"/resources/personalarea/img/lido.jpg\" alt=\"lido\" />"+
				  	"<figcaption>"+
				  	   "<h2>Lido</h2>"+
				  	   "<p>Clicca qui per accedere all'area lido!</p>"+
				  	"</figcaption>"+
				  "<a href=\"/guidafederico/view/personalarea/user/lido\"></a>"+
				"</figure>"+
				    "<p>Con l'emergenza covid19 abbiamo introdotto<br>la possibilit&agrave; di prenotare il posto nel nostro lido<br>"+
				    "risparmiando cos&igrave; lunghe file agli sportelli.</p>"+
				"</div>";
		$('#content').html(cnt);
	})
	
	$('#data').on('click', function(){
		$.ajax({
			type: "GET",
			url: "/guidafederico/view/personalarea/update",
			data: {action: "userData"},
			dataType: "json",
			success: function (data) {
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
