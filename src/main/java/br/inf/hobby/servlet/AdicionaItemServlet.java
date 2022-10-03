package br.inf.hobby.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.inf.hobby.model.Item;

@WebServlet("/adicionar-item")
public class AdicionaItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		try (PrintWriter out = response.getWriter()) {
			ArrayList<Item> itemLista = new ArrayList<>();

			int id = Integer.parseInt(request.getParameter("id"));
			Item item = new Item();
			item.setId(id);
			item.setQuantidade(1);

			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			ArrayList<Item> lista_item = (ArrayList<Item>) session.getAttribute("lista-item");

			if (lista_item == null) {
				itemLista.add(item);
				session.setAttribute("lista-item", itemLista);
				response.sendRedirect("index.jsp");
			} else {
				itemLista = lista_item;
				boolean existeLista = false;
				
				for(Item i:lista_item) {
					
					if (i.getId() == id) {
						existeLista = true;
						out.println("<h3 style='color:crimson; text-align:center'>Este produto já foi adicionado!<a href='carrinho.jsp'>Ir para o Carrinho</a></h3>");
					}
				}
				if (!existeLista) {
					itemLista.add(item);
					response.sendRedirect("index.jsp");
				}
			}
		}
	}
}
