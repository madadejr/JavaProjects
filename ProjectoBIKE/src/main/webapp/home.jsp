<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="coding.model.Utilizador"%>
<%@ page import="coding.model.Bike"%>
<%@ page import="coding.model.Ciclista"%>
<%@ page import="coding.model.Reserva"%>
<%@ page import="coding.model.Recibo"%>
<!DOCTYPE html>
<html>
<head>

<style>
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  border: 1px solid #e7e7e7;
  background-color: #f3f3f3;
}

li {
  float: left;
}

li a {
  display: block;
  color: rgb(0, 128, 255);
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

li a:hover:not(.active) {
/*   background-color: #ddd; */
	background-color: #000000;
	color: white;
}

li a.active {
  color: white;
  background-color: #f44336;
}

body {font-family: Arial, Helvetica, sans-serif;}

/* Full-width input fields */
input[type=text], input[type=password], input[type=datetime-local], input[type=file] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

/* Set a style for all buttons */
button {
  background-color: #04AA6D;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}

button:hover {
  opacity: 0.8;
}

/* Extra styles for the cancel button */
.cancelbtn {
  width: 100%;
  padding: 10px 18px;
  background-color: #f44336;
}

.cancelbtn2 {
  width: auto;
  padding: 10px 18px;
  background-color: #f44336;
}

/* Center the image and position the close button */
.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
  position: relative;
}

img.avatar {
  width: 40%;
  border-radius: 50%;
}

.container {
  padding: 16px;
}

span.psw {
  float: right;
  padding-top: 16px;
}

/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  padding-top: 60px;
}

.gestao {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 70%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  padding-top: 20px;
  margin-left:20px;
  margin-top:150px;
}

.modal_2 {
  display: block; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 15% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
  border: 1px solid #888;
  width: 80%; /* Could be more or less, depending on screen size */
}

/* The Close Button (x) */
.close {
  position: absolute;
  right: 25px;
  top: 0;
  color: #000;
  font-size: 35px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: red;
  cursor: pointer;
}

/* Add Zoom Animation */
.animate {
  -webkit-animation: animatezoom 0.6s;
  animation: animatezoom 0.6s
}

@-webkit-keyframes animatezoom {
  from {-webkit-transform: scale(0)} 
  to {-webkit-transform: scale(1)}
}
  
@keyframes animatezoom {
  from {transform: scale(0)} 
  to {transform: scale(1)}
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
  span.psw {
     display: block;
     float: none;
  }
  .cancelbtn, .cancelbtn2 {
     width: 100%;
  }
}

table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
  height: 50%;
  border: 1px solid #ddd;
  overflow: auto;
}

th, td {
  text-align: left;
  padding: 8px;
}

tr:nth-child(even){background-color: #f2f2f2}

* {
  box-sizing: border-box;
}

#myInput {
  background-image: url('/css/searchicon.png');
  background-position: 10px 10px;
  background-repeat: no-repeat;
  width: 100%;
  font-size: 16px;
  padding: 12px 20px 12px 40px;
  border: 1px solid #ddd;
  margin-bottom: 12px;
}

/* #myTable tr.header, #myTable tr:hover { */
/*   background-color: #f1f1f1; */
/* } */

/* The contain */
.contain {
  display: block;
  position: relative;
  padding-left: 35px;
  margin-bottom: 12px;
  cursor: pointer;
  font-size: 18px;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

/* Hide the browser's default checkbox */
.contain input {
  position: absolute;
  opacity: 0;
  cursor: pointer;
  height: 0;
  width: 0;
}

/* Create a custom checkbox */
.checkmark {
  position: absolute;
  top: 0;
  left: 0;
  height: 20px;
  width: 20px;
  background-color: #eee;
}

/* On mouse-over, add a grey background color */
.contain:hover input ~ .checkmark {
  background-color: #ccc;
}

/* When the checkbox is checked, add a blue background */
.contain input:checked ~ .checkmark {
  background-color: #2196F3;
}

/* Create the checkmark/indicator (hidden when not checked) */
.checkmark:after {
  content: "";
  position: absolute;
  display: none;
}

/* Show the checkmark when checked */
.contain input:checked ~ .checkmark:after {
  display: block;
}

/* Style the checkmark/indicator */
.contain .checkmark:after {
  left: 5px;
  top: 5px;
  width: 4px;
  height: 5px;
  border: solid white;
  border-width: 0 3px 3px 0;
  -webkit-transform: rotate(45deg);
  -ms-transform: rotate(45deg);
  transform: rotate(45deg);
}

/* Center the image and position the close button */
.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
  position: relative;
}

