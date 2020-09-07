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
    <title>Lido Vetrana - Carrello</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/fonts/fontawesome-all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/fonts/fontawesome5-overrides.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/cart/css/styles.css">
</head>

<body>
    <div>
        <nav class="navbar navbar-light navbar-expand-md d-inline navigation-clean">
            <div class="container">
                <div class="collapse navbar-collapse d-inline-flex d-xl-flex justify-content-xl-start" id="navcol-1" style="opacity: 1;">
                	<a class="navbar-brand" href="/guidafederico/view/home">Lido Vetrana</a>
                    <ul class="nav navbar-nav ml-auto">
                        <li class="nav-item" role="presentation"><a class="nav-link" href="/guidafederico/view/personalarea/">Area Personale<i class="fas fa-user" style="margin: 6px;"></i></a></li>
                        <li id="totalPrice" class="nav-item" role="presentation">
	                        <a class="nav-link" href="/guidafederico/view/personalarea/user/restaurant/cart">Carrello
	                        	<i class="fas fa-shopping-cart" style="margin: 6px;"></i><fmt:formatNumber value="${cart.totalPrice}" type="currency" currencySymbol="&euro;" /><br>
                       		</a>
                       	</li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
    <div><div class="shopping-cart">
<div class="px-4 px-lg-0">
  <div class="pb-5">
    <div class="container">
      <div class="row">
        <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">
          <div class="table-responsive">
            <table class="table">
              <thead>
                <tr>
                  <th scope="col" class="border-0 bg-light">
                    <div class="p-2 px-3 text-uppercase">Prodotto</div>
                  </th>
                  <th scope="col" class="border-0 bg-light">
                    <div class="py-2 text-uppercase">Prezzo</div>
                  </th>
                  <th scope="col" class="border-0 bg-light">
                    <div class="py-2 text-uppercase">Quantità </div>
                  </th>
                  <th scope="col" class="border-0 bg-light">
                    <div class="py-2 text-uppercase">Prezzo * Quantità</div>
                  </th>
                  <th scope="col" class="border-0 bg-light">
                    <div class="py-2 text-uppercase">Rimuovi</div>
                  </th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${cart.items}" var="item" varStatus="loop">
                <tr id="products" class="${item.product.product_code}">
                  <th scope="row" class="border-0">
                    <div class="p-2">
                      <img src="${item.product.imgURL }" alt="" width="70" class="img-fluid rounded shadow-sm">
                      <div class="ml-3 d-inline-block align-middle">
                        <h5 class="mb-0">${item.product.name}</h5><span class="text-muted font-weight-normal font-italic d-block">${item.product.category}</span>
                      </div>
                    </div>
                  </th>
                  <td  id="price_${item.product.product_code}" class="border-0 align-middle"><strong><fmt:formatNumber value="${item.product.price}" type="currency" currencySymbol="&euro;" /></strong></td>
		          	<td id="update" class="border-0 align-middle">
		            	<input id="quantity_${item.product.product_code}" type="number" min="1" max="15" name="quantity" value="${item.quantity}" style="width: 3em" required>
		                	<a id="${item.product.product_code}" class="text-dark"><i class="fa fa-refresh" aria-hidden="true"></i></a>
		            </td>
                  	<td id="partial_${item.product.product_code}" class="border-0 align-middle">
                    	<strong><fmt:formatNumber value="${item.total}" type="currency" currencySymbol="&euro;" /></strong>
                  	</td>
                  <td id="remove" class="border-0 align-middle">
                  	<a id="${item.product.product_code}" class="text-dark"><i class="fa fa-trash"></i></a>
                  </td>
                </tr>
               </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <div class="row py-5 p-4 bg-white rounded shadow-sm">
       <strong id="message">${message}</strong>
        <div class="col-lg-6">
          <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Istruzioni per il cuoco</div>
          <div id="instruction" class="p-4">
            <p class="font-italic mb-4">Se hai qualche intolleranza scrivila nel box sottostante</p>
            <textarea  name="" cols="30" rows="2" class="form-control"></textarea>
          </div>
        </div>
        <div class="col-lg-6">
          <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Riepilogo ordine </div>
          <div class="p-4">
                <p class="font-italic mb-4">Paga ora il tuo ordine, oppure paga il tuo ordine direttamente alla cassa. Si informa la clientela che l'ordine partirà  una volta effettuato il pagamento.
            <ul class="list-unstyled mb-4">
              <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Totale</strong>
                <h5 id="total" class="font-weight-bold"><fmt:formatNumber value="${cart.totalPrice}" type="currency" currencySymbol="&euro;" /></h5>
              </li>
            </ul>
            	<a id="pay_now" class="btn btn-light rounded-pill py-2 btn-block">Paga ora</a><br>
            	<form id="prenotation_form" method="POST" action="/guidafederico/view/personalarea/user/restaurant/order?action=prenotation">
                	<a id="prenotation" class="btn btn-light rounded-pill py-2 btn-block">Paga alla cassa</a>
                </form>
          </div>
        </div>
        <a href="/guidafederico/view/personalarea/user/restaurant" class="btn btn-dark rounded-pill py-2 btn-block">Torna indietro</a>
      </div>
    </div>
  </div>
</div>
</div></div>
    <script src="${pageContext.request.contextPath}/resources/utility/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/utility/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/utility/js/jquery.session.js"></script>
    <script src="${pageContext.request.contextPath}/resources/cart/js/cart.js"></script>
</body>
</html>