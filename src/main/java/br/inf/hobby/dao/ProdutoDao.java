package br.inf.hobby.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.inf.hobby.model.Item;
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
	
	public List<Item> obterLista(ArrayList<Item> listaItem) {
		List<Item> produtos = new ArrayList<Item>();
		
		try {
			if (listaItem.size() > 0) {
				for(Item item : listaItem) {
					query = "select * from produtos where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					
					while(rs.next()) {
						Item row = new Item();
						row.setId(rs.getInt("id"));
						row.setNome(rs.getString("nome"));
						row.setCategoria(rs.getString("categoria"));
						row.setPreco(rs.getDouble("preco") * item.getQuantidade());
						row.setQuantidade(item.getQuantidade());
						row.setImagem(rs.getString("imagem"));
						
						
						produtos.add(row);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return produtos;
	}
	
	public double obterTotalCarrinho(ArrayList<Item> listaItem) {
		double total = 0;

		try {
			if (listaItem.size() > 0) {
				 for (Item item : listaItem) {
					 query = "select preco from produtos where id=?";
					 pst = this.con.prepareStatement(query);
					 pst.setInt(1, item.getId());
					 rs = pst.executeQuery();
					 
					 while (rs.next() ) {
						 total += item.getQuantidade() * rs.getDouble("preco");
					 }
					
				 }
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return total;
	}
}
