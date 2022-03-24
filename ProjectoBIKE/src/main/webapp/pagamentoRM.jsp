<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="recib" class="coding.model.Recibo">
		<jsp:setProperty name="recib" property="*"/>
	</jsp:useBean>
	
	<jsp:useBean id="recibCRUD" class="coding.controller.Recibo_C">
	</jsp:useBean>
	<jsp:useBean id="reservCRUD" class="coding.controller.Reserva_C">
	</jsp:useBean>
	
	<jsp:useBean id="log" class="coding.controller.Login">
	</jsp:useBean>
	
	<% 
		if(recibCRUD.existeCancel(recib.getId_recibo())){
			reservCRUD.registar(recib.getId_recibo(), recibCRUD.idBikes(recib.getId_recibo()), recibCRUD.idCiclista(recibCRUD.ciclista(recib.getId_recibo())), recibCRUD.data(recib.getId_recibo()));
			recibCRUD.remover(recib.getId_recibo());
	%>
			<script>
				alert("CANCELADO!");
				window.location = "home.jsp"
			</script>
	<%		
		}else{
	%>
			<script>
				alert("RESERVA NAO ENCONTRADA!");
				window.location = "home.jsp"
			</script>
	<%
		}
	
	%>
</body>
</html>