img.avatar {
  width: 40%;
  border-radius: 50%;
}

</style>
</head>
<body class = "modal_2">
	<jsp:useBean id="utiliz" class="coding.model.Utilizador">
		<jsp:setProperty name="utiliz" property="*"/>
	</jsp:useBean>
	<jsp:useBean id="bike" class="coding.model.Bike">
		<jsp:setProperty name="bike" property="*"/>
	</jsp:useBean>
	<jsp:useBean id="ciclis" class="coding.model.Ciclista">
		<jsp:setProperty name="ciclis" property="*"/>
	</jsp:useBean>
	<jsp:useBean id="res" class="coding.model.Reserva">
		<jsp:setProperty name="res" property="*"/>
	</jsp:useBean>
	<jsp:useBean id="rec" class="coding.model.Recibo">
		<jsp:setProperty name="rec" property="*"/>
	</jsp:useBean>
	
	<jsp:useBean id="userCRUD" class="coding.controller.Utilizador_C">
	</jsp:useBean>
	<jsp:useBean id="bikeCRUD" class="coding.controller.Bike_C">
	</jsp:useBean>
	<jsp:useBean id="ciclisCRUD" class="coding.controller.Ciclista_C">
	</jsp:useBean>
	<jsp:useBean id="reservCRUD" class="coding.controller.Reserva_C">
	</jsp:useBean>
	<jsp:useBean id="recibCRUD" class="coding.controller.Recibo_C">
	</jsp:useBean>
	
	<jsp:useBean id="log" class="coding.controller.Login">
	</jsp:useBean>

<!-- 	MENU PRINCIPAL -->

 	<div class="imgcontainer">
      <img src="./images/logo_p.png" alt="Logo" class="avatar">
    </div>
	
	<ul>
	  <li><a onclick="document.getElementById('id02').style.display='block'" href="#bikes">Bikes</a></li>
	  <%if(log.readAdmin().equals("true")){ %>
	  	<li><a onclick="document.getElementById('id03').style.display='block'" href="#users">Utilizadores</a></li>
	  <%}%>
	  
	  <li><a onclick="document.getElementById('id04').style.display='block'" href="#reservas">Reservas</a></li>
	  
	  <%if(log.readAdmin().equals("true") || log.readAdmin().equals("false")){ %>
		  <li><a onclick="document.getElementById('id01').style.display='block'" href="#ciclistas">Ciclistas</a></li>
		  <li><a onclick="document.getElementById('id05').style.display='block'" href="#pagamentos">Pagamentos</a></li>
		  <li><a onclick="document.getElementById('id15').style.display='block'" href="#recibos">Recibos</a></li>
	  <%}%>
	  
	  <li><a onclick="document.getElementById('id06').style.display='block'" class="active" href="#logout">Logout</a></li>
	</ul>
	
