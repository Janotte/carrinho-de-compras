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

@WebServlet("/quantidade-inc-dec")
public class QuantidadeIncrementaDecrementa extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		
		try(PrintWriter out = response.getWriter()) {
			String acao = request.getParameter("action");
			int id = Integer.parseInt(request.getParameter("id"));
			
			ArrayList<Item> lista_item = (ArrayList<Item>) request.getSession().getAttribute("lista-item");
			
			if (acao != null && id >= 1) {
				if (acao.equals("inc")) {
					for (Item i : lista_item) {
						if (i.getId() == id) {
							int quantidade = i.getQuantidade();
							quantidade++;
							i.setQuantidade(quantidade);
							response.sendRedirect("carrinho.jsp");
						}
					}
				}
				if (acao.equals("dec")) {
					for (Item i : lista_item) {
						if (i.getId() == id && i.getQuantidade() > 1) {
							int quantidade = i.getQuantidade();
							quantidade--;
							i.setQuantidade(quantidade);
							break;
						}
					}
					response.sendRedirect("carrinho.jsp");
				} 
			} else {
				response.sendRedirect("carrinho.jsp");
			}
		}
	}

}
