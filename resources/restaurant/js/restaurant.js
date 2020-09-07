$(document).ready(function () {
	if ($('.clean-gallery').length > 0) {
		   baguetteBox.run('.clean-gallery', { animation: 'slideIn'});
		}

		if ($('.clean-product').length > 0) {
		    $(window).on("load",function() {
		        $('.sp-wrap').smoothproducts();
		    });
		}
	$('#products a').on('click', function(){		
		$.ajax({
			type: "POST",
			url: "/guidafederico/view/personalarea/user/restaurant/add",
			data: {product_code: $('#product_code_' + this.id).val(), quantity: 1, action: "add" },
			dataType: "text",
			success: function (response) {
				const formatter = new Intl.NumberFormat('it-IT', {
					  style: 'currency',
					  currency: 'EUR'
					})
				if($.isNumeric(response)){
					$('#totalPrice').html("<a class=\"nav-link\" href=\"/guidafederico/view/personalarea/user/restaurant/cart\">Carrello<i class=\"fas fa-shopping-cart\" style=\"margin: 6px;\"></i>" + formatter.format(response));
				}else{
					location.reload(true);
					}
				},
			error: function (){
				$('#totalPrice').html("error");
			}
		});
	})
})