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

@WebServlet("/compre-agora")
public class CompreAgoraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {
			
			SimpleDateFormat fomatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
					
			Usuario auth = (Usuario) request.getSession().getAttribute("auth");
			
			if ( auth != null) {
				
				String produtoId = request.getParameter("id");
				int produtoQuantidade = Integer.parseInt(request.getParameter("quantidade"));
				
				if (produtoQuantidade <= 0) {
					produtoQuantidade = 1;
				}
				
				Pedido pedidoModel = new Pedido();
				pedidoModel.setUsuarioId(auth.getId());
				pedidoModel.setProdutoId(Integer.parseInt(produtoId));
				pedidoModel.setQuantidade(produtoQuantidade);
				pedidoModel.setData(fomatter.format(date));
				
				PedidoDao pedidoDao = new PedidoDao(DbConnection.getConnection());
				boolean result = pedidoDao.inserirPedido(pedidoModel);
				
				if (result) {
					ArrayList<Item> lista_item = (ArrayList<Item>) request.getSession().getAttribute("lista-item");
					
					if (lista_item != null) {
						for (Item i : lista_item) {
							if (i.getId() == Integer.parseInt(produtoId)) {
								lista_item.remove(lista_item.indexOf(i));
								break;
							}
						}
					}
					response.sendRedirect("pedidos.jsp");
				} else {
					out.print("Falho no Pedido");
				}
				
			} else {
				response.sendRedirect("login.jsp");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