<!-- 	GESTAO DE CICLISTAS	 -->
	<div id="id01" class="modal" style="max-width: 600px;">
	  <form class="modal-content animation" action="ciclista.jsp">
	    <div class="container">
	      <center><h1>CADASTRO DE CICLISTA</h1></center>
	      <label class="contain">
			  <input type="checkbox" id="checkedZero" onclick="displayer0()">Actualização/Remoção
			  <span class="checkmark"></span>
			</label>
		  
	      <p>Campos obrigatorios para cadastro (*)</p>
	      <hr>
	      <label for="ident" id="identificar00" style="display:none;"><b>* ID</b></label>
	      <input type="text" placeholder="Digite o ID" name="id_ciclista" id="identificador00" style="display:none;">
	      
	      <label for="ncompleto"><b>* Nome completo</b></label>
	      <input type="text" placeholder="Digite o nome completo" name="nome">
	      
	      <label for="contacto"><b>* Contacto</b></label>
	      <input type="text" placeholder="+258 8x xxx xxxx" name="contacto">
	      
	      <label for="morada"><b>* Morada</b></label>
	      <input type="text" placeholder="Cidade, Bairro, Qt., Nr. casa" name="morada">
	      
	      <label for="uname"><b>* Utilizador</b></label>
	      <input type="text" placeholder="nome.apelido" name="uname">
	
	      <label for="psw"><b>* Senha</b></label>
	      <input type="password" placeholder="Digite a senha" name="psw">
	      
	      <div class="clearfix">
	        <button type="submit" class="signupbtn" id="save00">Gravar</button>
	      </div>
	      <div class="clearfix">
	      	
	        <button type="submit" class="signupbtn" id="update00" formaction="ciclistaUP.jsp" style="display:none;">Actualizar</button>
	      	<button type="submit" id="cancel00" formaction="ciclistaRM.jsp" class="cancelbtn" style="display:none;">Remover</button>
	      	<div class="clearfix">
	      		<button type="button" onclick="document.getElementById('id13').style.display='block'; document.getElementById('id01').style.display='none'" class="signupbtn">Visualizar Ciclistas</button>
	  	 	</div>
	      </div>
	    </div>
	
	    <div class="container" style="background-color:#f1f1f1">
	      <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn2">Fechar</button>
	    </div>
	  </form>
	</div>
	
	<div id="id13" class="gestao animate" style="max-width: 1000px;background-color:white;">
		  <input type="text" id="myInput13" onkeyup="myFunction13()" placeholder="Procurar por nomes..." style="max-width: 500px;">
		  <table id="myTable13">
		    <tr>
		      <th>ID</th>
		      <th>Nome</th>
		      <th>Contacto</th>
		      <th>Morada</th>
		      <th>Utilizador</th>
		    </tr>
		    <% 
		    	ciclisCRUD.recuperarCiclistas();
		    	for(Ciclista ccl: ciclisCRUD.ciclis){
			%>
					<tr>
						<td><% out.print(ccl.getId_ciclista());%></td>
						<td><% out.print(ccl.getNome());%></td>
						<td><% out.print(ccl.getContacto());%></td>
						<td><% out.print(ccl.getMorada());%></td>
						<td><% out.print(ccl.getUname());%></td>
					</tr>
			<%
		    	}
		    %>
		  </table>
		  
		  <div class="container" style="background-color:#f1f1f1">
	      	<button type="button" onclick="document.getElementById('id13').style.display='none'; document.getElementById('id01').style.display='block'" class="cancelbtn2">Fechar Visualizacao</button>
	      </div>
	</div>

