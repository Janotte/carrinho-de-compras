<%@page import="br.inf.hobby.connection.DbConnection"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <title>Home</title>
    <%@include file="includes/head.jsp" %>
  </head>
  <body>
  	<%@include file="includes/navbar.jsp" %>
    <% out.print(DbConnection.getConnection()); %>
    <%@include file="includes/footer.jsp" %>
  </body>
</html>s