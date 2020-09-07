<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Lido Vetrana - Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/fonts/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/login/css/style.css">
</head>

<body>
<div class="container">
        <nav class="navbar navbar-light navbar-expand-md fixed-top d-inline navigation-clean">
            <div class="container-fluid"><a class="navbar-brand" href="/guidafederico/view/home">Lido Vetrana</a><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse d-xl-flex justify-content-xl-start"
                    id="navcol-1" style="opacity: 1;">
                    <ul class="nav navbar-nav ml-auto">
                        <li class="nav-item" role="presentation"><a class="nav-link" href="/guidafederico/view/personalarea/">Area Personale</a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link" href="/guidafederico/view/home/registration">Registrazione</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
    <div class="login-clean" style="opacity: 1;filter: brightness(112%);">
        <form id="fLogin" method="post" action=j_security_check>
            <h2 class="sr-only">Login Form</h2>
            <div class="illustration"><img src="${pageContext.request.contextPath}/resources/login/img/beach-sunset-summer-logo.jpg" style="width: 240px;"></div>
             <%
			String show = request.getParameter("show");
			if (show != null && show.equals("logerror"))
				out.println("<div class=\"alert alert-danger\" role=\"alert\">"+
						    "Email e/o password errata"+
						    "</div>");
			%>
			<%	ServletContext sc = getServletContext();
				if(sc.getAttribute("message")!=null){
					out.println("<div class=\"alert alert-info\" role=\"alert\">"+
								sc.getAttribute("message")+
					    		"</div>");
					sc.removeAttribute("message");
				}
				if(sc.getAttribute("email")!=null){
					out.println("<div class=\"form-group\"><input  class=\"form-control\" type=\"email\" name=\"j_username\" placeholder=\"Email\" required value=\""+sc.getAttribute("email")+"\"></div>");
					sc.removeAttribute("email");
				}else
					out.println("<div class=\"form-group\"><input  class=\"form-control\" type=\"email\" name=\"j_username\" placeholder=\"Email\" required ></div>");
				
			%>
            <div class="form-group"><input  class="form-control" type="password" name="j_password" placeholder="Password" required></div>
            <div class="form-group"><button class="btn btn-primary btn-block" type="submit" style="background-color: rgb(255,15,0);">Accedi</button></div>
            <a class="forgot">Clicca qui se hai dimenticato la tua password!</a>
         </form>
    </div>
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
    <script type="text/javascript">
		var contextPath='<%=request.getContextPath()%>';
	</script>
    <script src="${pageContext.request.contextPath}/resources/login/js/login.js"></script>
</body>

</html>