<!-- 	GESTAO DE RESERVAS	 -->
	<div id="id04" class="modal" style="max-width: 600px;">
	  
	  <form class="modal-content animate" action="reserva.jsp" method="post">
	
	   <div class="container">
	      <center><h1>REGISTRO DE RESERVA</h1></center>
	       <label class="contain">
			  <input type="checkbox" id="checkedFour" onclick="displayer4()">Actualização/Cancelamento
			  <span class="checkmark"></span>
			</label>
		  
	      <p>Campos obrigatorios para registro (*)</p>
	      <hr>
	      <label for="ident" id="identificar04" style="display:none;"><b>* ID</b></label>
	      <input type="text" placeholder="Digite o ID" name="id_reserva" id="identificador04" style="display:none;">
	      
	      <label for="bike"><b>* Bike</b></label>
	      <input type="text" placeholder="Digite o ID da bike" name="id_bike">
	     	
	     	<label for="id_ciclista"><b>* Ciclista</b></label>
      		<input type="text" placeholder="Digite o ID do ciclista" name="id_ciclista">

	      <label for="hora_saida"><b>* Data & Hora de Saida</b></label>
	      <input type="datetime-local" placeholder="Selecione a data" name="hora_saida">
	      
	      <div class="clearfix">
	        <button type="submit" class="signupbtn" id="save04">Gravar</button>
	      </div>
	      <div class="clearfix">
	      	<%if(log.readAdmin().equals("true") || log.readAdmin().equals("false")){ %>
	        	<button type="submit" class="signupbtn" id="update04" formaction="reservaUP.jsp" style="display:none;">Actualizar</button>
	      		<button type="submit" id="cancel04" formaction="reservaRM.jsp" class="cancelbtn" style="display:none;">Cancelar</button>
		      	<div class="clearfix">
		      		<button type="button" onclick="document.getElementById('id14').style.display='block'; document.getElementById('id04').style.display='none'" class="signupbtn">Visualizar Reservas</button>
		  	 	</div>
		  	 <%}%>
	      </div>
	    </div>
	
	    <div class="container" style="background-color:#f1f1f1">
	      <button type="button" onclick="document.getElementById('id04').style.display='none'" class="cancelbtn2">Fechar</button>
	    </div>
	  	 
	  </form>	  
	</div>
	
	<%if(log.readAdmin().equals("true") || log.readAdmin().equals("false")){ %>
		<div id="id14" class="gestao animate" style="max-width: 1000px;background-color:white;">
			  <input type="text" id="myInput14" onkeyup="myFunction14()" placeholder="Procurar por ciclistas..." style="max-width: 500px;">
			  <table id="myTable14">
			    <tr>
			      <th>ID</th>
			      <th>Bike</th>
			      <th>Ciclista</th>
			      <th>Data & Hora_Saida</th>
			    </tr>
			    <% 
			    	reservCRUD.recuperarReservas();
			    	for(Reserva rs: reservCRUD.reservs){
				%>
						<tr>
							<td><% out.print(rs.getId_reserva());%></td>
							<td><% out.print(rs.getId_bike());%></td>
							<td><% out.print(rs.getId_ciclista());%></td>
							<td><% out.print(rs.getHora_saida());%></td>
						</tr>
				<%
			    	}
			    %>
			  </table>
			  
			  <div class="container" style="background-color:#f1f1f1">
		      	<button type="button" onclick="document.getElementById('id14').style.display='none'; document.getElementById('id04').style.display='block'" class="cancelbtn2">Fechar Visualizacao</button>
		      </div>
		</div>
	<%}%>
	
<!-- 	GESTAO DE PAGAMENTOS -->
	<div id="id05" class="modal" style="max-width: 600px;">
	  
	  <form class="modal-content animate" action="pagamento.jsp" method="post">
	
	   <div class="container">
	      <center><h1>PAGAMENTO PELO USO</h1></center>
	       <label class="contain">
			  <input type="checkbox" id="checkedFive" onclick="displayer5()">Cancelamento
			  <span class="checkmark"></span>
			</label>
		  
	      <p>Campos obrigatorios (*)</p>
	      <hr>
	      <label for="ident" id="identificar05" style="display:block;"><b>* ID</b></label>
	      <input type="text" placeholder="Digite o ID" name="id_recibo" id="identificador05" style="display:block;">
	      
	      <label for="tempo"><b>* Tempo de permanência (Horas)</b></label>
	      <input type="text" placeholder="Digite o tempo de permanencia" id="tp" name="tempo_permanencia">
	     	
	     	<label for="valor"><b>* Custo</b></label>
      		<input type="text" placeholder="0.0" name="custo" id="val" readonly>
      		
   			<label for="hora_entrada"><b>* Data & Hora de Devolução</b></label>
	      	<input type="datetime-local" placeholder="Selecione a data" name="hora_entrada">
      		
      		<div class="clearfix">
	        	<button type="button" onclick="myFunction15()" class="signupbtn" id="calc05">Calcular</button>
	      	</div>

	     <div class="clearfix">
	        <button type="submit" class="signupbtn" id="save05">Pagar</button>
	      </div>
	      <div class="clearfix">
	      	<button type="submit" id="cancel05" formaction="pagamentoRM.jsp" class="cancelbtn" style="display:none;">Cancelar</button>
	      </div>
	    </div>
	
	    <div class="container" style="background-color:#f1f1f1">
	      <button type="button" onclick="document.getElementById('id05').style.display='none'" class="cancelbtn2">Fechar</button>
	    </div>
	  	 
	  </form>	  
	</div>
	
