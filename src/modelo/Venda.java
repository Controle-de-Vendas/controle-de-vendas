package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import enumerações.Pagamento;
import enumerações.StatusDaVenda;

public class Venda {
	
	private static Integer idVenda = 0;
	private Integer id;
	private Vendedor vendedor;
	private Cliente cliente;
	private List<Item> itens;
	private String data;
	private Double valor;
	private StatusDaVenda status;
	private Pagamento pagamento;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Venda(Vendedor vendedor, Cliente cliente , List<Item> itens,
			StatusDaVenda status, Pagamento pagamento) {
		this.id = idVenda + 1;
		this.vendedor = vendedor;
		this.cliente = cliente;
		this.itens = itens;
		this.data = sdf.format(new Date());
		this.valor = calcularTotal();
		this.status = status;
		this.pagamento = pagamento;
		idVenda += 1;
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
		return valor;
	}



	public void setValor() {
		Double total = 0.0;
		for (Item i : itens) {
			total += i.calcularSubtotal();
		}
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

	public Double calcularTotal() {
		Double total = 0.0;
		for (Item i : itens) {
			total += i.calcularSubtotal();
		}
		return total;
	}
	
	public void zerarListaItens() {
		for(Item i : itens) {
			i.devolverProduto();
			i.reverterProdutosVendidos();
		}
	}
}
