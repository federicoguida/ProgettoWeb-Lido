<%@page import="org.apache.catalina.connector.Request"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Lido Vetrana - Area Utente</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/fonts/fontawesome-all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/fonts/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/fonts/fontawesome5-overrides.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/personalarea/css/style.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
</head>

<body>
    <nav class="navbar navbar-light navbar-expand-sm d-flex navigation-clean">
        <div class="container-fluid">
            <div id="wrapper">
                <div id="sidebar-wrapper" style="background-color: rgb(255,255,255);">
                    <ul class="sidebar-nav">
                        <li class="sidebar-brand"> <a href="/guidafederico/view/home">Home </a></li>
                        <li> <a id="personal" type="button">Area personale<i class="fas fa-user"></i></a>
                        	 <a id="prenotation" type="button">Riepilogo prenotazioni<i class="fas fa-umbrella-beach"></i></a>
                        </li>
                        <li> <a id="order" type="button">Riepilogo ordini<i class="fa fa-shopping-basket"></i></a></li>
                        <li> <a id="data" type="button">Modifica dati account<i class="fa fa-user-circle"></i></a>
                        	 <a href="/guidafederico/view/personalarea/logout">Logout<i class="icon ion-log-out"></i></a>
                       	</li>
                    </ul>
                </div>
                <div class="page-content-wrapper">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-12">
                                <div>
                                    <h1><a class="btn btn-link" role="button" id="menu-toggle" href="#menu-toggle"><i class="fa fa-bars"></i></a><i class="fa fa-user-circle-o"></i></h1>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <ul class="nav navbar-nav mr-auto">
                <li class="nav-item mr-auto" role="presentation"><a class="navbar-brand">Lido Vetrana</a></li>
            </ul>
            <div><i class="far fa-user"></i>
                <p id="user" class="d-inline"><c:out value="${pageContext.request.userPrincipal.name}"/></p>
            </div>
        </div>
    </nav>
    <div>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div style="text-align:center;">
                        <h2 class="divider-style"><span id="title">Benvenuto nella tua area personale!</span></h2>
                    </div>
                </div>
            </div>
            <div id="content" class="row text-center"><div class="col-md-6">
				<figure class="snip1563">
				  <img src="${pageContext.request.contextPath}/resources/personalarea/img/restaurant.jpg" alt="ristorante" />
				  <figcaption>
				    <h2>Ristorante</h2>
				    <p>Clicca qui per accedere all'area ristorante!</p>
				  </figcaption>
				  <a href="/guidafederico/view/personalarea/user/restaurant"></a>
				</figure>
				    <p> Acquista i piatti del nostro ristorante<br>
				       risparmiando cos&igrave; lunghe file alla cassa,<br>
				        ritira il tuo ordine una volta pronto!.</p>
				</div>
				<div class="col-md-6">
				    <figure class="snip1563"><img src="${pageContext.request.contextPath}/resources/personalarea/img/lido.jpg" alt="lido" />
					  <figcaption>
					    <h2>Lido</h2>
					    <p>Clicca qui per accedere all'area lido!</p>
					  </figcaption>
					  	<a href="/guidafederico/view/personalarea/user/lido"></a>
					</figure>
				    <p>Con l'emergenza covid19 abbiamo introdotto<br>
				       la possibilit&agrave; di prenotare il posto nel nostro lido<br>
				       risparmiando cos&igrave; lunghe file agli sportelli.</p>
				</div>
			</div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/resources/utility/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/utility/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.2/dist/jquery.validate.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.2/dist/additional-methods.js"></script>
    <script type="text/javascript">
		var contextPath='<%=request.getContextPath()%>';
	</script>
    <script src="${pageContext.request.contextPath}/resources/personalarea/js/personalarea.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>  
</body>
</html>