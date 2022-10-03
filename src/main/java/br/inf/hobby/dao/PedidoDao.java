package br.inf.hobby.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import br.inf.hobby.model.Pedido;
import br.inf.hobby.model.Produto;

public class PedidoDao {

	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public PedidoDao(Connection con) {
		this.con = con;
	}
	
	public boolean inserirPedido(Pedido pedido) {
		
		boolean result = false;
		
		try {
			query = "insert into pedidos(usuario_id, produto_id, quantidade, data)values(?,?,?,?)";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, pedido.getUsuarioId());
			pst.setInt(2, pedido.getProdutoId());
			pst.setInt(3, pedido.getQuantidade());
			pst.setString(4, pedido.getData());
			pst.executeUpdate();
			result = true;			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public List<Pedido> pedidosUsuario(int id) {		
		ArrayList<Pedido> lista_pedido = new ArrayList<Pedido>();
		
		try {
			query = "select * from pedidos where usuario_id=? order by pedidos.id desc";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				Pedido pedido = new Pedido();
				ProdutoDao produtoDao = new ProdutoDao(this.con);
				int produtoId = rs.getInt("produto_id");
				
				Produto produto = produtoDao.obterProdutoUnico(produtoId);
				pedido.setId(rs.getInt("id"));
				pedido.setProdutoId(produtoId);
				pedido.setNome(produto.getNome());
				pedido.setCategoria(produto.getCategoria());
				pedido.setPreco(produto.getPreco() * rs.getInt("quantidade"));
				pedido.setQuantidade(rs.getInt("quantidade"));
				pedido.setData(rs.getString("data"));		
				lista_pedido.add(pedido);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista_pedido;
	}
	
	public void cancelaPedido(int id) {
		try {
			query = "delete from pedidos where id=?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
