package br.inf.hobby.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.inf.hobby.model.Item;

@WebServlet("/remove-item")
public class RemoveItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()) {
			String id = request.getParameter("id");
			if ( id != null) {
				ArrayList<Item> lista_item = (ArrayList<Item>) request.getSession().getAttribute("lista-item");
				
				if (lista_item != null) {
					for (Item i : lista_item) {
						if (i.getId() == Integer.parseInt(id)) {
							lista_item.remove(lista_item.indexOf(i));
							break;
						}
					}
				}
				response.sendRedirect("carrinho.jsp");
			} else {
				response.sendRedirect("carrinho.jsp");
			}
		} 
	}
}
