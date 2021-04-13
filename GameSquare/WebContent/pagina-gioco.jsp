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
</head>
<body>

<!-- sezione con le info del gioco, immagine ecc -->

	<section class="gameinfo-section">
		<div class="container border-prodsing">
			<div class="row">
				<div class="col-md-12">
					<h3><%=bean.getNome()%></h3>
				</div>
			</div>
			<div class="row" id="spacerPS">
					<img id="imgXD" src="<%=bean.getImgpath()%>/img1.png" alt="Img" style="width:100%; max-width:280px; height: 280px;" class="imgItem2">
					<div id="myModal" class="modal">
						<img class="modal-content" id="img01" style="width: 350px;">
					</div>
				<div class="col-md-8" id="spacerDettagli">
					<div>
					<br>
						<p id="stiletitle">Publisher: </p>
						<p class="stilep"><%=bean.getPublisher()%></p>
					</div>
					<div>
					<br>
						<p id="stiletitle">Anno: </p>
						<p class="stilep" id="model_prod"><%=bean.getAnno()%></p>
					</div>
					<div>
						<br>
						<p id="stiletitle">Genere: </p>
							<p class="stilep"> <%=bean.getGenere()%> </p>
					</div>
					<div>
						<br>
						<p id="stiletitle">Punteggio: </p>
							<p class="stilep"> <%=bean.getPunteggio()%> </p>
					</div>
					<div>
						<br>
						<p id="stiletitle">Media dei voti: </p>
							<p class="stilep"> <%=bean.getMediaVoti()%> </p>
					</div>
				
						</div>
					</div>
				</div>
		<div class="container spacerDescrizioneProd">
			<div class="row">
				<div class="col-md-3">
					<h2 id="titlePPS">DESCRIZIONE</h2>
				</div>
				<div class="descstyle col-md-9">
					<hr>
				</div> 
				<p id="descP"><%=bean.getDescrizione()%></p>
			</div>
		</div>
		
		<div class="container spacerDescrizioneProd">
			<div class="row">
				<div class="col-md-3">
					<h2 id="titlePPS">DISCUSSIONI</h2>
				</div>
				<div class="descstyle col-md-9">
					<hr>
				</div> 

<div>
		<div>
			<%
			ThreadDAO model_thread=new ThreadDAO();
			ArrayList<GameThread> array = model_thread.getThread(bean.getIdGioco());
         
			
			if(array.size()!=0)
			{
			
								
				Iterator<?> it2 = array.iterator();
				while (it2.hasNext()) 
				{
					GameThread tbean = (GameThread) it2.next();	
			%>
			
			
				
					<div class="row justify-content-center">
						<a href="#"> (<%=tbean.getTipoThread()%>) <b><%=tbean.getTitolo()%></b> - scritta da <%=tbean.getUsernameUtente()%> </a>
					</div>
				</div>
				<br>
			
			
			<% 	}
				} else { %>
					<div class="col-md-12"><h4>Nessuna Discussione.</h4><a href="#">Sii il primo a crearla!</a></div>
			<% } %>
			</div>
		</div>

</body>
</html>