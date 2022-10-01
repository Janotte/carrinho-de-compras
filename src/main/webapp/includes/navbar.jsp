<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <div class="container">
	  	<a class="navbar-brand" href="index.jsp">Carrinho de Compras</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" 
	    	data-bs-target="#navbarSupportedContent" 
	    	aria-controls="navbarSupportedContent" aria-expanded="false" 
	    	aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	    	<ul class="navbar-nav me-auto mb-2 mb-lg-0"></ul>
	       	<ul class="nav navbar-nav navbar-right">
		    	<li class="nav-item">
		          	<a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
		        </li>
		        <li class="nav-item">
		          	<a class="nav-link" href="carrinho.jsp">
		          		Carrinho
		          		<span class="badge bg-info">${lista_item.size()}</span>
		          	</a>
		        </li>
		        <%
		        if(auth != null){%>
			        <li class="nav-item">
			          	<a class="nav-link" href="pedidos.jsp">Pedidos</a>
			        </li>
		        	<li>
						<a class="nav-link" href="logout">
							<span id="username">${usuario.nome}</span>
		              		<span> Logout <i class="bi bi-box-arrow-right"></i></span>
		           		</a>
	           		</li>
		        <%}else{%>
		        	<li>
					<a class="nav-link" href="login.jsp">Login <i class="bi bi-box-arrow-in-left"></i></a>
				</li>
		        <%}%>   
	       	</ul>
	    </div>
	  </div>
	</nav>