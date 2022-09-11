package modelo;

/**
 * <p> A classe Produto serve para modelar os produtos vendidos no sistema.</p>
 * @author Pedro Henrique Rodrigues, Chaydson Ferreira
 *
 */
public class Produto {
	
	private static Integer auxilizarAtualizaId = 0;
	private Integer id;
	private String nome;
	private Double preco;
	private Integer quantidadeTotalVendida = 0;
	private Integer quantidade;
	
	public Produto(String nome, Double preco, Integer quantidade) {
		this.id = auxilizarAtualizaId + 1;
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		auxilizarAtualizaId += 1;
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
