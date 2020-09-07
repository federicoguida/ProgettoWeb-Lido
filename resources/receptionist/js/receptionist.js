function getPrenotation(){
	$.ajax({
		type : "GET",
		url : "/guidafederico/view/personalarea/receptionist/",
		data : { action : "getPrenotation"},
		dataType : "json",
		success : function(data){
			var table = "<div id=\"divPrenotation\" class=\"table-responsive\">"+
	        "<table id=\"prenotationTable\" class=\"table\">"+
	        "<thead>"+
	            "<tr><th>ID Prenotazione</th><th>Posto</th><th>Status</th><th>Check-out</th></tr>" +
	        "</thead>" +
	        "<tbody>";
			$.each(data , function(key, val){
				table+="<tr id=\""+ val.idprenotation +"\">"
					+ "<td>" + val.idprenotation + "</td>"
					+ "<td>" + val.location_idlocation + "</td>"
				    + "<td>Attivo</td>"
				    + "<td><button id=\""+ val.location_idlocation +"\" class=\"btn btn-primary\">Terminato</button></td></tr>";
			})
			table+="</tbody></table></div>";
			$('#content').html(table);
			$('#prenotationTable :button').on('click',function(){
				var idlocation = this.id;
				var idprenotation = this.parentElement.parentElement.id;
				$.ajax({
					type : "POST",
					url : "/guidafederico/view/personalarea/receptionist/",
					data : { action : "done", idlocation : idlocation, idprenotation : idprenotation},
					success : function(){
						getPrenotation();
					}
				});
			})
			$('#prenotationTable').DataTable();
			$('.table-responsive').append("<button id=\"hide\" class=\"btn btn-primary\">Annulla</button>");
			$('#hide').on('click',function(){
				this.parentElement.style.display='none';
			})
		}
	});
}
	
function getCash(){	
	$.ajax({
		type : "GET",
		url : "/guidafederico/view/personalarea/receptionist/",
		data : { action : "getCash"},
		dataType : "json",
		success : function(data){
			var table = "<div id=\"divCashDesk\" class=\"table-responsive\">"+
	        "<table id=\"cashTable\" class=\"table\">"+
	        "<thead>"+
	            "<tr><th>Posto</th><th>Check-out</th></tr>" +
	        "</thead>" +
	        "<tbody>";
			$.each(data.location , function(key, val){
				table+="<tr>"
					+ "<td>" + val + "</td>"
				    + "<td><button id=\""+ val +"\" class=\"btn btn-primary\">Terminato</button></td></tr>";
			})
			table+="</tbody></table></div>";
			$('#content').html(table);
			$('#cashTable :button').on('click',function(){
				$.ajax({
					type : "POST",
					url : "/guidafederico/view/personalarea/receptionist/",
					data : { action : "out", idlocation : this.id},
					success : function(data){
						getCash();
					}
				});
			})
			$('#cashTable').DataTable();
			$('.table-responsive').append("<button id=\"hide\" class=\"btn btn-primary\">Annulla</button>");
			$('#hide').on('click',function(){
				this.parentElement.style.display='none';
			})
		}
	});
}

$(document).ready(function(){
	
	$('#prenotation').on('click', function(){
		getPrenotation();
	})
	
	$('#cashdesk').on('click', function(){
		getCash();
	})
	
})