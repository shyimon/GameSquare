<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
     import="java.util.*"
    import="gameManager.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GameSquare - Richiedi un nuovo gioco</title>


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
		
		<h1>Richiesta per l'inserimento di un nuovo gioco</h1>
		
		<form>
			<div class="container">

				<div class="form-top-grid">
					<div>
						<span>Titolo del nuovo gioco:<label>*</label></span> <input
							name="gamereq_title" type="text" id="gamereq_title"
							placeholder="Inserire un titolo..." required>
					</div>
					<div>
						<span>Publisher:<label>*</label></span> <input
							name="publisher" type="text" id="publisher"
							placeholder="Inserire un publisher..." required>
					
					<label for="year">Anno di uscita:</label>
							<select id="game_year" name="game_year">
								<% for (int anno = 2021; anno>=1958; anno--){ %>
 									<option value="<%=anno%>"><%=anno%></option>
 									<%} %>
							</select>
					</div>
					
					<div>
						<label for="genre">Genere:</label>
							<select id="game_genre" name="game_genre">
 									<option value="Altro">Altro</option>
 									<option value="Applicazione">Applicazione</option>
  									<option value="Azione">Azione</option>
 									<option value="Avventura">Avventura</option>
  									<option value="Arcade">Arcade</option>
  									<option value="Action-Adventure">Action-Adventure</option>
  									<option value="Action RPG">Action RPG</option>
  									<option value="Educativo">Educativo</option>
  									<option value="Gestionale">Gestionale</option>
  									<option value="JRPG">JRPG</option>
  									<option value="Musicale">Musicale</option>
  									<option value="Party">Party</option>
  									<option value="Platform">Platform</option>
  									<option value="Picchiaduro">Picchiaduro</option>
  									<option value="Puzzle">Puzzle</option>
  									<option value="RPG">RPG</option>
  									<option value="Roguelike">Roguelike</option>
  									<option value="Sparatutto">Sparatutto</option>
  									<option value="Sport">Sport</option>
  									<option value="Stealth">Stealth</option>
  									<option value="Strategico">Strategico</option>
							</select>
						</div>
					
					<div>
						<span>Fonte:<label>*</label></span> <textarea name="gamereq_source"
							id="gamereq_source" placeholder="Inserire il link a una fonte valida..." required ></textarea>
					</div>
					<div class="clear"></div>
				</div>
				<input type="hidden" id="username" name="username" value="<%=utenteLoggato.getUsername()%>">
	
				<div class="button2">
					<input type="button" id="buttonrequest" class="setButton"
						value="Richiedi gioco">
				</div>
			</div>
			
		
</form>
</body>

<script src="script/CreazioneRichiestaGioco.js"></script>
</html>