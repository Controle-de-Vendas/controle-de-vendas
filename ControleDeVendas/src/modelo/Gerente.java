package modelo;

import java.util.ArrayList;
import java.util.List;

import enumeracao.Departamento;
/**
 * <p>A classe Gerente serve para modelar os funcionários gerentes.</p>
 * @see Funcionario
 * @author Pedro Henrique Rodrigues, Chaydson Ferreira
 */
public class Gerente extends Funcionario {
	
	private static Integer idGerente = 0;
	private final Double porcentagem = 0.05;
	private List<Vendedor> vendedoresAssociados = new ArrayList<Vendedor>();

	public Gerente(String name, String cpf, Departamento departamento) {
		super(idGerente + 1, name, cpf, departamento);
		idGerente += 1;
	}
	
	
	public List<Vendedor> getVendedoresAssociados() {
		return vendedoresAssociados;
	}


	/**
	 * Método que adiciona um objeto vendedor na lista de vendedores associados.
	 * @param Objeto vendedor.
	 */
	public void addVendedoresAssociados(Vendedor vendedor) {
		vendedoresAssociados.add(vendedor);
	}

	/**
	 * Método que calcula o salário de gerente.
	 * @return salário do gerente.
	 */
	@Override
	public double calcularSalario() {
		Double comissao = 0.0;
		for (Vendedor v : vendedoresAssociados) {
			for (Venda s : v.getVendas()) {
				comissao += s.getValor() * this.porcentagem;
			}
		}
		Double salarioTotal = comissao + this.salarioBase;
		return salarioTotal;
	}

}
