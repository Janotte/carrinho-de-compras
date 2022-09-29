package br.inf.hobby.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.inf.hobby.model.Usuario;

public class UsuarioDao {
	
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public UsuarioDao(Connection con) {
		this.con = con;
	}
	
	public Usuario usuarioLogin(String email, String senha) {
		Usuario usuario = null;
		try {
			query = "select * from usuarios where email=? and senha=?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, senha);
			rs = pst.executeQuery();
			if(rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		
		return usuario;
	}
	
}
