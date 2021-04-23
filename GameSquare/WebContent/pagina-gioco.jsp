<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*"
    import="gameManager.*"
    import="threadManager.*"%>
    
  <%	
	Gioco bean = (Gioco) request.getAttribute("game");
	
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
				
					<p id="stiletitle">Punteggio: <h4 id="score"><%=bean.getPunteggio()%></h4></p>
					<p id="stiletitle">Media dei voti: <%=bean.getMediaVoti()%></p>
			</div>
			
			<div class="gamesec2">
				<h4 id="titlePPS">DESCRIZIONE</h4>
				<p id="descP"><%=bean.getDescrizione()%></p>
				<p id="stiletitle">Publisher: <%=bean.getPublisher()%></p>
				<p id="stiletitle">Anno: <%=bean.getAnno()%></p>
				<p id="stiletitle">Genere: <%=bean.getGenere()%></p>
				
				
				<br>
				<div class="VoteArea">
				<% if(utenteLoggato!=null){ 
				VotoDAO voteModel = new VotoDAO();
				ArrayList <Voto> voti = voteModel.getVote("Gioco", " "+bean.getIdGioco(), "utente", utenteLoggato.getUsername()); 
				if(voti.size()!=0){ 
						Voto voto = voti.get(0);%>
					<h4>Hai votato questo gioco con <%=voto.getValutazione()%> <input type="button" id="deleteVote" class="setButton" value="Cancella voto"></h4>
				<% }%>
				
				<input type="hidden" id="usrValue" value="<%=utenteLoggato.getUsername()%>">
				<% } %>
				<form>
				<input type="hidden" id="gameIdValue" value="<%=bean.getIdGioco()%>">
					<label for="vote">Il tuo voto:</label>
						<select id="vote" name="vote">
							<% for (int i = 1; i<=10; i++){ %>
 								<option value="<%=i%>"><%=i%></option>
 							<%} %>
						</select>
						<% if(utenteLoggato!=null){ %>
					<input type="button" id="voteButton" class="setButton" value="Valuta">
					<% }else{ %>
					<a href="login-page.jsp"> Valuta </a>
					<% } %>
				</form>
				
				</div>
		   
		   </div>
			
		</div>
			
			
			
		<div class="ThreadArea">
			<div class="thread">
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
								<h4>Nessuna Discussione.
								<% if(utenteLoggato != null)
									{%>					
										<a href="Thread?action=newdiscussion&gameid=<%=bean.getIdGioco()%>">Sii il primo a crearla!</a></
									
								<%	}
									else
									{%>
										<a href="login-page.jsp">Sii il primo a crearla!</a></
									<%}%></h4>
								<% } %>	
						</div>	
				</div>
			<div class="crea-discussione">
				<% if(utenteLoggato != null)
				{%>					
					<a href="Thread?action=newdiscussion&gameid=<%=bean.getIdGioco()%>">Crea una nuova discussione su <%=bean.getNome()%></a></		
				<%}
					else
				{%>
					<a href="login-page.jsp">Crea una nuova discussione su <%=bean.getNome()%></a>
				<%}%>
		</div>
</div>
</div>
</body>

<script>
document.getElementById("vote").selectedIndex = -1;
var username  = $('#usrValue').val();
var game_id = $('#gameIdValue').val();

$("#voteButton").on("click", function vote() {
	var vote_value = $('#vote').val();
    
   
    if(vote_value === null)
	{
		Swal.fire({ 
			title: 'Inserire un voto valido',
			type: 'warning',
			  confirmButtonColor: '#3085d6',
			  confirmButtonText: 'OK',
			width: '400px',
			})
		setTimeout(function(){location.href="pagina-gioco.jsp"} , 135000);
		return false;
	} 
    else
	{
    	//alert(username + " ha votato " +game_id+" con "+vote_value );
    	$.ajax({ //INVOCAZIONE AJAX
		  	type: "POST",
		    url: "AddVote",
		    data: {"username" : username, "game_id": game_id, "vote_value": vote_value},
		    success: function(results){
		    	Swal.fire({ //SECONDO POPUP
		  			  title: 'Valutazione aggiunta!',
		  			  timer: 1000,
		  			  type: 'success',
		  			  showCancelButton: false,
		  			  showConfirmButton: false,
		  			  width: '400px',
		  			})
		  			setTimeout(function(){location.href="Game?action=gioco&id="+game_id} , 1000);
			  }
		})
    }
});


    $('#deleteVote').on("click", function deleteVote() {
    	
						
    	//alert(username + " vuole togliere il voto a " +game_id );
    				$.ajax({ //INVOCAZIONE AJAX
					  	type: "GET",
					    url: "DeleteVote",
					    data: {"username" : username, "game_id": game_id},
					    success: function(results){
					    	Swal.fire({ //SECONDO POPUP
					  			  title: 'Voto Eliminato',
					  			  timer: 1000,
					  			  type: 'success',
					  			  showCancelButton: false,
					  			  showConfirmButton: false,
					  			  width: '400px',
					  			})
					  		setTimeout(function(){location.href="Game?action=gioco&id="+game_id} , 1000);
						  }
					})
			});
  

</script>

</html>