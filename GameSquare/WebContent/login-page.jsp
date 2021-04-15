<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GameSquare - Login</title>
	 <script type="text/javascript">
        function preventBack() { window.history.forward(); }
        setTimeout("preventBack()", 0);
        window.onunload = function () { null };
    </script>
    <link rel="stylesheet" type="text/css" href="css/login-page.css">
</head>
<body style="margin:0">
		<header class="login-header">
			<%@ include file="./fragment/header.jsp" %>
		</header>
<br>


	<% 	Boolean correctEmail = (Boolean) request.getAttribute("correctEmail");	
					if(correctEmail!=null) 
					{
					if(!correctEmail) %>
					<i style="color: red" class="glyphicon glyphicon-remove"> </i><h4 style="color:red; display: inline-block;"> Indirizzo e-mail errato.</h4>
					<%			}
	 	Boolean correctPassword = (Boolean) request.getAttribute("correctPassword");	
					if(correctPassword!=null) 
					{
					if(!correctPassword) %>
					<i style="color: red" class="glyphicon glyphicon-remove"> </i><h4 style="color:red; display: inline-block;"> Password errata.</h4>
					<%	}
	%>

<div id="loginbox" class="loginbox">
							<form action="UserLogin" method="POST" name="login" id="login-form">
								<fieldset class="input">
									<p id="login-form-username">
										<label for="email">Email</label>
										<input id="email" type="email" name="user_email" class="inputbox" size="18" autocomplete="off">
									</p>
									<p id="login-form-password">
										<label for="password">Password</label>
										<input id="password" type="password" name="user_password" class="inputbox" size="18" autocomplete="off">
									</p>
										<input type="submit" name="Submit" class="button" value="Login">
										<div class="clear"></div>
								</fieldset>
						    </form>
					    </div>
<br>
<div class="tasti-nav" id="torna-home">
	<a href="index.jsp">Torna alla home</a>
</div>

<div class="tasti-nav" id="registrati">
	<a href="registrazione.jsp">Non hai un account? Registrati!</a>
</div>

<div class="tasti-nav" id="recupero-password">
	<a href="recupero-password.jsp">Hai dimenticato la password?</a>
</div>



</body>
</html>