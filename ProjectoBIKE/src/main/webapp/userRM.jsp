<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="utiliz" class="coding.model.Utilizador">
		<jsp:setProperty name="utiliz" property="*"/>
	</jsp:useBean>
	
	<jsp:useBean id="userCRUD" class="coding.controller.Utilizador_C">
	</jsp:useBean>
	
	<jsp:useBean id="log" class="coding.controller.Login">
	</jsp:useBean>
	
	<% 
		if(userCRUD.existeID(utiliz.getId_user())){
			userCRUD.remover(utiliz.getId_user());
	%>
			<script>
				alert("REMOVIDO!");
				window.location = "home.jsp"
			</script>
	<%		
		}else{
	%>
			<script>
				alert("USUARIO NAO EXISTENTE!");
				window.location = "home.jsp"
			</script>
	<%
		}
	%>
</body>
</html>