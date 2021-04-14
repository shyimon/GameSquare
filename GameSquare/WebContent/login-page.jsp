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
</head>
<body>
<h1>Rudimentale pagina di login, da aggiustare</h1>
<br>

<% 	Boolean login = (Boolean) request.getAttribute("login");	
												if(login!=null) 
												{
													if(!login) %>
													<i style="color: red" class="glyphicon glyphicon-remove"> </i><h4 style="color:red; display: inline-block;"> Email e/o password errati!</h4>
							<%					}
											
										
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


</body>
</html>