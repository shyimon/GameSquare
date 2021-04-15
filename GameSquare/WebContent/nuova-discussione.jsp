<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*"
    import="gameManager.*"
    import="threadManager.*"%>
    
    <%	
	GiocoDAO gameModel = new GiocoDAO();
    int idGioco = Integer.parseInt(request.getParameter("gameid"));
    ArrayList <Gioco> array = gameModel.viewGame("id", ""+idGioco);
    Gioco game = array.get(0);
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nuova Discussione</title>



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
<body>
<header>
			<%@ include file="fragment/header.jsp" %>
		</header>
<h1>Nuova discussione su <%=game.getNome()%></h1>
<h3>Scritta da <%=utenteLoggato.getUsername()%> (<%=utenteLoggato.getTipo()%>)</h3>

<form>
			<div class="container">

				<div class="form-top-grid">
					<div>
						<span>Titolo della discussione:<label>*</label></span> <input
							name="thread_title" type="text" id="thread_title"
							placeholder="Inserire un titolo..." required>
					</div>
					<label for="tipo">Tipo:</label>
							<select id="thread_type" name="thread_type">
 									 <option value="Opinioni">Opinioni</option>
  									<option value="Suggerimenti">Suggerimenti</option>
 									 <option value="Spoiler">Spoiler</option>
  									<option value="Torneo">Torneo</option>
							</select>
					<div>
						<span>Testo</span> <textarea name="thread_text"
							id="thread_text" placeholder="Inserire un testo (opzionale)" ></textarea>
					</div>
					<div class="clear"></div>
				</div>
				<input type="hidden" id="gameID" name="gameID" value="<%=game.getIdGioco()%>">
				<input type="hidden" id="username" name="username" value="<%=utenteLoggato.getUsername()%>">
				<% if(utenteLoggato.getTipo().equals("mod")|| utenteLoggato.getTipo().equals("dev")){ %>
				<div class="button2">
					<input type="button" id="buttoncrea" class="setButton"
						value="Crea thread">
				</div>
				<%}else{ %>
				<div class="button2">
					<input type="button" id="buttonrichiedi" class="setButton"
						value="Richiedi thread">
				</div>
			</div>
			<%} %>
		
</form>

</body>

<script>
document.getElementById("thread_type").selectedIndex = -1;

$("#buttoncrea").on('click', function validate(){	
	var thread_title  = $('#thread_title').val();
	var thread_type = $('#thread_type').val();
	var thread_text = $('#thread_text').val();
	var gameId = $('#gameID').val();
	var username = $('#username').val();
	
	//alert('pulsante premuto' + '1'+thread_title +'2'+ thread_type +'3'+ thread_text+'4'+ gameId +'5'+ username);
	
	if(thread_title === "")
	{
		Swal.fire({ 
			title: 'Inserire il titolo della discussione',
			type: 'warning',
			  confirmButtonColor: '#3085d6',
			  confirmButtonText: 'OK',
			width: '400px',
			})
		setTimeout(function(){location.href="nuova-discussione.jsp"} , 135000);
		return false;
	}
	else 
	{
		$.ajax({ 
		type: "POST",
		url: "CreateThread",
		data: {"thread_title": thread_title, "thread_type": thread_type,"thread_text":thread_text, "gameId":gameId, "username": username},
		success: function(results){
			Swal.fire({ //SECONDO POPUP
				title: 'Discussione creata!',
				timer: 40000,
				type: 'success',
				showCancelButton: false,
				showConfirmButton: false,
				width: '400px',
				})
			setTimeout(function(){location.href="Game?action=gioco&id="+gameId+"#ThreadArea"} , 2000);
			}    
		})
	}
	
	
	
});



</script>
</html>