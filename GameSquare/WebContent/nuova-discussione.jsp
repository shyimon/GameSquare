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
</head>
<body>
<header>
			<%@ include file="fragment/header.jsp" %>
		</header>
<h1>Nuova discussione su <%=game.getNome()%></h1>
<h3>Scritta da <%=utenteLoggato.getUsername()%> (<%=utenteLoggato.getTipo()%>)</h3>

</body>
</html>