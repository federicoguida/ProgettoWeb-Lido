<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Lido Vetrana - Registrazione</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/registration/css/styles.css">
</head>

<body>
	<nav class="navbar navbar-light navbar-expand-md fixed-top d-inline navigation-clean">
        <div class="container-fluid"><a class="navbar-brand" href="/guidafederico/view/home">Lido Vetrana</a><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse d-xl-flex justify-content-xl-start"
                id="navcol-1" style="opacity: 1;">
                <ul class="nav navbar-nav ml-auto">
                    <li class="nav-item" role="presentation"><a class="nav-link" href="/guidafederico/view/personalarea/">Area Personale<i class="fas fa-user" style="margin: 6px;"></i></a></li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="register-photo" style="filter: brightness(106%);">
        <div class="form-container">
            <div class="image-holder"></div>
            <form id="registration" method="POST">
                <h2 class="text-center"><strong>Crea</strong> un account.</h2>
                <div class="form-group"><input class="form-control" type="email" name="Email" placeholder="Email" required pattern="[a-z0-9!#$%&amp;'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&amp;'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+(?:[A-Z]{2}|com|org|net|gov|mil|biz|info|mobi|name|aero|jobs|museum)\b"></div>
                <div
                    class="form-group"><input id="pass" class="form-control" type="password" name="Password" placeholder="Password" required></div>
        <div class="form-group"><input id="passRepeat" class="form-control" type="password" name="Password-repeat" placeholder="Password (ripeti)" required ></div>
        <p id="message"></p>
        <div class="form-group"><input class="form-control" type="text" name="Name" required placeholder="Nome"></div>
        <div class="form-group"><input class="form-control" type="text" name="Surname" required placeholder="Cognome"></div>
        <div class="form-group"><input class="form-control" type="number" name="Age" required inputmode="numeric" placeholder="Età"></div>
        <div class="form-group">
            <div class="form-check"><label class="form-check-label"><input class="form-check-input" type="checkbox" required>Accetto i termini e condizioni</label></div>
        </div>
        <div class="form-group"><button id="submit" class="btn btn-primary btn-block">Registrati</button></div><a class="already" href="/guidafederico/view/personalarea/">Hai già un account? Effettua il <strong>login</strong> qui.</a></form>
    </div>
    </div>
    <script src="${pageContext.request.contextPath}/resources/utility/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/utility/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/registration/js/registration.js"></script>
</body>

</html>