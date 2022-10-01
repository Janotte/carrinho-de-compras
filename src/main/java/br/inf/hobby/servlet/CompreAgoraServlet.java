package br.inf.hobby.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
				
				Pedido pedido = new Pedido();
				pedido.setId(Integer.parseInt(produtoId));
				pedido.setUsuarioId(auth.getId());
				pedido.setQuantidade(produtoQuantidade);
				pedido.setData(fomatter.format(date));
				
			} else {
				response.sendRedirect("login.jsp");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
