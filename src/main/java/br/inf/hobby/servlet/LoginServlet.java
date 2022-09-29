package br.inf.hobby.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.inf.hobby.connection.DbConnection;
import br.inf.hobby.dao.UsuarioDao;
import br.inf.hobby.model.Usuario;

@WebServlet("/usuario-login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html.charset=UTF-8");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		try(PrintWriter out = response.getWriter()) {
			UsuarioDao usuarioDao = new UsuarioDao(DbConnection.getConnection());
			Usuario usuario = usuarioDao.usuarioLogin(email, senha);
			if (usuario != null) {
				request.getSession().setAttribute("auth", usuario);
				response.sendRedirect("index.jsp");
			} else {
				out.print("Falha no Login!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
	}
}
