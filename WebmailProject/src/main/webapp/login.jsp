<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<jsp:useBean id="Logger" class="coding.beans.ClientInfo">
		<jsp:setProperty name="Logger" property="*"/>
	</jsp:useBean>
	
	<jsp:useBean id="LoggerD" class="coding.beans.UsersData">
	</jsp:useBean>
	
	<%if(LoggerD.checkUser(Logger.getLogin(), Logger.getPass())){%>
		<script>
    		window.location = "send_email.html"
		</script>
	<%} else %> <script> alert("USUARIO NAO CADASTRADO! POR FAVOR, REGISTA-SE."); window.location = "login.html" </script>
	
</body>
</html>