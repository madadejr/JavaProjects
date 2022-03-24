<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="ciclis" class="coding.model.Ciclista">
		<jsp:setProperty name="ciclis" property="*"/>
	</jsp:useBean>
	
	<jsp:useBean id="ciclisCRUD" class="coding.controller.Ciclista_C">
	</jsp:useBean>
	
	<jsp:useBean id="log" class="coding.controller.Login">
	</jsp:useBean>
	
	<% 
		if(ciclisCRUD.existeID(ciclis.getId_ciclista())){
			ciclisCRUD.actualizar(ciclis.getId_ciclista(), ciclis.getNome(), ciclis.getContacto(), ciclis.getMorada(), ciclis.getUname(), ciclis.getPsw());
	%>
			<script>
				alert("ACTUALIZADO!");
				window.location = "home.jsp"
			</script>
	<%		
		}else{
	%>
			<script>
				alert("CICLISTA NAO EXISTENTE!");
				window.location = "home.jsp"
			</script>
	<%
		}
	%>		
</body>
</html>