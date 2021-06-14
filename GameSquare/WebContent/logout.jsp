<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
   <head>
      <title>Logout completato</title>
       <script type="text/javascript">
        function preventBack() { window.history.forward(); }
        setTimeout("preventBack()", 0);
        window.onunload = function () { null };
    </script>
   </head>
   
   <body style="text-align: center;">
 
      <h5>Operazione di logout completata.</h5>
		<a href="index.jsp">torna alla home</a>
   </body>
</html>