package br.inf.hobby.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.inf.hobby.model.Pedido;

public class PedidoDao {

	private Connection con;
	private String query;
	private PreparedStatement pst;
	
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
	
	
}
