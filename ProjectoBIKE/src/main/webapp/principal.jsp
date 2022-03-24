<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div id="id01" class="modal">
  	<jsp:useBean id="log" class="coding.controller.Login">
	</jsp:useBean>
  
  <% 
  	log.writeAdmin("empty");
  %>
  <form class="modal-content animate" action="login.jsp" method="post" style="max-width: 500px;">
    <div class="imgcontainer">
      <img src="./images/login2.png" alt="Login" class="avatar">
    </div>

    <div class="container">
      <label for="uname"><b>Utilizador</b></label>
      <input type="text" placeholder="Digite o utilizador" name="uname" required>

      <label for="psw"><b>Senha</b></label>
      <input type="password" placeholder="Digite a senha" name="psw" required>
        
      <button type="submit">Login</button>
    </div>

    <div class="container" style="background-color:#f1f1f1">
      <span class="acc">Criar <a href="register.html">conta?</a></span>
    </div>
  </form>
</div>
</body>
</html>