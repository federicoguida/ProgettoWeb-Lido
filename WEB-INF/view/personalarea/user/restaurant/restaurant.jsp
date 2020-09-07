<%@page import="org.apache.catalina.connector.Request"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="it_IT"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Lido Vetrana - Ristorante</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:400,400i,700,700i,600,600i">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/fonts/fontawesome-all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/fonts/fontawesome5-overrides.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.8.2/css/lightbox.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/restaurant/css/style.css">
</head>
<body>
	<nav class="navbar navbar-light navbar-expand d-inline navigation-clean">
	        <div class="container">
	            <div class="collapse navbar-collapse d-xl-flex justify-content-xl-start" id="navcol-2" style="opacity: 1;"><a class="navbar-brand" href="/guidafederico/view/home">Lido Vetrana</a>
	                <ul class="nav navbar-nav ml-auto">
	                    <li class="nav-item" role="presentation"><a class="nav-link" href="/guidafederico/view/personalarea/">Area Personale<i class="fas fa-user" style="margin: 6px;"></i></a></li>
	                    <li id="totalPrice" class="nav-item" role="presentation"><a class="nav-link" href="/guidafederico/view/personalarea/user/restaurant/cart">Carrello<i class="fas fa-shopping-cart" style="margin: 6px;"></i><fmt:formatNumber value="${cart.totalPrice}" type="currency" currencySymbol="&euro;"/><br></a></li>
	                </ul>
	            </div>
	        </div>
	</nav>
    <div class="photo-gallery">
        <div class="container">
            <h2 class="text-info">Lido Vetrana - Ristorante</h2>
            <div class="intro">
                <p class="text-break text-center text-dark" style="width: 577px;height: 136px;font-family: Montserrat, sans-serif;font-size: 14px;">
                <br>L'Hotel Lido Vetrana si situa all'interno del panorama degli <br>stabilimenti balneari con pizzeria annessa come una realtà  unica e 
                inconfondibile, sia per la tipologia di struttura, ma soprattutto per l'offerta qualitativa del proprio
                ristorante.<br><br></p>
            </div>
            <div class="row photos">
                <div class="col-sm-6 col-md-4 col-lg-3 item"><img class="img-fluid" src="${pageContext.request.contextPath}/resources/restaurant/img/alberghi-hotel-trabia-042-600x400.jpg"></div>
                <div class="col-sm-6 col-md-4 col-lg-3 item"><img class="img-fluid" src="${pageContext.request.contextPath}/resources/restaurant/img/alberghi-hotel-trabia-041-600x400.jpg"></div>
                <div class="col-sm-6 col-md-4 col-lg-3 item"><img class="img-fluid" src="${pageContext.request.contextPath}/resources/restaurant/img/alberghi-hotel-trabia-043-600x400.jpg"></div>
                <div class="col-sm-6 col-md-4 col-lg-3 item"><img class="img-fluid" src="${pageContext.request.contextPath}/resources/restaurant/img/alberghi-hotel-trabia-035-600x400.jpg"></div>
            </div>
        </div>
    </div>
    <main class="page catalog-page">
        <section class="clean-block clean-catalog dark" style="background-color: rgb(255,255,255);">
            <div class="container">
                <div class="block-heading"></div>
	            <div class="shopping-grid">
				    <div id="products" class="container">
				    	<div class="row">
					     <c:forEach items="${products}" var="value" varStatus="loop">
						        <div class="col-md-3 col-sm-6">
							            <div class="product-grid7">
							                <div class="product-image7">
							                        <img class="pic-1" src="${value.imgURL}">
							                        <img class="pic-2" src="${value.imgURL}">
							                    <ul class="social">
							                        <li><a id='${loop.count}' class="fa fa-shopping-cart"></a></li>
							                    </ul>
							                    <span class="product-new-label">${value.category}</span>
							                </div>
							                <div class="product-content">
							                	<input type="hidden" id="product_code_${loop.count}" value="${value.product_code}">
							                    <h2 class="title">${value.name}</h2>
							                    <p>${value.description}</p>
							                    <div class="price">
							                    <fmt:formatNumber value="${value.price}" type="currency" currencySymbol="&euro;" />
							                    </div>
							                </div>
							            </div>
						         </div>
						 </c:forEach>
				    	</div>
					</div>
				</div>
			</div>
		</section>
	</main>
    <script src="${pageContext.request.contextPath}/resources/utility/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/utility/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/restaurant/js/smoothproducts.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/restaurant/js/restaurant.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.8.2/js/lightbox.min.js"></script>
</body>
</html>