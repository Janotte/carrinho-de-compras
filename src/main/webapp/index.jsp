<%@page import="java.util.List"%>
<%@page import="br.inf.hobby.connection.DbConnection"%>
<%@page import="br.inf.hobby.model.*"%>
<%@page import="br.inf.hobby.dao.ProdutoDao"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<% 
	Usuario auth = (Usuario) request.getSession().getAttribute("auth");
	if(auth != null) {
		request.setAttribute("auth", auth);
	}
	
	ProdutoDao produtoDao = new ProdutoDao(DbConnection.getConnection());
	List<Produto> produtos = produtoDao.obterTodosProdutos();
%>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <title>Home</title>
    <%@include file="includes/head.jsp" %>
  </head>
  <body>
  	<%@include file="includes/navbar.jsp" %>
  	
  	<div class="container">
  		<div class="card-header my-3">
  			<h1>Catálogo de Produtos</h1>
  		</div>
  		<div class="row">
  		<%
  			if(!produtos.isEmpty()) {
  				for(Produto p : produtos) {%>
  					<div class="col-md-3 my-3">
  	  				<div class="card w-100" style="width: 18rem;">
  				  		<img src="<%=p.getImagem()%>" class="card-img-top" alt="...">
  						<div class="card-body">
  							<h5 class="card-title"><%=p.getNome()%></h5>
  							<h6 class="preco">Preço: <%=p.getPreco()%></h6>
  							<h6 class="categoria">Categoria: <%=p.getCategoria()%></h6>
  							<div class="mt-3 d-flex justify-content-between">
  								<a href="#" class="btn btn-primary">Adicionar</a>
  								<a href="#" class="btn btn-primary">Comprar agora</a>
  							</div>
  							
  						</div>
  					</div>
  	  			</div>
  				<%}
  			}
  		%>
  			
		  	
  		</div>
  	</div>
    <%@include file="includes/footer.jsp" %>
  </body>
</html>