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
	if(utiliz.getName_user() != null && utiliz.getEmail() != null && utiliz.getType_user() != null && utiliz.getUname() != null && utiliz.getPsw() != null){
		if(!userCRUD.existe(utiliz.getUname())){
			userCRUD.registar(userCRUD.pegarUltimo()+1, utiliz.getName_user(), utiliz.getEmail(), utiliz.getType_user(), utiliz.getUname(), utiliz.getPsw());
	%>
			<script>
				alert("SUCESSO!");
				window.location = "home.jsp"
			</script>
	<%		
		}else{
	%>
			<script>
				alert("USUARIO JA EXISTENTE!");
				window.location = "home.jsp"
			</script>
	<%
		}
	} else { %>
		<script>
			alert("PREENCHA OS CAMPOS OBRIGATORIOS!");
			window.location = "home.jsp"
		</script>
	<%	
	}
	%>
</body>
</html>