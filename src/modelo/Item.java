package modelo;

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

	public void adicionarProdutosVendidos() {
		produto.setQuantidadeTotalVendida(produto.getQuantidadeTotalVendida() + quantidadeComprada);
	}
	
	public void reverterProdutosVendidos() {
		produto.setQuantidadeTotalVendida(produto.getQuantidadeTotalVendida() - quantidadeComprada);
	}
	
	public void devolverProduto() {
		produto.setQuantidade(produto.getQuantidade() + quantidadeComprada);
	}

	public Double calcularSubtotal() {
		return quantidadeComprada * produto.getPreco();
	}
	
	
}
