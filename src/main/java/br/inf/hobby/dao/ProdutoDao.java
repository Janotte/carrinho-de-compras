package br.inf.hobby.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.inf.hobby.model.Produto;

public class ProdutoDao {

	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ProdutoDao(Connection con) {
		this.con = con;
	}
	
	public List<Produto> obterTodosProdutos() {
		List<Produto> produtos = new ArrayList<Produto>();
		
		try {
			query = "select * from produtos";
			pst = this.con.prepareStatement(query);
			rs =pst.executeQuery();
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setNome(rs.getString("nome"));
				produto.setCategoria(rs.getString("categoria"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setImagem(rs.getString("imagem"));
				
				produtos.add(produto);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return produtos;
	}
}
