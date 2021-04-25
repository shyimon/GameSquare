<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="gameManager.*"
    import="java.util.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>GameSquare - Home</title>
		<link rel="stylesheet" type="text/css" href="css/index.css">
			 <script type="text/javascript">
    </script>
	</head>
	<body style="margin:0">
		<header class="index-header">
			<%@ include file="./fragment/header.jsp" %>
		</header>
	
	
	<!-- Giochi -->
		<section class="catalogo-section">
			<div class="container">
				<p>Giochi popolari</p>
			</div>
			<div>
				<%
				GiocoDAO model_product=new GiocoDAO();
				ArrayList<Gioco> array = model_product.viewGame();
	           	ArrayList<Gioco> arrayFeatured = new ArrayList<Gioco>();						
				int I=0;
				
				if((array!=null || array.size()!=0) && (arrayFeatured!=null || arrayFeatured.size()!=0))
				{
					while(I<array.size())
					{
						Gioco g = array.get(I);
						if(arrayFeatured.size() < 11)
							arrayFeatured.add(g);
						I++;
					}
				%>
				<ul class="games-list">
				<%				
					Iterator<?> it2 = arrayFeatured.iterator();
					while (it2.hasNext()) 
					{
						Gioco bean = (Gioco) it2.next();	
				%>
				
					<li class="game-object">
							<div class="imageContainer">
								<a href="Game?action=gioco&id=<%=bean.getIdGioco()%>"><img src=<%=bean.getImgpath()+"/img1.png"%> width="150" height="150" class="imgItem"></a>
							</div>
								<div class="nameContainer">
									<h5><a class="game-name" href="Game?action=gioco&id=<%=bean.getIdGioco()%>"><%=bean.getNome()%></a></h5>
							</div>
					</li>
				
				
				<% 	}
					} else { %>
						<div class="col-md-12"><h4>Nessun gioco disponibile</h4></div>
				<% } %>
				</ul>
				</div>
		</section>
	
	<!-- Classifica Utenti -->
		<section class="classifica-section">
			<div class="container">
				<p>Top 10 Utenti del sito</p>
			</div>
			<div>
				<%
				ArrayList<Utente> arrayTop10 = userModel.findTopUsers();						
				int j=0;
				
				if((arrayTop10!=null || arrayTop10.size()!=0))
				{
					
				%>
				<ul class="users-list">
				<%				
					Iterator<?> it3 = arrayTop10.iterator();
					while (it3.hasNext()) 
					{	
						j++;
						Utente user = (Utente) it3.next();	
				%>
				
					<li class="playerInTop10">
						<h5><%=j%>° <%=user.getUsername()%> (<%=user.getTipo()%>) - <%=user.getPunteggio()%> punti</h5>
					</li>
				
				
				<% 	}
					} else { %>
						<div class="col-md-12"><h4>Nessun utente disponibile.</h4></div>
				<% } %>
				</ul>
				</div>
		
	</section>
	
	</body>
</html>