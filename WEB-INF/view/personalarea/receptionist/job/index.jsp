<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Lido Vetrana - Area Receptionist</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/receptionist/css/style.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/fonts/ionicons.min.css">
</head>

<body>
    <nav class="navbar navbar-light navbar-expand d-inline navigation-clean">
        <div class="container"><a class="navbar-brand" href="/guidafederico/view/home">Lido Vetrana</a>
        <button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1">
        <span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse d-xl-flex justify-content-xl-start" id="navcol-2" style="opacity: 1;">
                <ul class="nav navbar-nav ml-auto">
                    <li class="nav-item" role="presentation"><a class="nav-link active" href="/guidafederico/view/personalarea/logout">Logout <i class="icon ion-log-out"></i></a></li>
                </ul>
            </div>
        </div>
    </nav>
    <section>
	    <div id="header">
		    <h1 class="text-success">Area Receptionist - Lido Vetrana</h1>
		    <div class="accordion_bk">
		    	<img id="receptionist" src="${pageContext.request.contextPath}/resources/receptionist/img/receptionist.png" />
		    </div>
		</div>
		<div class="container">
			<button id="prenotation" class="btn btn-primary">Prenotazioni</button>
			<button id="cashdesk" class="btn btn-primary">Cassa</button>
			<div id="content" class="row text-center"></div>
		</div>
    </section>
    <script src="${pageContext.request.contextPath}/resources/utility/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/utility/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/receptionist/js/receptionist.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
</body>

</html>