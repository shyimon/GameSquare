<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*"
    import="gameManager.*"
    import="threadManager.*"%>
    
  <%	
	Gioco bean = (Gioco) request.getAttribute("game");
	GiocoDAO gameModel = new GiocoDAO();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>GameSquare - <%=bean.getNome()%></title>
	 	<link rel="stylesheet" type="text/css" href="css/pagina-gioco.css">
</head>

<body style="margin:0">
	<header class="header">
			<%@ include file="./fragment/header.jsp" %>
	</header>

<!-- sezione con le info del gioco, immagine ecc -->
	<div class="gameinfo-section">
		<div class="game-info">
			<div class="gamesec1">
				<h3 id="game-name"><%=bean.getNome()%></h3>
				<img id="imgXD" src="<%=bean.getImgpath()%>/img1.png" alt="Img" style="width:100%; max-width:280px; height: 280px;" class="imgItem2">
				<img class="modal-content" id="img01" style="width: 350px;">
				
					<p id="stiletitle">Punteggio: <%=bean.getPunteggio()%></p>
					<p id="stiletitle">Media dei voti: <%=bean.getMediaVoti()%></p>
			</div>
			
			<div class="gamesec2">
				<h4 id="titlePPS">DESCRIZIONE</h4>
				<p id="descP"><%=bean.getDescrizione()%></p>
				<p id="stiletitle">Publisher: <%=bean.getPublisher()%></p>
				<p id="stiletitle">Anno: <%=bean.getAnno()%></p>
				<p id="stiletitle">Genere: <%=bean.getGenere()%></p>
			</div>
		</div>
			
			
		<div class="ThreadArea">
			<h2 id="titolo">Discussioni più recenti</h2>
			<div class="discussioni">
			<%
				ThreadDAO model_thread=new ThreadDAO();
				ArrayList<GameThread> array = model_thread.viewThread("Idgioco", ""+bean.getIdGioco());		
				if(array.size()!=0)
				{										
					Iterator<?> it2 = array.iterator();
					while (it2.hasNext()) 
					{
						GameThread tbean = (GameThread) it2.next();	
						if (tbean.getTipoThread().equals("Spoiler")){%>
						<a class="discussione" href="Thread?action=discussion&threadid=<%=tbean.getIdThread()%>"> <b>La discussione è marcata come SPOILER</b>: clicca per visualizzarla! - scritta da <%=tbean.getUsernameUtente()%> </a>
						<%}
						else{
						%>
							<a class="discussione" href="Thread?action=discussion&threadid=<%=tbean.getIdThread()%>"> (<%=tbean.getTipoThread()%>) <b><%=tbean.getTitolo()%></b> - scritta da <%=tbean.getUsernameUtente()%> </a>
						
						<% 	}
						}
							} else { %>
								<h4>Nessuna Discussione.</h4>
								</div>
								<% if(utenteLoggato != null)
									{%>					
										<a href="Thread?action=newdiscussion&gameid=<%=bean.getIdGioco()%>">Sii il primo a crearla!</a></
									
								<%	}
									else
									{%>
										<a href="login-page.jsp">Sii il primo a crearla!</a></
									<%}%>
				<% } %>
				
								<% if(utenteLoggato != null)
				{%>					
					<a href="Thread?action=newdiscussion&gameid=<%=bean.getIdGioco()%>">Crea una nuova discussione su <%=bean.getNome()%></a></		
				<%}
					else
				{%>
				</div>
					<a href="login-page.jsp">Crea una nuova discussione su <%=bean.getNome()%></a></
				<%}%>
	</div>
</div>
	
</body>
</html>