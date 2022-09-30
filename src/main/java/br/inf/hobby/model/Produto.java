package br.inf.hobby.model;

public class Produto {
	
	private int id;
	private String nome;
	private String categoria;
	private double preco;
	private String imagem;
	
	public Produto() {
	}
	
	public Produto(int id, String nome, String categoria, double preco, String imagem) {
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
		this.preco = preco;
		this.imagem = imagem;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", categoria=" + categoria + ", preco=" + preco + ", imagem="
				+ imagem + "]";
	}	
}
