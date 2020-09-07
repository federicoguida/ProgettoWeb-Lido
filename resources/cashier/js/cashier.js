$(document).ready(function(){
	function reloadTableOrder(){
		$.ajax({
			type : "GET",
			url : "/guidafederico/view/personalarea/cashier",
			data : { action : "data" },
			datatype : "json",
			success: function (data) {
				var table="<div id=\"containerTable\" class=\"table-responsive\">"+
		        "<table id=\"orderTable\" class=\"table\">"+
		        "<thead>"+
		            "<tr><th>ID Ordine</th><th>Prezzo</th><th>Paga ordine</th></tr>" +
		        "</thead>" +
		        "<tbody>";
				$.each(data, function(i, item) {
				    table+="<tr id=\"TR"+item.order_id+"\"><td>"+item.order_id+"</td>" +
				    		"<td>"+item.total+"&euro;</td>" +
				    		"<td><button id=\""+item.order_id+"\" class=\"btn btn-primary\">Paga</button></td></tr>";
				});
				table+="</tbody></table></div>";
				$('#tableContent').html(table);
				$('#orderTable :button').on('click', function(){
					$.ajax({
						type : "POST",
						url : "/guidafederico/view/personalarea/cashier",
						data : { action : "pay" , order_id : this.id},
						success: function () {
							reloadTableOrder(); 
						}
				});
			})
			$('#orderTable').DataTable({
					"order": [[ 1, "desc" ]]
			});
			$('.table-responsive').append("<button id=\"hide\" class=\"btn btn-primary\">Annulla</button>");
			$('#hide').on('click',function(){
				this.parentElement.style.display='none';
			})
		  }
	  });
    }
	
	function reloadTableLido(){
		$.ajax({
			type : "GET",
			url : "/guidafederico/view/personalarea/cashier",
			data : { action : "lido" },
			datatype : "json",
			success: function (data) {
				var table="<div id=\"containerTable\" class=\"table-responsive\">"+
		        "<table id=\"lidoTable\" class=\"table\">"+
		        "<thead>"+
		            "<tr><th>Postazione</th><th>Prezzo</th><th>Prenota postazione</th></tr>" +
		        "</thead>" +
		        "<tbody>";
				$.each(data, function(i, item) {
				    table+="<tr id=\"TR"+item.idlocation+"\"><td>"+item.idlocation+"</td>" +
				    		"<td>"+item.price+"&euro;</td>" +
				    		"<td><button id=\""+item.idlocation+"\" class=\"btn btn-primary\">Prenota</button></td></tr>";
				});
				table+="</tbody></table></div>";
				$('#tableContent').html(table);
				$('#lidoTable :button').on('click', function(){
					$.ajax({
						type : "POST",
						url : "/guidafederico/view/personalarea/cashier",
						data : { action : "prenotation" , idlocation : this.id},
						success: function () {
							reloadTableLido(); 
						}
				});
			})
			$('#lidoTable').DataTable({
					"order": [[ 1, "desc" ]]
			});
			$('.table-responsive').append("<button id=\"hide\" class=\"btn btn-primary\">Annulla</button>");
			$('#hide').on('click',function(){
				this.parentElement.style.display='none';
			})
		  }
		});
	}
	
	$('#newPost').on('click',function(){
			reloadTableLido();
	})
	
	$('#payOrd').on('click',function(){
			 reloadTableOrder();
	})
		
})

