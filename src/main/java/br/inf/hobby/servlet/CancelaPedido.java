package br.inf.hobby.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.inf.hobby.connection.DbConnection;
import br.inf.hobby.dao.PedidoDao;

@WebServlet("/cancela-pedido")
public class CancelaPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()) {
			String id = request.getParameter("id");
			
			if(id != null) {
				PedidoDao pedidoDao = new PedidoDao(DbConnection.getConnection());
				pedidoDao.cancelaPedido(Integer.parseInt(id));
			}
			response.sendRedirect("pedidos.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
