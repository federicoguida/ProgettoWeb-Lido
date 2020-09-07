$(document).ready(function(){
	reloadContent();
	setInterval(reloadContent, 60*1000);
})
function reloadContent(){
	$('.accordion__header').click(function() {
	  $(".accordion__body").not($(this).next()).slideUp(400);
	  $(this).next().slideToggle(400);
	  $(".accordion__item").not($(this).closest(".accordion__item")).removeClass("open-accordion");
	  $(this).closest(".accordion__item").toggleClass("open-accordion");
	});
	$.ajax({
		type: "GET",
		url: "/guidafederico/view/personalarea/kitchen/Order",
		data: {action: "Order"},
		dataType: "json",
		success: function (data) {
			  var items = [];
			  var order = data;
			  $.each( data, function( key, val ) {
				  items.push("<div id=\"item_"+val.order_id+"\" class=\"card\">"+
				  " <div id=\"div_"+key+"\" class=\"card-header bg-white shadow-sm border-0\">" +
				  " <h6 id=\""+val.order_id+"\" class=\"mb-0 font-weight-bold\">" +
				  " <a href=\"#\" data-toggle=\"collapse\" data-target=\"#collapse_"+key+"\" aria-expanded=\"false\" aria-controls=\"collapse_"+key+"\"" +
				  " class=\"d-block position-relative collapsed text-dark text-uppercase collapsible-link py-2\">"+
				  " "+val.order_id+"</a></h6>"+
				  " </div>"+
			      " <div id=\"collapse_"+key+"\" aria-labelledby=\""+key+"\" data-parent=\"#orders_item\" class=\"collapse\">"+
			      " <div id=\"Card_"+val.order_id+"\" class=\"card-body p-5\"></div></div></div>");
			  })
			  $('#orders_item').html(items.join(""));
			  $('.card :header').one('click', function(){
				  var id=this.id;
				  	$.ajax({
				  		type: "GET",
						url: "/guidafederico/view/personalarea/kitchen/Order",
						data: {action: "products", order_id: id},
						dataType: "json",
						success: function (data) {
							  var items = [];
							  items.push("<div class=\"table-responsive\"><table class=\"table\">"+
			                  "<thead><tr><th>Prodotto</th><th>Quantit&agrave;</th></tr></thead><tbody>")
							  $.each( data, function( key, val ) {
								  items.push("<tr><td>"+val.name+"</td><td>"+val.quantity+"</td></tr>")
							  })
							  items.push("</tbody></table></div>");
							  for(var i = 0 ; i<order.length ; i++){
								  if(order[i].order_id==id){
									  if(order[i].instruction!="undefined" && order[i].instruction!="null" && order[i].instruction!="")
										  items.push("<p>Istruzioni:<br></p><p class=\"font-weight-light m-0\">"+order[i].instruction+"</p><br>");
									  items.push("<button id=\""+order[i].order_id+"\" class=\"btn btn-primary float-right\" type=\"button\">Consegnato</button>");
								  }
							  }
							  $("#Card_"+id).html(items.join("")); 
							  $(':button').on('click', function(){
								  var id=this.id;
								  $.ajax({
									  type: "POST",
									  url: "/guidafederico/view/personalarea/kitchen/Order",
								      data: {action: "delivered", order_id: this.id},
								      success: function(){
								    	  $("#item_"+id).remove();
								      }
								  });
							  })
						}
				  	})
			 })
		}
	});
}

