<%@page import="br.inf.hobby.connection.DbConnection"%>
<%@page import="br.inf.hobby.dao.*"%>
<%@page import="br.inf.hobby.model.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
DecimalFormat df = new DecimalFormat("#.00");
request.setAttribute("df", df);
List<Pedido> pedidos = null;
Usuario auth = (Usuario) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
	pedidos = new PedidoDao(DbConnection.getConnection()).pedidosUsuario(auth.getId());
} else {
	response.sendRedirect("login.jsp");
}
ArrayList<Item> lista_item = (ArrayList<Item>) session.getAttribute("lista-item");
List<Item> listaItem = null;
if (lista_item != null) {
	request.setAttribute("lista_item", lista_item);
}
%>

<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <%@include file="/includes/head.jsp" %>
    <title>Pedidos</title>
  </head>
<body>
  	<%@include file="/includes/navbar.jsp" %>
  	<div class="container">
  		<div class="card-header my-3">
  			<h1>Pedidos</h1>
  		</div>
  		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Data</th>
					<th scope="col">Nome</th>
					<th scope="col">Categoria</th>
					<th scope="col">Quantidade</th>
					<th scope="col">Preço</th>
					<th scope="col">Cancelar</th>
				</tr>
			</thead>
			<tbody>
				<%
				if(pedidos != null) {
					for(Pedido p : pedidos) {%>
						<tr>
							<td><%= p.getData() %></td>
							<td><%= p.getNome() %></td>
							<td><%= p.getCategoria() %></td>
							<td><%= p.getQuantidade() %></td>
							<td><%= df.format(p.getPreco()) %></td>
							<td><a class="btn btn-sm btn-danger" href="cancela-pedido?id=<%= p.getId()%>">Calcelar</a></td>		
						</tr>		
					<%}
				}%>
			</tbody>
		</table>
  	</div>
    <%@include file="/includes/footer.jsp" %>
  </body>
</html>