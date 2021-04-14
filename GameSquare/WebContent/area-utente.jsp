<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*"
    import="util.*"
    import="userManager.*"
    %>
  
	
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>GameSquare - Area Utente</title>
 	<link rel="stylesheet" type="text/css" href="css/area-utente.css">  
</head>

<body style="margin:0">


   <%@ include file="fragment/header.jsp" %>
	
	
<section class="utente-section">
		<div class="container">
			<div class="row justify-content-center">
				<h1>Area Utente -  <%=utenteLoggato.getUsername()%></h1>
			</div>
		</div>
		<div class="container border-utentepage">
			<div class="row justify-content-start">
				<div class="col-md-6">
					<h4 id="list1" class="utenteh4">Informazioni Utente</h4>
				</div>
			</div>
		</div>
		<!-- Info utente -->
		<div class="container" id="utdiv">
			<div class="container spacerUP borderutdiv" id="infPut">
				<div class="row justify-content-start">
					<div class="col-md-3">
						<div class="row">
							<h4 class="upH4">Username: </h4>
							<p class="upPCircle"><%=utenteLoggato.getUsername()%></p>
						</div>
					</div>
					<div class="col-md-3">
						<div class="row">
							<h4 class="upH4">E-Mail: </h4>
							<p class="upPCircle"><%=utenteLoggato.getEmail()%></p>
						</div>
					</div>
					<div class="col-md-5">
						<div class="row">
							<h4 class="upH4">Tipo: </h4>
							<p class="upPCircle"><%=utenteLoggato.getTipo()%></p>
						</div>
					</div>
				</div>
				<div class="col-md-5">
						<div class="row">
							<h4 class="upH4">Punteggio abilità: </h4>
							<p class="upPCircle"><%=utenteLoggato.getPunteggio()%></p>
						</div>
					</div>
				</div>
			</div>
	</section>
</body>
</html>