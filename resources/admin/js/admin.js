function validatorSettings(){
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
	        } else {
	            error.insertAfter(element);
	        }
	    },
	});
	jQuery.extend(jQuery.validator.messages, {
		required: "Questo campo &egrave; richiesto!",
		equalTo: "Inserire lo stesso valore in entrambi i campi!",
		min : jQuery.validator.format("Perfavore inserisci un valore maggiore o uguale a {0}"),
		extension : "Perfavore inserisci un immagine di un formato jpeg, png, jpg" 
	});
}

$(document).ready(function(){
	validatorSettings();
	$("#menu-toggle").click(function(e) {
	    e.preventDefault();
	    $("#wrapper").toggleClass("toggled");
	});
	$('#personal').on('click',function(){
		var html = "<div id=\"buttons\"><button id=\"add\" class=\"btn btn-primary\">Aggiungi dipendente</button>";
		html += "<button id=\"remove\" class=\"btn btn-primary\">Modifica dipendente</button></div>";
		$('#title').text("Benvenuto nell'area dipendenti");
		$('#imgDiv').html("<img id=\"imgTitle\" src=\""+contextPath+"/resources/admin/img/dipendenti.png\">");
		$('#content').html(html);
		
		$('#add').on('click',function(){
			$(this).hide();
			$('#remove').hide();
			var form = "<div id=\"addUserDiv\"><form method=\"POST\" action=\"admin/management?action=addUser\" id=\"addUser\">" +
							"<div class=\"form-group\"><label for=\"email\">Email dipendente</label>" +
							"<input id=\"email\" type=\"text\" name=\"email\" class=\"form-control\">" +
							"</div>" +
							"<div class=\"form-group\"><label for=\"password\">Password</label>" +
							"<input id=\"password\" type=\"password\" name=\"password\" class=\"form-control\">" +
							"</div>" +
							"<div class=\"form-group\"><label for=\"confirmPassword\">Conferma password</label>" +
							"<input id=\"confirmPassword\" type=\"password\" name=\"confirmPassword\" class=\"form-control\">" +
							"</div>" +
							"<div class=\"form-group\"><label for=\"name\">Nome</label>" +
							"<input id=\"name\" type=\"text\" name=\"name\" class=\"form-control\">" +
							"</div>" +
							"<div class=\"form-group\"><label for=\"surname\">Cognome</label>" +
							"<input id=\"surname\" type=\"text\" name=\"surname\" class=\"form-control\">" +
							"</div>" +
							"<div class=\"form-group\"><label for=\"age\" class=\"input-number-label\">Et&agrave;</label>" +
							"<input id=\"age\" type=\"number\" name=\"age\" value=\"18\" class=\"form-control\">" +
							"</div>" +
							"<div class=\"form-group\"><label for=\"role\">Ruolo</label>" +
							"<select id=\"roles\" name=\"rolelist\" form=\"addUser\" class=\"custom-select custom-select-sm\">"+
							"<option value=\"admin\">Amministratore</option>"+
							"<option value=\"cook\">Cuoco</option>"+
							"<option value=\"cashier\">Cassiere</option>" +
							"<option value=\"receptionist\">Receptionist</option>"+
							"</select>" +
							"</div>" +
							"<button id=\"return\" class=\"btn btn-primary\">Annulla</button>" +
							"<button id=\"sendUser\" type=\"submit\" class=\"btn btn-primary\">Registra dipendente</button>" +
							"</form>" +
							"<div id=\"notifyMessage\"></div>" +
							"</div>";
			$('#content').append(form);
			$('#return').on('click', function(){
				$('#add').show();
				$('#remove').show();
				$('#addUser').remove();
			})
			$('#addUser').validate({
				rules : {
					email : {
						required : true,
						pattern: /(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/
					},
					
					password : {
						required : true
					},
					
					confirmPassword : {
						required : true,
						equalTo : "#password"
					},
					
					name : {
						required : true
					},
					
					surname : {
						required : true
					},
					
					age : {
						required : true,
						min : "18",
					},
					
					roles : {
						required : true,
					}
				},
				
				messages : {
					email : {
						pattern : "L'email inserita non &egrave; in un formato valido!"
					}
				},
				
				submitHandler: function(form) {
					 $.ajax({
				            url: form.action,
				            type: form.method,
				            data: $(form).serialize(),
				            success: function(response) {
				            	if(response=="false"){
				            		$('#sendUser').hide();
				            		$('#notifyMessage').html("<div class=\"alert alert-danger\" role=\"alert\">"+
				            									"L'email inserita non pu&ograve; essere utilizzata!"+
				            								"</div>");
				            		setTimeout(function(){
				        				$('#notifyMessage').html("");
				        				$('#sendUser').show();
				            		},2000)
				            	}else if(response=="true"){
				            		$('#sendUser').hide();
				            		$('#notifyMessage').html("<div class=\"alert alert-success\" role=\"alert\">"+
				            									"Dipendente inserito con successo!"+
				            								"</div>");
				            		setTimeout(function(){
				            			$('#add').show();
				        				$('#remove').show();
				        				$('#addUser').remove();
				        				$('#notifyMessage').remove();
									},2000);
				            	}
				            	else{
				            		$('#notifyMessage').html("<p class=\"error\">Errore sconosciuto! Verra ricaricata la pagina tra qualche secondo!</p>");
				            		setTimeout(function(){
									       location.reload();
									},3000);
				            	}
				            		
				            }
					 });
				}
			})
		})
		
		$('#remove').on('click',function(){
			$('#add').hide();
			$(this).hide();
			$.ajax({
				type : "GET",
				url : "/guidafederico/view/personalarea/admin/management",
				data : { action : "getUsers"},
				dataType: "json",
				success : function(data){
					var table="<div id=\"users\" class=\"table-responsive\">"+
			        "<table id=\"userTable\" class=\"table\">"+
			        "<thead>"+
			            "<tr><th>Email</th><th>Ruolo</th><th>Modifica utente</th></tr>" +
			        "</thead>" +
			        "<tbody>";
					$.each( data, function( key, val ) {
						table+="<tr id=\""+key+"\">" +
								"<td>"+val.email+"</td>";
						switch(val.role) {
						  case "admin":
						    table+="<td>Amministratore</td>";
						    break;
						  case "cook":
							  table+="<td>Cuoco</td>";
						    break;
						  case "cashier":
							  table+="<td>Cassiere</td>";
						    break;
						  case "receptionist":
							  table+="<td>Receptionist</td>"
						  default:
						    // code block
						} 					
						table+="<td id=\""+val.email+"\"><button id=\"moduser"+key+"\" class=\"btn btn-primary\">Modifica Ruolo</button> " +
								"<button id=\"rmuser"+key+"\" class=\"btn btn-primary\">Rimuovi</button></td></tr>";
					})
					table+="</tbody></table><button id=\"return\" class=\"btn btn-primary\">Annulla</button></div>";
					$('#content').append(table);
					$('#userTable').DataTable();
					$('#return').on('click', function(){
						$('#add').show();
						$('#remove').show();
						$('#users').remove();
					})
					
					$('#userTable :button').on('click',function(){
						var email = this.parentElement.id
						var id = this.parentElement.parentElement.id;
						if(this.id.includes('moduser')){
							$(this).hide();
							$('#rmuser'+id).hide();
							$(this.parentElement).append("<div id=\"rmDiv"+id+"\"<label for=\"role\">Seleziona ruolo</label>"+
								    "<select id=\"role"+id+"\" class=\"custom-select custom-select-sm\">"+
								    "<option value=\"admin\">Amministratore</option>"+
									"<option value=\"cook\">Cuoco</option>"+
									"<option value=\"cashier\">Cassiere</option>" +
									"<option value=\"receptionist\">Receptionist</option>"+
								    "</select> <button id=\"cancel"+id+"\" class=\"btn btn-primary\">Annulla</button> " +
								    "<button id=\"update"+id+"\" class=\"btn btn-primary\">Aggiorna</button></div>");
							$('#cancel'+id).css("margin-top","10px");
							$('#update'+id).css("margin-top","10px");
							$('#cancel'+id).on('click', function(){
								$('#moduser'+id).show();
								$('#rmuser'+id).show();
								$('#rmDiv'+id).remove();
							})
							
							$('#update'+id).on('click',function(){
								$.ajax({
								type : "POST",
								url : "/guidafederico/view/personalarea/admin/management",
								data : { action : "moduser" , email : email , role : $('#role'+id).val()},
								success : function(){
									$('#users').remove();
									$('#remove').trigger('click');
								}
								});
							})
						}else{
							$.ajax({
								type : "POST",
								url : "/guidafederico/view/personalarea/admin/management",
								data : { action : "rmuser" , email : email },
								success : function(){
									$('#users').remove();
									$('#remove').trigger('click');
								}
								});
						}
					})
				}
			});
		})	
	})
	
	$('#lido').on('click',function(){
		$('#title').text("Benvenuto nell'area lido");
		$('#imgDiv').html("<img id=\"imgTitle\" src=\""+contextPath+"/resources/admin/img/lido.png\">");
		$.ajax({
			type : "GET",
			url :"/guidafederico/view/personalarea/admin/management",
			data : { action : "getLido"},
			dataType : "json",
			success : function(data){
				var table="<div id=\"lidoLocationDiv\" class=\"table-responsive\">"+
		        "<table id=\"lidoLocation\" class=\"table\">"+
		        "<thead>"+
		            "<tr><th>ID Posto</th><th>Status</th><th>Modifica status</th></tr>" +
		        "</thead>" +
		        "<tbody>";
				$.each(data, function(key,val){
					table+="<tr id=\""+val.idlocation+"\">" +
							"<td>"+val.idlocation+"</td>";
					if(val.disabled==false)
						table+="<td>Abilitata</td>";
					else
						table+="<td>Disabilitata</td>";
					if(val.disabled=="0")
						table+="<td><button id=\"disable\" class=\"btn btn-primary\">Disabilita</button></td></tr>"
					else
						table+="<td><button id=\"active\" class=\"btn btn-primary\" >Abilita</button></td></tr>"	
				})
				table+="</tbody></table></div>";
				$('#content').html(table);
				$('#lidoLocation :button').on('click',function(){
					var id = this.parentElement.parentElement.id;
					var action = this.id;
					$.ajax({
						type : "POST",
						url : "/guidafederico/view/personalarea/admin/management",
						data : {action : action , idlocation : id},
						success: function(){
							$('#lido').trigger('click');
						}
					});
				})
				$('#lidoLocation').DataTable();
			}
		});	
	})
	
	$('#restaurant').on('click',function(){
		$('#title').text("Benvenuto nell'area ristorante");
		$('#imgDiv').html("<img id=\"imgTitle\" src=\""+contextPath+"/resources/admin/img/restaurant.png\">");
		$('#content').html("<div id=\"buttons\"><button id=\"add\" class=\"btn btn-primary\">Aggiungi prodotto</button>" +
				" <button id=\"remove\" class=\"btn btn-primary\">Rimuovi prodotto</button></div>");
		$('#buttons').css("margin-top","15px");
		$('#add').on('click',function(){
			$('#buttons').hide();
			var form = "<div id=\"productDiv\"><form method=\"POST\" action=\"admin/management?action=addProduct\" id=\"addProduct\" enctype=\"multipart/form-data\">" +
							"<div class=\"form-group\"><label for=\"name\">Nome prodotto</label>" +
							"<input id=\"name\" type=\"text\" name=\"name\" class=\"form-control\">" +
							"</div>" +
							"<div class=\"form-group\"><label for=\"description\">Descrizione</label>" +
							"<input id=\"description\" type=\"text\" name=\"description\" class=\"form-control\">" +
							"</div>" +
							"<div class=\"form-group\"><label for=\"price\">Prezzo &euro;</label>" +
							"<input id=\"price\" type=\"number\" name=\"price\" class=\"form-control\">" +
							"</div>"+
							"<div class=\"form-group\"><label for=\"category\">Categoria</label>" +
							"<select id=\"category\" name=\"categorylist\" form=\"addProduct\" class=\"custom-select custom-select-sm\">"+
							"<option value=\"Antipasto\">Antipasto</option>"+
							"<option value=\"Primi\">Primo</option>"+
							"<option value=\"Secondo\">Secondo</option>"+
							"<option value=\"Bibita\">Bibita</option>"+
							"</select></div>" +
							"<div class=\"custom-file\">"+
						    "<input type=\"file\" class=\"custom-file-input\" id=\"customFile\" name=\"customFile\">"+
						    "<label class=\"custom-file-label\" for=\"customFile\">Inserisci immagine prodotto</label>"+
						    "</div><div id=\"buttonsForm\">"+	
							"<button id=\"return\" class=\"btn btn-primary\">Annulla</button>" +
							"<button id=\"sendProduct\" type=\"submit\" class=\"btn btn-primary\">Aggiungi</button></div></form>" +
							"<div id=\"notify\"></div></div>";
			$('#content').append(form);		
			$('#buttonsForm').css("margin-top","15px");
			$('#sendProduct').css("float","right");
			$(".custom-file-input").on("change", function() {
				  var fileName = $(this).val().split("\\").pop();
				  $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
			});
			$('#return').on('click',function(){
				$('#productDiv').remove();
				$('#buttons').show();
			})
			$('#addProduct').validate({
				rules : {
					customFile : {
						required : true,
						extension : "jpeg|png|jpg",
					},
					
					name : {
						required : true
					},
					
					description : {
						required : true
					},
					
					categorylist : {
						required : true
					},
					
					price : {
						required : true,
						min : "1"
					}
				},
				
				submitHandler: function(form) {
					var formData = new FormData(form);
					 $.ajax({
				            url: form.action,
				            type: form.method,
				            data: formData,
				            contentType: false,
				            cache: false,
				            processData:false,
				            success: function(response) {
				            	$('#buttonsForm').hide();
				            	$('#notify').html("<div class=\"alert alert-success\" role=\"alert\">"+
				            								"Prodotto inserito con successo"+
				            								"</div>");
				            	setTimeout(function(){
				            		$('#productDiv').remove();
				    				$('#buttons').show();
								},3000);							
				            }
					 });
				}
			});
			$('#productDiv').css("width", "100%");
			$('#productDiv').css("margin-top", "15px");
		})
		
		$('#remove').on('click',function(){
			$('#buttons').hide();
			$.ajax({
				type : "GET",
				url :"/guidafederico/view/personalarea/admin/management",
				data : { action : "getProducts"},
				dataType : "json",
				success : function(data){
					var table="<div id=\"products\" class=\"table-responsive\">"+
			        "<table id=\"productsTable\" class=\"table\">"+
			        "<thead>"+
			            "<tr><th>Prodotto</th><th>Categoria</th><th>Rimuovi prodotto</th></tr>" +
			        "</thead>" +
			        "<tbody>";
					$.each( data, function( key, val ) {
						table+="<tr id=\""+key+"\">" +
						"<td>"+val.name+"</td>" +
						"<td>"+val.category+"</td>" +
						"<td id=\""+val.code+"\"><button id=\"remove\" class=\"btn btn-primary\">Rimuovi</button></td></tr>";
					})
					table+="</tbody></table><button id=\"return\" class=\"btn btn-primary\">Annulla</button>" +
							"<div id=\"notifyMessage\"></div></div>";
					$('#content').append(table);
					$('#return').on('click',function(){
						$('#products').remove();
						$('#buttons').show();
					})
					
					$('#productsTable :button').on('click',function(){
						var product_code = this.parentElement.id;
						$.ajax({
							type : "POST",
							url :"/guidafederico/view/personalarea/admin/management",
							data : { action : "removeProduct", product_code : product_code},
							success : function(data){
								$('#notifyMessage').html("<div class=\"alert alert-success\" role=\"alert\">"+
        								"Prodotto eliminato con successo!"+
        								"</div>");
								setTimeout(function(){
									$('#products').remove();
									$('#buttons').show();
								},2000);
							}
						});
					})
					$('#productsTable').DataTable();
				}
			});
		})
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