<!-- 	GESTAO DE BIKES	 -->
	<div id="id02" class="modal" style="max-width: 600px;">
	  
	  <form class="modal-content animate" action="BikeRegisterServlet" enctype="multipart/form-data" method="post">
	
	   <div class="container">
	      <center><h1>REGISTRO DE BIKE</h1></center>
	       <label class="contain">
			  <input type="checkbox" id="checkedTwo" onclick="displayer2()">Actualização/Remoção
			  <span class="checkmark"></span>
			</label>
		  
	      <p>Campos obrigatorios para registro (*)</p>
	      <hr>
	      <label for="ident" id="identificar02" style="display:none;"><b>* ID</b></label>
	      <input type="text" placeholder="Digite o ID" name="id_bike" id="identificador02" style="display:none;">
	      
	      <label for="marca"><b>* Marca</b></label>
	      <input type="text" placeholder="Digite a marca" name="marca">
	     	
	     	<label for="modelo"><b>* Modelo</b></label>
      		<input type="text" placeholder="Digite o modelo" name="modelo">

	      <label for="imagem"><b>* Imagem</b></label>
	      <input type="file" name="imagem">
	      
		    <label for="estado"><b>* Estado</b></label>
		    <input type="text" placeholder="Nova ou Usada ou Com mazelas" name="estado">
				
	      <label for="hora_entrada"><b>* Data & Hora de Entrada</b></label>
	      <input type="datetime-local" placeholder="Selecione a data" name="hora_entrada">
	     
	      <%if(log.readAdmin().equals("true") || log.readAdmin().equals("false")){ %>
	      <div class="clearfix">
	        <button type="submit" class="signupbtn" id="save02">Gravar</button>
	      </div>
	      <%}%>
	      <div class="clearfix">
	      	<%if(log.readAdmin().equals("true") || log.readAdmin().equals("false")){ %>
	        <button type="submit" class="signupbtn" id="update02" formaction="bikeUP.jsp" style="display:none;">Actualizar</button>
	      	<button type="submit" id="cancel02" formaction="bikeRM.jsp" class="cancelbtn" style="display:none;">Remover</button>
	      	<%}%>
	      	<div class="clearfix">
	      		<button type="button" onclick="document.getElementById('id12').style.display='block'; document.getElementById('id02').style.display='none'" class="signupbtn">Visualizar Bikes</button>
	  	 	</div>
	      </div>
	    </div>
	
	    <div class="container" style="background-color:#f1f1f1">
	      <button type="button" onclick="document.getElementById('id02').style.display='none'" class="cancelbtn2">Fechar</button>
	    </div>
	  	 
	  </form>	  
	</div>
	
	<div id="id12" class="gestao animate" style="max-width: 1000px;background-color:white;">
		  <input type="text" id="myInput12" onkeyup="myFunction12()" placeholder="Procurar por modelos..." style="max-width: 500px;">
		  <table id="myTable12">
		    <tr>
		      <th>ID</th>
		      <th>Marca</th>
		      <th>Modelo</th>
		      <th>Imagem</th>
		      <th>Estado</th>
		      <th>Data & Hora_Entrada</th>
		    </tr>
		    <% 
		    	bikeCRUD.recuperarBikes();
		    	for(Bike bk: bikeCRUD.bikes){
			%>
					<tr>
						<td><% out.print(bk.getId_bike());%></td>
						<td><% out.print(bk.getMarca());%></td>
						<td><% out.print(bk.getModelo());%></td>
						<td><img alt="bikeimage" src="./uploadedFiles/<% out.print(bk.getImagem());%>" style="width:50px; height:50px;"></td>
						<td><% out.print(bk.getEstado());%></td>
						<td><% out.print(bk.getHora_entrada());%></td>
					</tr>
			<%
		    	}
		    %>
		  </table>
		  
		  <div class="container" style="background-color:#f1f1f1">
	      	<button type="button" onclick="document.getElementById('id12').style.display='none'; document.getElementById('id02').style.display='block'" class="cancelbtn2">Fechar Visualizacao</button>
	      </div>
	</div>
	
	<div id="id15" class="gestao animate" style="max-width: 1000px;background-color:white;">
			  <input type="text" id="myInput15" onkeyup="myFunction151()" placeholder="Procurar por recibos..." style="max-width: 500px;">
			  <table id="myTable15">
			    <tr>
			      <th>ID</th>
			      <th>Bike</th>
			      <th>Ciclista</th>
			      <th>Data & Hora_Saida</th>
			      <th>Data & Hora de Devolução</th>
			      <th>Tempo de permanência</th>
			      <th>Custo</th>
			    </tr>
			    <% 
			    	recibCRUD.recuperarRecibos();
			    	for(Recibo rs: recibCRUD.recibos){
				%>
						<tr>
							<td><% out.print(rs.getId_recibo());%></td>
							<td><% out.print(rs.getId_bike());%></td>
							<td><% out.print(rs.getNome_ciclista());%></td>
							<td><% out.print(rs.getHora_saida());%></td>
							<td><% out.print(rs.getHora_entrada());%></td>
							<td><% out.print(rs.getTempo_permanencia());%></td>
							<td><% out.print(rs.getCusto());%></td>
						</tr>
				<%
			    	}
			    %>
			  </table>
			  
			  <div class="container" style="background-color:#f1f1f1">
		      	<button type="button" onclick="document.getElementById('id15').style.display='none';" class="cancelbtn2">Fechar Visualizacao</button>
		      </div>
		</div>
	
