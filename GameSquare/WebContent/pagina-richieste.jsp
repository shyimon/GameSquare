<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
     import="gameManager.*"
    import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GameSquare - Richieste di aggiunta giochi nuovi</title>
</head>
<body>
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
<h1> Nuove richieste per l'inserimento di giochi al sito</h1>
<br>
	<div class="container">
		<div>
			<div>
				<%
				RichiestaGiocoDAO model=new RichiestaGiocoDAO();
				ArrayList<RichiestaGioco> array = model.viewRequest();
				
				if(array.size()!=0)
				{
						
					Iterator<?> it2 = array.iterator();
					while (it2.hasNext()) 
					{
						RichiestaGioco bean = (RichiestaGioco) it2.next();	
				%>
					<div class="col-md-12" id="request<%=bean.getIdRichiesta()%>"><h4><%=bean.getNomeGioco()%> (<%=bean.getPublisher()%>, <%=bean.getAnno()%>) - richiesto da <%=bean.getUsernameUtente()%>. (Fonte: <a href="http://<%=bean.getFonte()%>"><%=bean.getFonte()%></a>)</h4></div>
							
				
				
				<% 	}
					} else { %>
						<div class="col-md-12"><h4>Nessuna richiesta al momento.</h4></div>
				<% } %>
			</div>
		</div>
	</div>

</body>
</html>