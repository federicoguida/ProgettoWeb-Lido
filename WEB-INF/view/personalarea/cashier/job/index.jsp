<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Lido Vetrana - Area Cassiere</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/fonts/ionicons.min.css">
</head>

<body>
    <nav class="navbar navbar-light navbar-expand d-inline navigation-clean">
        <div class="container"><a class="navbar-brand" href="/guidafederico/view/home">Lido Vetrana</a>
        <button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span>
        <span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse d-xl-flex justify-content-xl-start" id="navcol-2" style="opacity: 1;">
                <ul class="nav navbar-nav ml-auto">
                    <li class="nav-item" role="presentation">
                    <a class="nav-link active" href="/guidafederico/view/personalarea/logout">Logout <i class="icon ion-log-out"></i></a></li>
                </ul>
            </div>
        </div>
    </nav>
    <section>
        <h1 class="text-center text-success">Area cassiere - Lido Vetrana</h1>
		<div id="content" class="container">
			<div class="row py-5">
			    <div class="col-lg-9 mx-auto text-white text-center">
			      <h1 class="display-4">Accordion</h1>
			        <img src="${pageContext.request.contextPath}/resources/cashier/img/image.png" />
			    </div>
			  </div>
			  <div id="center" class="divCenter" style="text-align: center;">
				  <button id="newPost" type="button" class="btn btn-primary">Nuova postazione lido</button>
				  <button id="payOrd" type="button" class="btn btn-primary">Pagamento ordinazione</button>
			  </div>
			  <div id="tableContent" class="divCenter" style="text-align: center;"></div>
		  </div>
      </section>
    <script src="${pageContext.request.contextPath}/resources/utility/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/utility/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
    <script src="${pageContext.request.contextPath}/resources/cashier/js/cashier.js"></script>
</body>
</html>