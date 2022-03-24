<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="reserv" class="coding.model.Reserva">
		<jsp:setProperty name="reserv" property="*"/>
	</jsp:useBean>
	
	<jsp:useBean id="reservCRUD" class="coding.controller.Reserva_C">
	</jsp:useBean>
	
	<jsp:useBean id="log" class="coding.controller.Login">
	</jsp:useBean>
	
	<%
		if(reservCRUD.existe(reserv.getId_reserva(), reserv.getHora_saida())){
			reservCRUD.actualizar(reserv.getId_reserva(), reserv.getId_bike(), reserv.getId_ciclista(), reserv.getHora_saida());
	%>
			<script>
				alert("ACTUALIZADO!");
				window.location = "home.jsp"
			</script>
	<%		
		}else{
	%>
			<script>
				alert("RESERVA NAO EXISTENTE!");
				window.location = "home.jsp"
			</script>
	<%
		}
	%>
</body>
</html>