<!-- 	GESTAO DE UTILIZADORES	 -->
	<div id="id03" class="modal" style="max-width: 600px;">
	  
	  <form class="modal-content animate" action="user.jsp" method="post">
	
	   <div class="container">
	      <center><h1>CADASTRO DE UTILIZADOR</h1></center>
	       <label class="contain">
			  <input type="checkbox" id="checkedOne" onclick="displayer()">Actualização/Remoção
			  <span class="checkmark"></span>
			</label>
		  
	      <p>Campos obrigatorios para cadastro (*)</p>
	      <hr>
	      <label for="ident" id="identificar" style="display:none;"><b>* ID</b></label>
	      <input type="text" placeholder="Digite o ID" name="id_user" id="identificador" style="display:none;">
	      
	      <label for="ncompleto"><b>* Nome completo</b></label>
	      <input type="text" placeholder="Digite o nome completo" id="n" name="name_user">
	     	<label for="uname"><b>*Utilizador</b></label>
      		<input type="text" placeholder="nome.apelido" id="un" name="uname">

	      <label for="email"><b>* Email</b></label>
	      <input type="text" placeholder="exemplo@dominio" id="em" name="email">
	      
		    <label for="type_user"><b>* Tipo de Utilizador</b></label>
		    <input type="text" placeholder="Administrador ou Geral" id="tp" name="type_user">
				
	      <label for="psw"><b>* Senha</b></label>
	      <input type="password" placeholder="Digite a senha" id="sn" name="psw">
	      
	      <div class="clearfix">
	        <button type="submit" class="signupbtn" id="save">Gravar</button>
	      </div>
	      <div class="clearfix">
	      	
	        <button type="submit" class="signupbtn" id="update" formaction="userUP.jsp" style="display:none;">Actualizar</button>
	      	<button type="submit" id="cancel" formaction="userRM.jsp" class="cancelbtn" style="display:none;">Remover</button>
	      	<div class="clearfix">
	      		<button type="button" onclick="document.getElementById('id10').style.display='block'; document.getElementById('id03').style.display='none'" class="signupbtn">Visualizar Utilizadores</button>
	  	 	</div>
	      </div>
	    </div>
	
	    <div class="container" style="background-color:#f1f1f1">
	      <button type="button" onclick="document.getElementById('id03').style.display='none'" class="cancelbtn2">Fechar</button>
	    </div>
	  	 
	  </form>	  
	</div>
	
	<div id="id10" class="gestao animate" style="max-width: 1000px;background-color:white;">
		  <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Procurar por nomes..." style="max-width: 500px;">
		  <table id="myTable">
		    <tr>
		      <th>ID</th>
		      <th>Nome completo</th>
		      <th>Email</th>
		      <th>Tipo de utilizador</th>
		      <th>Utilizador</th>
		    </tr>
		    <% 
		    	userCRUD.recuperarUsers();
		    	for(Utilizador usr: userCRUD.users){
			%>
					<tr>
						<td><% out.print(usr.getId_user());%></td>
						<td><% out.print(usr.getName_user());%></td>
						<td><% out.print(usr.getEmail());%></td>
						<td><% out.print(usr.getType_user());%></td>
						<td><% out.print(usr.getUname());%></td>
					</tr>
			<%
		    	}
		    %>
		  </table>
		  
		  <div class="container" style="background-color:#f1f1f1">
	      	<button type="button" onclick="document.getElementById('id10').style.display='none'; document.getElementById('id03').style.display='block'" class="cancelbtn2">Fechar Visualizacao</button>
	      </div>
	</div>
	
