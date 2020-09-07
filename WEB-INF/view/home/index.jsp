<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Lido Vetrana - Home</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/fonts/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/homepage/css/styles.css">
</head>

<body>
    <div class="article-list">
        <div class="container">
            <div class="intro">
                <h2 class="text-center"><img src="${pageContext.request.contextPath}/resources/homepage/img/beach-sunset-summer-logo.jpg" style="height: 374px;"></h2>
            </div>
            <nav class="navbar navbar-light navbar-expand-md fixed-top d-inline navigation-clean" >
                <div class="container-fluid"><a class="navbar-brand" href="/guidafederico/view/home">Lido Vetrana</a><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
                    <div
                        class="collapse navbar-collapse d-xl-flex justify-content-xl-start" id="navcol-1" style="opacity: 1;">
                        <ul class="nav navbar-nav ml-auto">
                            <li class="nav-item" role="presentation"><a class="nav-link" href="/guidafederico/view/personalarea/">Area Personale</a></li>
                            <li class="nav-item" role="presentation"><a class="nav-link" href="/guidafederico/view/home/registration">Registrazione</a></li>
                        </ul>
                </div>
        </div>
        </nav>
        <div class="row articles">
            <div class="col-sm-6 col-md-4 item" ><img class="img-fluid" src="${pageContext.request.contextPath}/resources/homepage/img/alberghi-hotel-trabia-016-1698x1163.jpg">
                <h3 class="name">Lido Vetrana</h3>
                <p class="text-justify description"><br>L'Hotel Lido Vetrana &egrave; un ottimo ristorante per feste ed eventi, che grazie alla bont&agrave; 
                della sua cucina, all'incantevole struttura e alla disponibilit&agrave; 
                del personale costituir&agrave; la soluzione perfetta per celebrare il tuo evento all'insegna
                del gusto, del relax e della convivialit&agrave; .<br></p>
            </div>
            <div class="col-sm-6 col-md-4 item" ><img class="img-fluid" src="${pageContext.request.contextPath}/resources/homepage/img/Immagine.png"></div>
            <div class="col-sm-6 col-md-4 item" ><img class="img-fluid" src="${pageContext.request.contextPath}/resources/homepage/img/alberghi-hotel-trabia-035-600x400.jpg">
                <h3 class="name">Servizio Ristorazione</h3>
                <p class="text-left description"><br>Il ristorante interno infatti, 
                	oltre che essere disponibile per tutti gli ospiti dell'hotel, con la classica formula della mezza pensione o della pensione completa,
               	    &egrave; in grado di accogliere col sorriso e con tutta la cortesia
                    del suo staff chi come te vuole gustare specialit&agrave; di pesce squisite e freschissime.<br>
                </p>
           </div>
        </div>
    </div>
    </div>
    <div class="align-items-center align-content-center map-clean">
        <div class="container">
            <div class="intro">
                <h2 class="text-center">Dove siamo</h2>
                <p class="text-center"></p>
            </div>
        </div><iframe style="border:0;" src="https://www.google.com/maps/embed/v1/place?key=AIzaSyCBhn8ltw7tBghWFXxwFZDSieeCH4xrJJA&amp;q=Lido+Vetrana&amp;zoom=11" width="100%" height="350"></iframe></div>
    <div class="footer-basic">
        <footer>
            <div class="social">
            	<a href="https://www.instagram.com/lidovetrana/"><i class="icon ion-social-instagram"></i></a>
	            <a href="https://twitter.com/hashtag/lidovetrana"><i class="icon ion-social-twitter"></i></a>
	            <a href="https://www.facebook.com/lidovetrana/"><i class="icon ion-social-facebook"></i></a>
            </div>
            <ul class="list-inline">
                <li class="list-inline-item"><a href="/guidafederico/view/home">Home</a></li>
                <li class="list-inline-item"><a href="">Servizi</a></li>
                <li class="list-inline-item"><a href="">Termini</a></li>
                <li class="list-inline-item"><a href="">Privacy</a></li>
            </ul>
            <p class="copyright">Lido Vetrana © 2020</p>
        </footer>
    </div>
    <script src="${pageContext.request.contextPath}/resources/utility/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/utility/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>