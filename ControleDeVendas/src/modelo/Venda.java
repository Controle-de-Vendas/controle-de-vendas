package modelo;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import enumeracao.Pagamento;
import enumeracao.StatusDaVenda;
/**
 * <p>A classe venda serve para modelar as vendas realizadas no sistema se relacionando com a classe Item no processo.</p>
 * @see Item
 * @author Pedro Henrique Rodrigues, Chaydson Ferreira
 */
public class Venda {
	
	private static Integer auxiliarAtualizaId = 0;
	private Integer id;
	private Vendedor vendedor;
	private Cliente cliente;
	private List<Item> itens = new ArrayList<Item>();
	private String data;
	private Double valor;
	private StatusDaVenda status;
	private Pagamento pagamento;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	static DecimalFormat df = new DecimalFormat("#,###.##", new DecimalFormatSymbols(Locale.GERMAN));
	
	public Venda(Vendedor vendedor, Cliente cliente , List<Item> itens,
			StatusDaVenda status, Pagamento pagamento) {
		this.id = auxiliarAtualizaId + 1;
		this.vendedor = vendedor;
		this.cliente = cliente;
		this.itens = itens;
		this.data = sdf.format(new Date());
		this.valor = calcularTotal();
		this.status = status;
		this.pagamento = pagamento;
		auxiliarAtualizaId += 1;
	}

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getVendedor() {
		return vendedor.getNome();
	}



	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}



	public String getCliente() {
		return cliente.getNome();
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public List<Item> getItens() {
		return itens;	
	}



	public void setItens(List<Item> itens) {
		this.itens = itens;
	}



	public String getData() {
		return data;
	}



	public void setData(String data) {
		this.data = data;
	}



	public Double getValor() {
		valor = calcularTotal();
		return valor;
	}



	public void setValor() {
		Double total = 0.0;
		for (Item i : itens) {
			total += i.calcularSubtotal();
		}
		df.format(total);
		this.valor = total;
	}



	public String getStatus() {
		return status.toString().replaceAll("_", " ").toLowerCase();
	}



	public void setStatus(StatusDaVenda status) {
		this.status = status;
	}

	public String getPagamento() {
		return pagamento.toString().toLowerCase();
	}
	
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	/**
	 * Método responsável por calcular o valor total da venda realizada.
	 * @return valor total da venda realizada.
	 */
	public Double calcularTotal() {
		Double total = 0.0;
		for (Item i : itens) {
			total += i.calcularSubtotal();
		}
		return total;
	}
	
	/**
	 * Método responsável por "devolver" os produtos vendidos ao estoque em caso de exclusão da venda do sistema.
	 */
	public void zerarListaItens() {
		for(Item i : itens) {
			i.devolverProduto();
			i.reverterProdutosVendidos();
		}
	}
}
