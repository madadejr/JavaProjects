<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registar</title>
</head>
<body style="width: 800px;">
	<jsp:useBean id="LoggerD" class="coding.beans.UsersData">
	</jsp:useBean>
	<div>	
		<!-- header -->
		<a href="index.html">
			<img src="./images/logo.png" alt="Logo" style="width:600px;height:200px;"></img>
		</a>
		<hr></hr>
	</div>

	<div class="content">
        <div style="width:600px; height:400px;margin-top:10%;">
			<h1 style="color:#000000;text-align:center;">Seja Bem Vindo ao Webmail</h1>
			<%	String[] usuario;
				String nome="",email="",login="",senha="";
				for(int i = 0; i < LoggerD.listUsers().length; i++){
					usuario = (LoggerD.listUsers()[(LoggerD.listUsers().length)-1]).split("-");
					if(LoggerD.verifica(usuario[0])){
						nome = usuario[1];
						email = usuario[2];
						login = usuario[0];
						senha = usuario[3];
					}
				}
			%>
			<h3>Os seus dados sao:</h3>
			<b>Nome: </b> <% out.print(nome);%><br>
			<b>Email: </b> <% out.print(email);%><br>
			<b>Login: </b> <% out.print(login);%><br>
			<b>Senha: </b> <% out.print(senha);%><br>
			<h3><a href="login.html">Continuar>>></a></h3>
		</div>
	</div>
   	<div>
   	<p style="padding:2%;background-color:black;color:white;text-align:left">&copy;2021 Abdul Karim All rights reserved!</p></div>
    
</body>
</html>