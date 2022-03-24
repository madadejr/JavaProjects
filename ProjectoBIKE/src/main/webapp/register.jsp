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
	if(ciclis.getNome() != null && ciclis.getContacto() != null && ciclis.getMorada() != null && ciclis.getUname() != null && ciclis.getPsw() != null){
		if(!ciclisCRUD.existe(ciclis.getUname())){
			ciclisCRUD.registar(ciclisCRUD.pegarUltimo()+1, ciclis.getNome(), ciclis.getContacto(), ciclis.getMorada(), ciclis.getUname(), ciclis.getPsw());
			log.writeAdmin("ciclista");
	%>
			<script>
				alert("SUCESSO!");
				window.location = "principal.jsp"
			</script>
	<%		
		}else{
	%>
			<script>
				alert("CICLISTA JA EXISTENTE!");
				window.location = "principal.jsp"
			</script>
	<%
		}
	} else { %>
		<script>
			alert("PREENCHA OS CAMPOS OBRIGATORIOS!");
			window.location = "register.html"
		</script>
	<%	
	}
	%>
</body>
</html>