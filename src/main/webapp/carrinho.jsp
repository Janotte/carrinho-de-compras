<%@page import="br.inf.hobby.connection.DbConnection"%>
<%@page import="br.inf.hobby.dao.ProdutoDao"%>
<%@page import="br.inf.hobby.model.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	DecimalFormat df = new DecimalFormat("#.##");
	request.setAttribute("df", df);
	Usuario auth = (Usuario) request.getSession().getAttribute("auth");
	if(auth != null) {
		request.setAttribute("auth", auth);
	}
	ArrayList<Item> lista_item = (ArrayList<Item>) session.getAttribute("lista-item");
	List<Item> listaItem = null;
	if (lista_item != null) {
		ProdutoDao produtoDao = new ProdutoDao(DbConnection.getConnection());
		listaItem = produtoDao.obterLista(lista_item);
		double total = produtoDao.obterTotalCarrinho(lista_item);
		request.setAttribute("lista_item", lista_item);
		request.setAttribute("total", total);
	}
%>

<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <%@include file="/includes/head.jsp" %>
    <title>Carrinho</title>
  </head>
  <body>
  	<%@include file="/includes/navbar.jsp" %>
  	<div class="container">
  		<h1>Carrinho</h1>
  		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Nome</th>
					<th scope="col">Categoria</th>
					<th scope="col">Preço</th>
					<th scope="col">Comprar</th>
					<th scope="col">Cancelar</th>
				</tr>
			</thead>
			<tbody>
				<%
				if(lista_item != null) {
					for (Item i : listaItem) {%>
						<tr>
							<td><%= i.getNome()%></td>
							<td><%= i.getCategoria()%></td>
							<td><%= df.format(i.getPreco())%></td>
							<td>
								<form action="" method="post" class="form-inline">
									<input type="hidden" name="id" value="<%= i.getId()%>" class="form-input">
									<div class="form-group d-flex justify-content-start">
										<a class="btn btn-increase" href="quantidade-inc-dec?action=dec&id=<%= i.getId()%>"><i class="bi bi-dash-square"></i></a>
										<input type="text" name="quantidade" class="form-control" value=<%= i.getQuantidade()%> readonly>
										<a class="btn btn-decrease" href="quantidade-inc-dec?action=inc&id=<%= i.getId()%>"><i class="bi bi-plus-square"></i></a>
									</div>
								</form>
							</td>
							<td>
								<a class="btn btn-sm btn-danger" href="remove-item?id=<%= i.getId()%>">Excluir</a>
							</td> 						
						</tr>
					<%}
				}
				%>
			</tbody>
		</table>
		<div class="d-flex justify-content-end py-3">
  			<a class="mx-3 btn btn-primary" href="#">Check Out</a>
  			<h3>Valor Total: R$ ${(total>0)?df.format(total):0}</h3>
  		</div>
  	</div>
    <%@include file="/includes/footer.jsp" %>
  </body>
</html>