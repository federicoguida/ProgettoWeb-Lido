/* Funzione che si occupa di aggiornare 
 * il contenuto del carrello */
$(document).ready(function () {  
	$('#update a').on('click', function(){
		var id=this.id;
		var quantity=$('#quantity_'+this.id).val();
		if(quantity>15 || quantity<1){
			$('#quantity_'+id).val(1);
		}else{
			$.ajax({
				type: "POST",
				url: "/guidafederico/view/personalarea/user/restaurant/update",
				data: {product_code: id, quantity: quantity, action: "update" },
				dataType: "text",
				success: function (response) {
					if($.isNumeric(response)){
						const formatter = new Intl.NumberFormat('it-IT', {
							  style: 'currency',
							  currency: 'EUR'
							})
						$('#totalPrice').html("<a class=\"nav-link\" href=\"/guidafederico/view/personalarea/user/restaurant/cart\">Carrello<i class=\"fas fa-shopping-cart\" style=\"margin: 6px;\"></i>" + formatter.format(response));
						$("#partial_"+id).html("<strong>"+formatter.format(parseFloat($('#price_'+id).text())*$('#quantity_'+id).val())+"</strong>");
						$("#total").html("<h5 class=\"font-weight-bold\">"+formatter.format(response)+"</h5>");
					}else{
						location.reload(true);
						}
					},
				error: function (){
					$('#totalPrice').html("error");
				}
			});
		}
	})
	
	$('#remove a').on('click', function(){
		var id=this.id;
		$.ajax({
			type: "POST",
			url: "/guidafederico/view/personalarea/user/restaurant/remove",
			data: {product_code: id, quantity: $('#quantity_'+this.id).val(), action: "remove" },
			dataType: "text",
			success: function (response) {
				if($.isNumeric(response)){
					const formatter = new Intl.NumberFormat('it-IT', {
						  style: 'currency',
						  currency: 'EUR'
						})
					$('#totalPrice').html("<a class=\"nav-link\" href=\"/guidafederico/view/personalarea/user/restaurant/cart\">Carrello<i class=\"fas fa-shopping-cart\" style=\"margin: 6px;\"></i>" + formatter.format(response));
					$("#total").html("<h5 class=\"font-weight-bold\">"+formatter.format(response)+"</h5>");
					$("."+id).remove();
				}else{
					location.reload(true);
					}
				},
			error: function (){
				$('#totalPrice').html("error");
			}
		});
	})
	
	$('#pay_now').on('click', function(){
		var id=this.id;
		var total=parseFloat($("#total").text());
		if(total<=0 || total === null || isNaN(total)){
			$('#message').html("<div class=\"alert alert-danger\" role=\"alert\">"+
									"<strong>Attenzione! Il carrello non contiene elementi non &egrave; possibile procedere con l'ordine</strong>"+
				               "</div>");
		}else{
			var instruction = $('#instruction > textarea').val()
			console.log($('#instruction > textarea').val());
			$.ajax({
				type: "GET",
				url: "/guidafederico/view/personalarea/user/restaurant/order",
				data: { ins: $('#instruction > textarea').val() , action: "instruction" },
				success: function () {
					window.location.assign("/guidafederico/view/personalarea/user/restaurant/pay");
				}
			});		
		}
	})
	
	$('#prenotation').on('click', function(){
		var id=this.id;
		var total=parseFloat($("#total").text());
		if(total<=0 || total===null || isNaN(total)){
			$('#message').html("<div class=\"alert alert-danger\" role=\"alert\">"+
									"<strong>Attenzione! Il carrello non contiene elementi non &egrave; possibile procedere con l'ordine</strong>"+
		                       "</div>");
		}else{
			$.session.set("instruction", $('#instruction').val());
			$('#prenotation_form').submit();
		}
	})
	
})