package modelo;
/**
 * <p>A classe Item serve para modelar os itens comprados pelo cliente.</p>
 * @author Pedro Henrique Rodrigues, Chaydson Ferreira
 */
public class Item {

	private Integer quantidadeComprada;
	private Produto produto;

	public Item(Integer quantidadeComprada, Produto produto) {
		this.quantidadeComprada = quantidadeComprada;
		this.produto = produto;
		produto.setQuantidade(produto.getQuantidade() - quantidadeComprada);
		adicionarProdutosVendidos();
	}
	
	public Item(Integer quantidadeComprada) {
		this.quantidadeComprada = quantidadeComprada;
	}

	public Integer getQuantidadeComprada() {
		return quantidadeComprada;
	}

	public void setQuantidadeComprada(Integer quantidadeComprada) {
		this.quantidadeComprada = quantidadeComprada;
	}

	public String getProduto() {
		return produto.getNome();
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	/**
	 * Método que atualiza o quanto um determinado produto foi vendido em caso de venda realizada.
	 */
	public void adicionarProdutosVendidos() {
		produto.setQuantidadeTotalVendida(produto.getQuantidadeTotalVendida() + quantidadeComprada);
	}
	
	/**
	 * Método que atualiza o quanto um determinado produto foi vendido em caso de desistência da venda.
	 */
	public void reverterProdutosVendidos() {
		produto.setQuantidadeTotalVendida(produto.getQuantidadeTotalVendida() - quantidadeComprada);
	}
	
	/**
	 * Método que devolve o produto ao estoque em caso desistência da venda.
	 */
	public void devolverProduto() {
		produto.setQuantidade(produto.getQuantidade() + quantidadeComprada);
	}
	
	/**
	 * Método que calcula o valor de cada item da venda separadamente.
	 * @return Valor do item comprado.
	 */
	public Double calcularSubtotal() {
		return quantidadeComprada * produto.getPreco();
	}
	
	
}
