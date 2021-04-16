<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*"
    import="gameManager.*"
    %>
    
    
    <%
	String action_name = (String) request.getAttribute("action_name");
	String ricerca = (String) session.getAttribute("ricerca");
	ArrayList<?> array = (ArrayList<?>) session.getAttribute("giochi");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GameSquare - Giochi</title>
</head>
<body>

<!-- Header section start -->
     	  <%@ include file="fragment/header.jsp" %>
	<!-- Header section end -->

<section class="cateProd-section">
		<div class="container">
			<div class="row justify-content-center">
				<%  	if(action_name!=null) {%>
				<h1><%=action_name%></h1>
					<% 	} else {%>
					<h1><%=ricerca%></h1>
			<% 	}%>
			</div>
		</div>
		<div class="container border-cateProd">
			<div class="row">
				<div class="col-md-10">
					<div class="row justify-content-start">
					
					<%
					GiocoDAO GameModel=new GiocoDAO();
				
					if(action_name!=null){
					if(action_name.equals("Tutti i giochi")){
						array = GameModel.viewGame();
					}
					}
					
					if(array!=null && array.size()!=0)
					{
						Iterator<?> it = array.iterator();
						while (it.hasNext()) 
						{
							Gioco bean = (Gioco) it.next();	
		
					%>
					
						<div class="col-sm-4 spacerProdotti">
							<a href="Game?action=gioco&id=<%=bean.getIdGioco()%>"><img src=<%=bean.getImgpath()+"/img1.png"%> width="150" height="150" class="imgItem"></a>
							<a class="game-name" href="Game?action=gioco&id=<%=bean.getIdGioco()%>"><p><b><%=bean.getNome()%></b></p></a>
						</div>
					<% 	}
						} else { %>
							<div class="row justify-content-center" style="margin-left: 20px"><h4>Nessun gioco disponibile.</h4></div>
							<% if(utenteLoggato!=null) { 
									if(utenteLoggato.getTipo().equals("user") || utenteLoggato.getTipo().equals("mod")){%>
							<div class="row justify-content-center" style="margin-left: 20px"><h4>Non trovi il tuo gioco preferito? <a href="nuova-richiesta-gioco.jsp">Richiedilo subito!</a></h4></div>
							<%		}else{ %>
							<div class="row justify-content-center" style="margin-left: 20px"><h4>Non trovi il tuo gioco preferito? <a href="#">Aggiungilo subito!</a></h4></div>
							<%		}
							} else{%>
							<div class="row justify-content-center" style="margin-left: 20px"><h4>Non trovi il tuo gioco preferito? <a href="login-page.jsp">Richiedilo subito!</a></h4></div>
							<% }
							}%>
					</div>
				</div>
			</div>
		</div>
	</section>

</body>
</html>