<!-- 	LOGOUT	 -->
	<div id="id06" class="modal" style="max-width: 400px;">
	  
	  <form class="modal-content animate" action="principal.jsp" method="post">
	
	    <div class="container">
	      <h2>De certeza que deseja sair?</h2>
	        
	      <button type="submit">Sim</button>
	     
	    </div>
	
	    <div class="container" style="background-color:#f1f1f1">
	      <button type="button" onclick="document.getElementById('id06').style.display='none'" class="cancelbtn">Não</button>
	    </div>
	  </form> 
	</div>
	
<!-- 	ALGUNS COMPORTAMENTOS DA PAGINA -->
	<script>
	//CICLISTAS	
		function displayer0(){
			var chk = document.getElementById('checkedZero');
			var id_label = document.getElementById('identificar00');
			var id_text = document.getElementById('identificador00');
			var save = document.getElementById('save00');
			var update = document.getElementById('update00');
			var cancel = document.getElementById('cancel00');
			
			if(chk.checked == true){
				id_label.style.display = "block";
				id_text.style.display = "block";
				update.style.display = "block";
				save.style.display = "none";
				cancel.style.display = "block";
			}else{
				id_label.style.display = "none";
				id_text.style.display = "none";
				update.style.display = "none";
				cancel.style.display = "none";
				save.style.display = "block";			
			}
		}
		
		function myFunction13() {
		  var input, filter, table, tr, td, i, txtValue;
		  input = document.getElementById("myInput13");
		  filter = input.value.toUpperCase();
		  table = document.getElementById("myTable13");
		  tr = table.getElementsByTagName("tr");
		  for (i = 0; i < tr.length; i++) {
		    td = tr[i].getElementsByTagName("td")[1];
		    if (td) {
		      txtValue = td.textContent || td.innerText;
		      if (txtValue.toUpperCase().indexOf(filter) > -1) {
		        tr[i].style.display = "";
		      } else {
		        tr[i].style.display = "none";
		      }
		    }       
		  }
		}
	
	//RESERVAS
		function displayer4(){
			var chk = document.getElementById('checkedFour');
			var id_label = document.getElementById('identificar04');
			var id_text = document.getElementById('identificador04');
			var save = document.getElementById('save04');
			var update = document.getElementById('update04');
			var cancel = document.getElementById('cancel04');
			
			if(chk.checked == true){
				id_label.style.display = "block";
				id_text.style.display = "block";
				update.style.display = "block";
				save.style.display = "none";
				cancel.style.display = "block";
			}else{
				id_label.style.display = "none";
				id_text.style.display = "none";
				update.style.display = "none";
				cancel.style.display = "none";
				save.style.display = "block";			
			}
		}
		
		function myFunction14() {
		  var input, filter, table, tr, td, i, txtValue;
		  input = document.getElementById("myInput14");
		  filter = input.value.toUpperCase();
		  table = document.getElementById("myTable14");
		  tr = table.getElementsByTagName("tr");
		  for (i = 0; i < tr.length; i++) {
		    td = tr[i].getElementsByTagName("td")[2];
		    if (td) {
		      txtValue = td.textContent || td.innerText;
		      if (txtValue.toUpperCase().indexOf(filter) > -1) {
		        tr[i].style.display = "";
		      } else {
		        tr[i].style.display = "none";
		      }
		    }       
		  }
		}
		
		//PAGAMENTOS	
		function displayer5(){
			var chk = document.getElementById('checkedFive');
			var save = document.getElementById('save05');
			var cancel = document.getElementById('cancel05');
			var calc = document.getElementById('calc05');
			
			if(chk.checked == true){
				save.style.display = "none";
				cancel.style.display = "block";
				calc.style.display = "none";
			}else{
				cancel.style.display = "none";
				save.style.display = "block";	
				calc.style.display = "block";		
			}
		}
		
		function myFunction15() {
		  var tempo = document.getElementById('tp').value;
		  document.getElementById('val').value = 150*tempo;
		}
		
		function myFunction151() {
			  var input, filter, table, tr, td, i, txtValue;
			  input = document.getElementById("myInput15");
			  filter = input.value.toUpperCase();
			  table = document.getElementById("myTable15");
			  tr = table.getElementsByTagName("tr");
			  for (i = 0; i < tr.length; i++) {
			    td = tr[i].getElementsByTagName("td")[0];
			    if (td) {
			      txtValue = td.textContent || td.innerText;
			      if (txtValue.toUpperCase().indexOf(filter) > -1) {
			        tr[i].style.display = "";
			      } else {
			        tr[i].style.display = "none";
			      }
			    }       
			  }
			}
	
		//--------
		function displayer(){
			var chk = document.getElementById('checkedOne');
			var id_label = document.getElementById('identificar');
			var id_text = document.getElementById('identificador');
			var save = document.getElementById('save');
			var update = document.getElementById('update');
			var cancel = document.getElementById('cancel');
			
			if(chk.checked == true){
				id_label.style.display = "block";
				id_text.style.display = "block";
				update.style.display = "block";
				save.style.display = "none";
				cancel.style.display = "block";
			}else{
				id_label.style.display = "none";
				id_text.style.display = "none";
				update.style.display = "none";
				cancel.style.display = "none";
				save.style.display = "block";			
			}
		}
		
		function displayer2(){
			var chk = document.getElementById('checkedTwo');
			var id_label = document.getElementById('identificar02');
			var id_text = document.getElementById('identificador02');
			var save = document.getElementById('save02');
			var update = document.getElementById('update02');
			var cancel = document.getElementById('cancel02');
			
			if(chk.checked == true){
				id_label.style.display = "block";
				id_text.style.display = "block";
				update.style.display = "block";
				save.style.display = "none";
				cancel.style.display = "block";
			}else{
				id_label.style.display = "none";
				id_text.style.display = "none";
				update.style.display = "none";
				cancel.style.display = "none";
				save.style.display = "block";			
			}
		}
		
		function myFunction() {
		  var input, filter, table, tr, td, i, txtValue;
		  input = document.getElementById("myInput");
		  filter = input.value.toUpperCase();
		  table = document.getElementById("myTable");
		  tr = table.getElementsByTagName("tr");
		  for (i = 0; i < tr.length; i++) {
		    td = tr[i].getElementsByTagName("td")[1];
		    if (td) {
		      txtValue = td.textContent || td.innerText;
		      if (txtValue.toUpperCase().indexOf(filter) > -1) {
		        tr[i].style.display = "";
		      } else {
		        tr[i].style.display = "none";
		      }
		    }       
		  }
		}
		
		function myFunction12() {
			  var input, filter, table, tr, td, i, txtValue;
			  input = document.getElementById("myInput12");
			  filter = input.value.toUpperCase();
			  table = document.getElementById("myTable12");
			  tr = table.getElementsByTagName("tr");
			  for (i = 0; i < tr.length; i++) {
			    td = tr[i].getElementsByTagName("td")[2];
			    if (td) {
			      txtValue = td.textContent || td.innerText;
			      if (txtValue.toUpperCase().indexOf(filter) > -1) {
			        tr[i].style.display = "";
			      } else {
			        tr[i].style.display = "none";
			      }
			    }       
			  }
			}
	</script>

</body>
</html>