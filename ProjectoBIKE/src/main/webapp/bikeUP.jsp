<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="bike" class="coding.model.Bike">
		<jsp:setProperty name="bike" property="*"/>
	</jsp:useBean>
	
	<jsp:useBean id="bikeCRUD" class="coding.controller.Bike_C">
	</jsp:useBean>
	
	<% 
		if(bikeCRUD.existe(bike.getId_bike())){
			bikeCRUD.actualizar(bike.getId_bike(), bike.getMarca(), bike.getModelo(), bike.getImagem(), bike.getEstado(), bike.getHora_entrada());
	%>
			<script>
				alert("ACTUALIZADO!");
				window.location = "home.jsp"
			</script>
	<%		
		}else{
	%>
			<script>
				alert("BIKE NAO EXISTENTE!");
				window.location = "home.jsp"
			</script>
	<%
		}
	%>
</body>
</html>