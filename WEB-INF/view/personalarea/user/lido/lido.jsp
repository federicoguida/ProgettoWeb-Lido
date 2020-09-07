<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Lido Vetrana - Prenotazione lido</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/fonts/fontawesome-all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/fonts/fontawesome5-overrides.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/utility/fonts/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/lido/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
</head>

<body>
<script type="text/javascript">
	var contextPath='<%=request.getContextPath()%>';
</script>
    <nav class="navbar navbar-light navbar-expand d-flex navigation-clean">
        <div class="container">
            <div class="collapse navbar-collapse d-xl-flex justify-content-xl-start" id="navcol-2" style="opacity: 1;"><a class="navbar-brand" href="/guidafederico/view/home">Lido Vetrana</a>
                <ul class="nav navbar-nav ml-auto">
                    <li class="nav-item" role="presentation">
                    	<a class="nav-link" href="/guidafederico/view/personalarea/" style="margin: 0px;">Area Personale<i class="fas fa-user"></i></a>
                    </li>
                    <li class="nav-item" role="presentation">
                    	<a class="nav-link" href="/guidafederico/view/personalarea/logout" style="margin: 0px;">Logout&nbsp;<i class="icon ion-log-out"></i></a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div style="text-align:center;">
        <h2 class="divider-style"><span class="text-dark d-inline-flex">E' possibile prenotarsi entro le ore 15<br>Inoltre la prenotazione dei posti &egrave; valida solo per lo stesso giorno<br>
        LA CHIUSURA DEL LIDO E' PER LE ORE 20</span></h2>
    </div>
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <div class="text-uppercase text-justify border rounded border-dark shadow" data-toggle="tooltip" data-bs-tooltip="" style="background: url(${pageContext.request.contextPath}/resources/lido/img/spiaggia-png-1.png) center / auto no-repeat;height: 670px;width: auto;margin-top: 0px;max-width: 420px;margin-left: 0px;padding-left: 0px;padding-right: 0px;min-width: 420px;padding-top: 378px;"
                        title="Clicca sull'ombrellone per selezionare il tuo posto!">
                        <div id="A" data-bs-hover-animate="pulse" data-toggle="tooltip"  data-bs-tooltip="" style="text-align: center;" title="Prezzo 10&euro;"></div>
                        <div id="B" data-bs-hover-animate="pulse" data-toggle="tooltip"  data-bs-tooltip="" style="margin-top: 26px;text-align: center;" title="Prezzo 8&euro;"></div>
                        <div id="C" data-bs-hover-animate="pulse" data-toggle="tooltip"  data-bs-tooltip="" style="margin-top: 26px;text-align: center;" title="Prezzo 6&euro;"></div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div style="height: 120px;max-width: auto;padding-top: 25px;">
                        <div style="text-align:center;">
                            <h2 class="divider-style" style="font-size: 22px;"><span class="text-info d-flex">Legenda</span></h2>
                        </div>
                        <div class="d-flex float-left" style="width: 150px;">
                        	<img src="${pageContext.request.contextPath}/resources/lido/img/pngwing.com.png" style="width: 30px;filter: grayscale(100%);height: 30px;">
                            <p class="float-left">Occupato</p>
                        </div>
                        <div class="d-flex float-left" style="width: 150px;">
                        	<img src="${pageContext.request.contextPath}/resources/lido/img/pngwing.com.png" style="width: 30px;height: 30px;filter: contrast(50%)">
                            <p class="float-left">Libero</p>
                        </div>
                        <div class="d-flex float-left" style="width: 150px;">
                        	<img src="${pageContext.request.contextPath}/resources/lido/img/pngwing.com.png" style="width: 30px;filter: contrast(200%);height: 30px;">
                            <p class="float-left">Selezionato</p>
                        </div>
                    </div>
                    <span class="d-flex">Seleziona il posto sulla mappa e acquistalo! Presenta il tuo ordine all'entrata e sarai accompagnato al tuo posto!</span>
                    <div id="riepilogoOrdine" style="height: auto;max-width: auto;margin-top: 24px;background: url(${pageContext.request.contextPath}/resources/lido/img/spiaggia-png-1.png) bottom;border-width: 1px;border-style: solid;">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>Posto</th>
                                        <th>Prezzo</th>
                                        <th>Rimuovi</th>
                                    </tr>
                                </thead>
                                <tbody id="tableBody">
                                </tbody>
                            </table>
                        </div>
                        <p id="total" class="lead text-dark shadow-none d-flex float-left" style="font-size: 28px;font-family: Roboto, sans-serif;text-align: left;margin-top: 10px;">Totale: <span id="amount">0</span>&euro;&nbsp;&nbsp;</p>
                        <button id="pay" class="btn btn-primary d-inline-flex float-left" style="margin-top: 10px;">Paga ora!</button>
                        </div>
                        <span id="message" class="d-inline-flex" style="padding-left: 19px;margin-top: 10px"></span>
                        <div id="pay-now" style="display:none;width:100%;" class="card credit-card-box">
							  <div class="card-header">
							     <h3><span class="panel-title-text">Dettagli pagamento </span><img class="img-fluid panel-title-image" src="${pageContext.request.contextPath}/resources/lido/img/accepted_cards.png"></h3>
							  </div>
							  <div class="card-body">
							    <form id="payment-form">
							    <div class="form-row">
							         <div class="col-12">
							            <div class="form-group"><label for="cardNumber">Numero carta </label>
							               <div class="input-group"><input class="form-control" type="tel" id="cardNumber" required pattern="[0-9]{13,16}" placeholder="Numero di carta valido">
							                   <div class="input-group-append"><span class="input-group-text"><i class="fa fa-credit-card"></i></span></div>
							                      </div>
							               </div>
							            </div>
							         </div>
							         <div class="form-row">
							            <div class="col-7">
							               <div class="form-group"><label for="cardExpiry"><span>Data scadenza </span></label><input class="form-control" type="tel" id="cardExpiry" required pattern="^[0-1][0-9]/[0-9]{2}$" placeholder="MM / YY"></div>
							            </div>
							               <div class="col-5 pull-right">
							                   <div class="form-group"><label for="cardCVC">Codice CV</label><input class="form-control" type="tel" id="cardCVC" pattern="^[0-9]{3}$" required placeholder="CVC"></div>
							                </div>
							         </div>
							         <div class="form-row">
							            <div class="col-12"><button id="submitPay" class="btn btn-success btn-block btn-lg">Clicca qui per pagare</button></div>
							         </div>
							      </form>
							</div>
						</div>
            	</div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/resources/utility/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/utility/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/lido/js/lido.js"></script>
</body>

</html>