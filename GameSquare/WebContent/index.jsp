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
				 <div class="row">
					<div class="headerCatalogo col-md-12">
						<p>Giochi popolari</p>
					</div>
					</div>
			<div>
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
						if(arrayFeatured.size() < 9)
							arrayFeatured.add(g);
						I++;
					}
				
									
					Iterator<?> it2 = arrayFeatured.iterator();
					while (it2.hasNext()) 
					{
						Gioco bean = (Gioco) it2.next();	
				%>
				
				
					<div class="objectItem col-md-3">
						<a href="Game?action=gioco&id=<%=bean.getIdGioco()%>"><img src=<%=bean.getImgpath()+"/img1.png"%> width="150" height="150" class="imgItem"></a>
						<div class="row justify-content-center">
							<a class="game-name" href="Game?action=gioco&id=<%=bean.getIdGioco()%>"><p><b><%=bean.getNome()%></b></p></a>
						</div>
					</div>
				
				
				<% 	}
					} else { %>
						<div class="col-md-12"><h4>Nessun gioco disponibile</h4></div>
				<% } %>
				</div>
			</div>
				</div>
		</section>
	
	</body>
</html>