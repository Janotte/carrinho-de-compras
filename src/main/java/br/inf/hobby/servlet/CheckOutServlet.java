package br.inf.hobby.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.inf.hobby.connection.DbConnection;
import br.inf.hobby.dao.PedidoDao;
import br.inf.hobby.model.Item;
import br.inf.hobby.model.Pedido;
import br.inf.hobby.model.Usuario;

@WebServlet("/check-out")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()){
			
			SimpleDateFormat fomatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			
			//Retorna todos os items
			@SuppressWarnings("unchecked")
			ArrayList<Item> lista_item = (ArrayList<Item>) request.getSession().getAttribute("lista-item");
			// Atenticação do usuário
			Usuario auth = (Usuario) request.getSession().getAttribute("auth");
			
			if (lista_item != null && auth != null) {
				
				for (Item i : lista_item) {
					Pedido pedido = new Pedido();
					pedido.setUsuarioId(auth.getId());
					pedido.setProdutoId(i.getId());
					pedido.setQuantidade(i.getQuantidade());
					pedido.setData(fomatter.format(date));
					
					PedidoDao pedidoDao = new PedidoDao(DbConnection.getConnection()); 
					boolean result = pedidoDao.inserirPedido(pedido);
					if (!result) break;
				}
				
				lista_item.clear();
				response.sendRedirect("pedidos.jsp");
			} else {
				if (auth == null) response.sendRedirect("login.jsp");
				response.sendRedirect("index.jsp");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
