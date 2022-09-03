package modelo;

public class Produto {
	
	private static Integer idProduto = 0;
	private Integer id;
	private String nome;
	private Double preco;
	private Integer quantidadeTotalVendida = 0;
	private Integer quantidade;
	
	public Produto(String nome, Double preco, Integer quantidade) {
		this.id = idProduto + 1;
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		idProduto += 1;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getQuantidadeTotalVendida() {
		return quantidadeTotalVendida;
	}

	public void setQuantidadeTotalVendida(Integer quantidadeTotalVendida) {
		this.quantidadeTotalVendida = quantidadeTotalVendida;
	}
}
