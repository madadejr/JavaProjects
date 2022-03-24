<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Properties" %>
<%@ page import="javax.mail.*" %>
<%@ page import="javax.mail.internet.*" %>
<%@ page import="java.io.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Envio de Emails</title>
</head>
<body>
	<jsp:useBean id="LoggerD" class="coding.beans.UsersData">
	</jsp:useBean>
	<%
 		final String to = request.getParameter("para");
		final String subject = request.getParameter("titulo");
 		final String msgText = request.getParameter("mensagem");
 		final String cc = request.getParameter("cc");
 		
 		final String from = request.getParameter("de");
 		
 		String host = "smtp.gmail.com";
	
	   	Properties properties = System.getProperties();
	
	 	properties.setProperty("mail.smtp.host", host);
	
	   	Session mailSession = Session.getDefaultInstance(properties);
	    
		if(!LoggerD.valida(from)){
			%>
	      		<script>
	      			alert("EMISSOR INVALIDO!");
					window.location = "send_email.html"
				</script>
	   		<%
 		}else{
	
		   	try{
		     
		    	MimeMessage message = new MimeMessage(mailSession);
		      	
		    	message.setFrom(new InternetAddress(from));
		       	message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				if(cc != null || !cc.equals("")){
		       		message.setRecipient(Message.RecipientType.CC, new InternetAddress(cc));
				}
		      	message.setSubject(subject);
		       	message.setText(msgText);
		      	Transport.send(message);      
		      
		    %>  
		      	<script>
					alert("Enviado com Sucesso!");
					window.location = "send_email.html"
				</script>
		    <%
		    	System.out.println("Message sent.");
		    }catch (MessagingException e){ 
		      	System.out.println("Erro de envio, causa: " + e);
		    %>
		      	<script>
					window.location = "send_email.html"
				</script>
		   	<%}
 		}
	%>
</body>
</html>