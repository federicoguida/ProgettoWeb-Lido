<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Lido Vetrana - Area Ristorante</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/kitchen/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/fonts/ionicons.min.css">
</head>

<body>
    <nav class="navbar navbar-light navbar-expand d-inline navigation-clean">
        <div class="container">
        	<a class="navbar-brand" href="/guidafederico/view/home">Lido Vetrana</a>
        	<button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1">
        	<span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse d-xl-flex justify-content-xl-start" id="navcol-2" style="opacity: 1;">
                <ul class="nav navbar-nav ml-auto">
                    <li class="nav-item" role="presentation">
                    <a class="nav-link active" href="/guidafederico/view/personalarea/logout">Logout <i class="icon ion-log-out"></i></a></li>
                </ul>
            </div>
        </div>
    </nav>
    <section>
	    <h1 class="text-center text-success">Area ristorante - Lido Vetrana</h1><div class="accordion_bk">
		<div class="container">
				  <div class="row py-5">
				    <div class="col-lg-9 mx-auto text-white text-center">
				      <h1 class="display-4">Accordion</h1>
				        <img src="${pageContext.request.contextPath}/resources/kitchen/img/separetor.png" />
				    </div>
				  </div>
					  <div class="row">
					    <div class="col-lg-9 mx-auto">
					      <div id="orders_item" class="accordion shadow">
					    </div>
					</div>
				</div>
			</div>
		</div>
    </section>
    <script type="text/javascript">
		var contextPath='<%=request.getContextPath()%>';
	</script>
    <script src="${pageContext.request.contextPath}/resources/utility/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/utility/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/kitchen/js/kitchen.js"></script>
</body>
</html>