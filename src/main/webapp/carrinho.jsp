<%@page import="br.inf.hobby.connection.DbConnection"%>
<%@page import="br.inf.hobby.model.*"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<% 
	Usuario auth = (Usuario) request.getSession().getAttribute("auth");
	if(auth != null) {
		request.setAttribute("auth", auth);
	}
%>

<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <title>Carrinho</title>
    <%@include file="includes/head.jsp" %>
  </head>
  <body>
  	<%@include file="includes/navbar.jsp" %>
  	<div class="container">
  		<h1>Carrinho</h1>
  	</div>
    <%@include file="includes/footer.jsp" %>
  </body>
</html>