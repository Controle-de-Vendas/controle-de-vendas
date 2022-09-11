package modelo;

import enumeracao.Departamento;
/**
 * <p> A classe Funcionário, que extende Pessoa, serve para generalizar todos os funcionários.</p> 
 * @see Pessoa 
 * @author Pedro Henrique Rodrigues, Chaydson Ferreira
 *
 */
public abstract class Funcionario extends Pessoa {

	private Departamento departamento;
	final protected Double salarioBase = 800.0;

	public Funcionario(Integer id, String name, String cpf, Departamento departamento) {
		super(id, name, cpf);
		this.departamento = departamento;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Double getSalarioBase() {
		return salarioBase;
	}
	/**
	 * Método abstrato que calcula o salário de cada funcionário de acordo com a função.
	 * @return salário do funcionário.
	 * 
	 */
	public abstract double calcularSalario();
}
