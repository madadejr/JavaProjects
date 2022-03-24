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
	if(recib.getId_recibo() != 0 && recib.getTempo_permanencia() != 0 && recib.getHora_entrada() != null && recib.getCusto() != 0){
		if(recibCRUD.existe(recib.getId_recibo())){
			recibCRUD.registar(recib.getId_recibo(), recib.getHora_entrada(), recib.getTempo_permanencia(), recib.getCusto());
			reservCRUD.remover(recib.getId_recibo());
	%>
			<script>
				alert("SUCESSO!");
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