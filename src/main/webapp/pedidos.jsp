<%@page import="br.inf.hobby.connection.DbConnection"%>
<%@page import="br.inf.hobby.model.*"%>
<%@page import="java.util.*"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<% 
	Usuario auth = (Usuario) request.getSession().getAttribute("auth");
	if(auth != null) {
		request.setAttribute("auth", auth);
	}
	ArrayList<Item> lista_item = (ArrayList<Item>) session.getAttribute("lista-item");
	if (lista_item != null) {
		request.setAttribute("lista_item", lista_item);
	}
%>

<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <title>Pedidos</title>
    <%@include file="includes/head.jsp" %>
  </head>
  <body>
  	<%@include file="includes/navbar.jsp" %>
  	<div class="container">
  		<h1>Pedidos</h1>
  	</div>
    <%@include file="includes/footer.jsp" %>
  </body>
</html>