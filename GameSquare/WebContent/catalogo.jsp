<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*"
    import="gameManager.*"
    %>
    
    
    <%
	String action_name = (String) request.getAttribute("action_name");
	String ricerca = (String) session.getAttribute("ricerca");
	ArrayList<?> array = (ArrayList<?>) session.getAttribute("giochi");
	GiocoDAO GameModel=new GiocoDAO();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GameSquare - Giochi</title>
<style>
.dropdown {
  float: left;
  overflow: hidden;
}

.dropdown .dropbtn {
  font-size: 17px;    
  border: none;
  outline: none;
  color: white;
  padding: 14px 16px;
  background-color: inherit;
  font-family: inherit;
  margin: 0;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  float: none;
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  text-align: left;
}

.topnav a:hover, .dropdown:hover .dropbtn {
  background-color: #555;
  color: white;
}

.dropdown-content a:hover {
  background-color: #ddd;
  color: black;
}

.dropdown:hover .dropdown-content {
  display: block;
}

</style>

</head>
<body>

<!-- Header section start -->
     	  <%@ include file="fragment/header.jsp" %>
	<!-- Header section end -->

<section class="cateProd-section">
		<div class="container">
			<div class="row justify-content-center">
				<%  	if(action_name!=null) {%>
				<h1><%=action_name%></h1>
					<% 	} else {%>
					<h1><%=ricerca%></h1>
			<% 	}%>
			</div>
		</div>
		
		
			<div class="dropdown">
    		<button class="dropbtn">Ricerca per Publisher
      				<i class="fa fa-caret-down"></i>
    		</button>
    		<div class="dropdown-content">
    		<%try{
					 ArrayList<String> list = GameModel.GetPublishers();
						for(String x:list){ %>
				<a href="Game?action=publisher&pub=<%=x%>" ><%=x%></a>
      			<% }
					} catch (Exception e) {
						System.out.println(e.getMessage());
									}  											    													    
								 %>
											 
    		</div>
		</div> 
		
		<div class="dropdown">
    		<button class="dropbtn">Ricerca per Genere
      				<i class="fa fa-caret-down"></i>
    		</button>
    		<div class="dropdown-content">
    		<%try{
					 ArrayList<String> list = GameModel.GetGenres();
						for(String x:list){ %>
				<a href="Game?action=genre&gen=<%=x%>" ><%=x%></a>
      			<% }
					} catch (Exception e) {
						System.out.println(e.getMessage());
									}  											    													    
								 %>
											 
    		</div>
		</div> 

		
		
		<div class="container border-cateProd">
			<div class="row">
				<div class="col-md-10">
					<div class="row justify-content-start">
					
					<%
					if(array!=null && array.size()!=0)
					{
						Iterator<?> it = array.iterator();
						while (it.hasNext()) 
						{
							Gioco bean = (Gioco) it.next();	
		
					%>
					
						<div class="col-sm-4 spacerProdotti">
							<a href="Game?action=gioco&id=<%=bean.getIdGioco()%>"><img src=<%=bean.getImgpath()+"/img1.png"%> width="150" height="150" class="imgItem"></a>
							<a class="game-name" href="Game?action=gioco&id=<%=bean.getIdGioco()%>"><p><b><%=bean.getNome()%></b></p></a>
						</div>
					<% 	}
						} else { %>
							<div class="row justify-content-center" style="margin-left: 20px"><h4>Nessun gioco disponibile.</h4></div>
							<% if(utenteLoggato!=null) { 
									if(utenteLoggato.getTipo().equals("user") || utenteLoggato.getTipo().equals("mod")){%>
							<div class="row justify-content-center" style="margin-left: 20px"><h4>Non trovi il tuo gioco preferito? <a href="nuova-richiesta-gioco.jsp">Richiedilo subito!</a></h4></div>
							<%		}else{ %>
							<div class="row justify-content-center" style="margin-left: 20px"><h4>Non trovi il tuo gioco preferito? <a href="#">Aggiungilo subito!</a></h4></div>
							<%		}
							} else{%>
							<div class="row justify-content-center" style="margin-left: 20px"><h4>Non trovi il tuo gioco preferito? <a href="login-page.jsp">Richiedilo subito!</a></h4></div>
							<% }
							}%>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	</body>
</html>