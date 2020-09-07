<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Lido Vetrana - Prenotazione</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/prenotation/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/fonts/ionicons.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/fonts/fontawesome-all.min.css">
</head>
<body>
<nav class="navbar navbar-light navbar-expand d-flex navigation-clean">
        <div class="container">
            <div class="collapse navbar-collapse d-xl-flex justify-content-xl-start" id="navcol-2" style="opacity: 1;"><a class="navbar-brand" href="/guidafederico/view/home">Lido Vetrana</a>
                <ul class="nav navbar-nav ml-auto">
                    <li class="nav-item" role="presentation">
                    	<a class="nav-link" href="/guidafederico/view/personalarea/" style="margin: 0px;">Area Personale&nbsp;<i class="fas fa-user"></i></a>
                    </li>
                    <li class="nav-item" role="presentation">
                    	<a class="nav-link" href="/guidafederico/view/personalarea/logout" style="margin: 0px;">Logout&nbsp;<i class="icon ion-log-out"></i></a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container">
    <br><br><br>
    	<div class="alert alert-success" role="alert">
			<h2>Ordine effettuato con successo!</h2>
		</div>
		<div class="alert alert-info" role="alert">
			<h2>Ordine id: ${order.order_id}</h2><br>
			<h3>Prenotazione effettuata con successo! Mostra il tuo id ordine in cassa per procedere al pagamento</h3>
		</div>
		<a id="goBack" href="/guidafederico/view/personalarea/user/restaurant">Torna indietro!</a>
    </div>
</body>
</html>