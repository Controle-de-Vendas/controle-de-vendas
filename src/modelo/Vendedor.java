package modelo;

import java.util.ArrayList;
import java.util.List;

import enumeracao.Departamento;
/**
 * <p>A classe Vendedor serve para modelar os funcion�rios vendedores.</p>
 * @see Funcionario
 * @author Pedro Henrique Rodrigues, Chaydson Ferreira
 */
public class Vendedor extends Funcionario {

	private static Integer idVendedor = 0;
	private final Double porcentagem = 0.10;
	private List<Venda> vendas = new ArrayList<Venda>();

	public Vendedor(String name, String cpf, Departamento departamento) {
		super(idVendedor +1, name, cpf, departamento);
		idVendedor +=1;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public void addVendas(Venda venda) {
		vendas.add(venda);
	}
	
	/**
	 * M�todo que calcula o sal�rio do vendedor.
	 * @return sal�rio do vendedor.
	 */
	@Override
	public double calcularSalario() {
		double comissao = 0;
		for (Venda v : vendas) {
		comissao += v.getValor() * this.porcentagem;
		}

		Double salarioTotal = comissao + this.salarioBase;

		return salarioTotal;
	}
}
