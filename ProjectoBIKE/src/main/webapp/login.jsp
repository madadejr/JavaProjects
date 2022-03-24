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
	<jsp:useBean id="ciclis" class="coding.model.Ciclista">
		<jsp:setProperty name="ciclis" property="*"/>
	</jsp:useBean>
	
	<jsp:useBean id="log" class="coding.controller.Login">
	</jsp:useBean>
	
 	<%if(log.validacao(utiliz.getUname(), utiliz.getPsw())){%>
 		<script> 
 			window.location = "home.jsp"; 
		</script> 
	<%} else if(log.validacaoC(ciclis.getUname(), ciclis.getPsw())){ %> 
		<script> 
 			window.location = "home.jsp"; 
		</script>
	<%} else {%>
		<script> alert("USUARIO NAO CADASTRADO! POR FAVOR, REGISTA-SE."); window.location = "principal.jsp" </script>
	<%}%>
</body>
</html>