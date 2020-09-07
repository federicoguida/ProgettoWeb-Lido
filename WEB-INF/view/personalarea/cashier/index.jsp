<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Lido Vetrana - Area Cassiere</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/fonts/fontawesome-all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/fonts/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/fonts/fontawesome5-overrides.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/cashier/css/style.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
	
</head>
<script type="text/javascript">
	var contextPath='<%=request.getContextPath()%>';
</script>
<body>
    <nav class="navbar navbar-light navbar-expand-sm d-flex navigation-clean">
        <div class="container-fluid">
            <div id="wrapper" class="toggled">
                <div id="sidebar-wrapper" style="background-color: rgb(255,255,255);">
                    <ul class="sidebar-nav">
                        <li class="sidebar-brand">
                        	<a href="/guidafederico/view/home">Home </a></li>
                        <li>
                        	<a id="job" type="button">Area lavoro<i class="fas fa-user"></i></a></li>
                        <li> 
                        	<a id="data" type="button">Modifica dati account<i class="fa fa-user-circle"></i></a>
                        	<a href="/guidafederico/view/personalarea/logout">Logout<i class="icon ion-log-out"></i></a></li>
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
                        <div id="imgDiv"></div>
                    </div>
                </div>
            </div>
            <div id="content" class="row text-center"></div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/resources/utility/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/utility/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.2/dist/jquery.validate.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.2/dist/additional-methods.js"></script>
    <script src="${pageContext.request.contextPath}/resources/cashier/js/index.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>   
</body>
</html>