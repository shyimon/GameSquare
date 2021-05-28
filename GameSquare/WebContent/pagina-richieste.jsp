<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
     import="gameManager.*"
    import="java.util.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
	    <link rel="stylesheet" type="text/css" href="css/pagina-richieste.css">
		<title>GameSquare - Richieste di aggiunta giochi nuovi</title>
		<!-- JQUERY 3.4.1 -->
		<script src="https://code.jquery.com/jquery-3.4.1.js"
			integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
			crossorigin="anonymous"></script>
		
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
			integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
			crossorigin="anonymous"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
			integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
			crossorigin="anonymous"></script>
	</head>
	<body style="margin:0">
	<!-- Header section start -->
		<div class="header">
			<%@ include file="fragment/header.jsp" %>
		</div> 
	<!-- Header section end -->
	
		<%
		
			if(!(utenteLoggato.getTipo().equals("manager")))
			{
				response.sendRedirect("home.jsp");
				return;
			}
		
		%>
	<div class="text-container">
		<h1> Nuove richieste per l'inserimento di giochi al sito</h1>
	</div>	
	
	<br>
		<div class="container">
			<div>
				<div>
					<%
					RichiestaGiocoDAO model=new RichiestaGiocoDAO();
					ArrayList<RichiestaGioco> array = model.getAllRequests();
					
					if(array.size()!=0)
					{
							
						Iterator<?> it2 = array.iterator();
						while (it2.hasNext()) 
						{
							RichiestaGioco bean = (RichiestaGioco) it2.next();	
					%>
					<div class="col-md-12" id="request">
						<h4><%=bean.getNomeGioco()%> (<%=bean.getPublisher()%>, <%=bean.getAnno()%>) - richiesto da <%=bean.getUsernameUtente()%>. (Fonte: <a href="<%=bean.getFonte()%>"><%=bean.getFonte()%></a>)</h4>
						<button id="accept" class="acceptButton" value="<%=bean.getIdRichiesta()%>">Accetta Richiesta</button>  
						<button id="refuse" class="refuseButton" value="<%=bean.getIdRichiesta()%>">Rifiuta Richiesta</button>
					</div>
					
					<% 	}
						} else { %>
							<div class="col-md-12"><h4>Nessuna richiesta al momento.</h4></div>
					<% } %>
				</div>
			</div>
		</div>
	
	</body>
	<script src="script/GestioneRichieste.js"></script>
</html>