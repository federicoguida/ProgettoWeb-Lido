$(document).ready(function(){
	$('[data-bs-hover-animate]')
		.mouseenter( function(){ var elem = $(this); elem.addClass('animated ' + elem.attr('data-bs-hover-animate')) })
		.mouseleave( function(){ var elem = $(this); elem.removeClass('animated ' + elem.attr('data-bs-hover-animate')) });
	$('[data-bs-tooltip]').tooltip();
	var today=new Date();
	var button = $('#pay');
	if(today.getHours()<15 || today.getHours()>20){
		$.ajax({
			type: "GET",
			url: "/guidafederico/view/personalarea/user/lido",
			data: {action: "lido"},
			dataType: "json",
			success: function (data) {
				 var items = [];
				  var order = data;
				  $.each( data, function( key, val ) {
					  var id=val.idlocation;
					  if(val.busy==false)
						  var imgHTML="<img id=\""+id+"\" src=\""+contextPath+"/resources/lido/img/pngwing.com.png\" style=\"width: 50px;filter: contrast(50%) invert(0%)\">";
					  else
						  var imgHTML="<img id=\""+id+"\" src=\""+contextPath+"/resources/lido/img/pngwing.com.png\" style=\"width: 50px;filter: grayscale(100%);\" >";
					  if(id.includes("A"))
						  $('#A').append(imgHTML);
					  else if(id.includes("B"))
						  $('#B').append(imgHTML);
					  else
						  $('#C').append(imgHTML);
					  
					  if(val.busy==false){
						  $('#'+id).on('click', function(){
							  $('#message').text('');
							  var $this = $(this);
							  var clicks = $(this).data('clicks');
							  if(clicks==undefined)
								  clicks=false;
							  var id=this.id;
							  if (clicks) {
								  $('#'+id).css('filter', 'contrast(50%) invert(0%)');
								  $.ajax({
										type: "POST",
										url: "/guidafederico/view/personalarea/user/lido",
										data: {action: "remove", itemID: id},
										success: function () {
													$('#TR'+id).remove();
													var amount = parseInt($('#amount').text());
													amount-=val.price;
													$('#amount').html(amount);
													$('#pay-now').hide();
													$('#total').append(button);
										}
								  });
							     } else {
									  $('#'+id).css('filter', 'contrast(200%) invert(0%)');
									  $.ajax({
											type: "POST",
											url: "/guidafederico/view/personalarea/user/lido",
											data: {action: "add", itemID: id},
											dataType: "json",
											success: function (data) {
												$.each( data, function( key, val ) {
													var tr="<tr id=\"TR"+val.idlocation+"\" >"+
			                                        "<td>"+val.idlocation+"</td>"+
			                                        "<td>"+val.price+"</td>"+
			                                        "<td><i id=\"I"+val.idlocation+"\" class=\"fa fa-remove\" style=\"padding-left: 10%;\"></i></td>"+
			                                        "</tr>";
													$('#tableBody').append(tr);
													var amount = parseInt($('#amount').text());
													amount+=val.price;
													$('#amount').html(amount);
													$('#pay-now').hide();
													$('#total').append(button);
													$('#I'+val.idlocation).on('click', function(){
														$('#'+id).css('filter', 'contrast(50%) invert(0%)');
														$.ajax({
															type: "POST",
															url: "/guidafederico/view/personalarea/user/lido",
															data: {action: "remove", itemID: id},
															success: function () {
																		$('#TR'+id).remove();
																		$this.data("clicks", false);
																		var amount = parseInt($('#amount').text());
																		amount-=val.price;
																		$('#amount').html(amount);
																		$('#pay-now').hide();
																		$('#total').append(button);
															}
													  });
													})
												})
											}
									  });
							     }
							  $(this).data("clicks", !clicks);
						  })
					  }
				  })
			}
		});	
	$('#pay').on('click',function(){
		var amount = parseInt($('#amount').text());
		if(amount <=0){
			$('#message').html("<div class=\"alert alert-danger\" role=\"alert\">"+
									"Il carrello &egrave; vuoto!</br>"+
			                   "</div>");
		}else{
			this.remove();
			$('#pay-now').show();
			$('#payment-form').on('submit',function(){
				$.ajax({
					type: "POST",
					url: "/guidafederico/view/personalarea/user/lido",
					data: {action: "pay"},
					dataType: "text",
					success: function(data){
						if(data=="true"){
							$('#message').html("<div class=\"alert alert-success\" role=\"alert\">"+
												"Pagamento effettuato con successo, troverai l'ordine nel tuo storico prenotazioni.<br>" +
												"a breve sarai reinderizzato alla tua area utente!"+
			                   		          "</div>");
							setTimeout(function(){
									window.location.assign("/guidafederico/view/personalarea/");
							   },4000);
						}else if(data=="false"){
							$('#message').html("<div class=\"alert alert-danger\" role=\"alert\">"+
												"Errore posti gi&agrave; prenotati!, la pagina verr&agrave; ricaricata a breve"+
			                   		           "</div>");
							setTimeout(function(){
							       location.reload();
							   },4000);
						}
					}
				});
				return false;
			})
		}
	})
	}else{
		$('#pay').one('click', function(){
			$('#message').append("<div class=\"alert alert-danger\" role=\"alert\">"+
								   "Ordini bloccati!</br>"+
	                             "</div>");
		})
	}
	
})

