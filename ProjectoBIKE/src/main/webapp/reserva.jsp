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
	if(reserv.getId_bike() != 0 && reserv.getId_ciclista() != 0 && reserv.getHora_saida() != null){
		if(!reservCRUD.existe(reserv.getId_bike(), reserv.getHora_saida())){
			reservCRUD.registar(reservCRUD.pegarUltimo()+1, reserv.getId_bike(), reserv.getId_ciclista(), reserv.getHora_saida());
	%>
			<script>
				alert("SUCESSO!");
				window.location = "home.jsp"
			</script>
	<%		
		}else{
	%>
			<script>
				alert("RESERVA JA EXISTENTE!");
				window.location = "home.jsp"
			</script>
	<%
		}
	} else { 
	
	%>
		<script>
			alert("PREENCHA OS CAMPOS OBRIGATORIOS!");
			window.location = "home.jsp"
		</script>
	<%	
	}
	%>
</body>
</html>