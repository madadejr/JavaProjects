<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registo</title>
</head>
<body>
	<jsp:useBean id="Logger" class="coding.beans.ClientInfo">
		<jsp:setProperty name="Logger" property="*"/>
	</jsp:useBean>
	
	<jsp:useBean id="LoggerD" class="coding.beans.UsersData">
	</jsp:useBean>
	
	<% 
		if(!LoggerD.userExist(Logger.getLogin())){
			LoggerD.storeNewUser(Logger.getLogin(), Logger.getNome(), Logger.getPass(), Logger.getEmail()); %>		
			
			<script>
    			window.location = "Properties.jsp"
			</script>
		<%}else{%>
			<script>
				alert("NAO FOI POSSIVEL REGISTAR. \nUTILIZADOR JA EXISTE!");
    			window.location = "registo.html"
			</script>
		<%}%>
</body>
</html>