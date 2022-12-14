<%@page import="br.inf.hobby.connection.DbConnection"%>
<%@page import="br.inf.hobby.dao.ProdutoDao"%>
<%@page import="br.inf.hobby.model.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
DecimalFormat df = new DecimalFormat("#.00");
request.setAttribute("df", df);
Usuario auth = (Usuario) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}

ProdutoDao produtoDao = new ProdutoDao(DbConnection.getConnection());
List<Produto> produtos = produtoDao.obterTodosProdutos();

ArrayList<Item> lista_item = (ArrayList<Item>) session.getAttribute("lista-item");
if (lista_item != null) {
	request.setAttribute("lista_item", lista_item);
}
%>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
  	<%@include file="/includes/head.jsp" %>
    <title>Home</title>
  </head>
  <body>
  	<%@include file="/includes/navbar.jsp" %>
  	
  	<div class="container">
  		<div class="card-header my-3">
  			<h1>Cat?logo de Produtos</h1>
  		</div>
  		<div class="row">
  		<%
  			if(!produtos.isEmpty()) {
  				for(Produto p : produtos) {%>
  					<div class="col-md-6 col-lg-4 my-3">
  	  				<div class="card w-100" style="width: 18rem;">
  				  		<img src="<%=p.getImagem()%>" class="card-img-top" alt="...">
  						<div class="card-body">
  							<h5 class="card-title"><%= p.getNome()%></h5>
  							<h6 class="preco">Pre?o: R$<%= df.format(p.getPreco())%></h6>
  							<h6 class="categoria">Categoria: <%= p.getCategoria()%></h6>
  							<div class="mt-3 d-flex justify-content-between">
  								<a href="adicionar-item?id=<%= p.getId() %>" class="btn btn-dark">Adicionar</a>
  								<a href="compre-agora?quantidade=1&id=<%= p.getId()%>" class="btn btn-primary">Comprar agora</a>
  							</div>
  							
  						</div>
  					</div>
  	  			</div>
  				<%}
  			}
  		%>	
  		</div>
  	</div>
    <%@include file="/includes/footer.jsp" %>
  </body>
</html>