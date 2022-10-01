package br.inf.hobby.model;

public class Pedido extends Produto {

	private int id;
	private int usuarioId;
	private int quantidade;
	private String data;
	
	public Pedido() {
	}

	public Pedido(int id, int usuarioId, int quantidade, String data) {
		this.id = id;
		this.usuarioId = usuarioId;
		this.quantidade = quantidade;
		this.data = data;
	}

	public Pedido(int usuarioId, int quantidade, String data) {
		this.usuarioId = usuarioId;
		this.quantidade = quantidade;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", usuarioId=" + usuarioId + ", quantidade=" + quantidade + ", data=" + data + "]";
	}	
}
