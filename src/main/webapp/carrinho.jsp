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
  		<div class="d-flex py-3">
  			<h3>Valor Total: R$ 999,00</h3>
  			<a class="mx-3 btn btn-primary" href="#">Check Out</a>
  		</div>
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
				<tr>
					<td>Sapato Masculino</td>
					<td>Sapatos</td>
					<td>R$ 199.00</td>
					<td>
						<form action="" method="post" class="form-inline">
							<input type="hidden" name="id" value="1" class="form-input">
							<div class="form-group d-flex justify-content-start">
								<a class="btn btn-increase" href=""><i class="bi bi-plus-square"></i></a>
								<input type="text" name="quantidade" class="form-control" value="1" readonly>
								<a class="btn btn-decrease" href=""><i class="bi bi-dash-square"></i></a>
							</div>
						</form>
					</td>
					<td>
						<a class="btn btn-sm btn-danger btn-remove" href="">Excluir</a>
					</td> 						
				</tr>
			</tbody>
		</table>
  	</div>
    <%@include file="includes/footer.jsp" %>
  </body>
</html>