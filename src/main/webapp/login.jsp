<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <title>Login - Carrinhos de Compras</title>
    <%@include file="includes/head.jsp" %>
  </head>
  <body>
    <div class="container">
    	<div class="card w-50 mx-auto my-5">
    		<div class="card-header text-center">Login</div>
    		<div class="card-body">
    			<form action="usuario-login" method="post">
    				<div class="from-group">
    					<label>Endereço de E-mail</label>
    					<input type="email" class="form-control" name="email" placeholder="Informe seu E-mail" required="required">
    				</div>
    				<div class="from-group">
    					<label>Senha</label>
    					<input type="password" class="form-control" name="senha" placeholder="Informe sua senha" required="required">
    				</div>
    				<div class="text-center my-3">
    					<button type="submit" class="btn btn-primary">Entrar</button>
    				</div>
    			</form>
    		</div>
    	</div>
    </div>
    
    <%@include file="includes/footer.jsp" %>
  </body>
</html>