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
						<span>Fonte:<label>*</label></span> <textarea name="gamereq_source"
							id="gamereq_source" placeholder="Inserire una fonte..." required ></textarea>
					</div>
					<div class="clear"></div>
				</div>
				<input type="hidden" id="username" name="username" value="<%=utenteLoggato.getUsername()%>">
	
				<div class="button2">
					<input type="button" id="buttonrichiedi" class="setButton"
						value="Richiedi gioco">
				</div>
			</div>
			
		
</form>
</body>
</html>