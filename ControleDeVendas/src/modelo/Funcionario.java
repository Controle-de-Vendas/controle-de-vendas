package modelo;

import enumeracao.Departamento;
/**
 * <p> A classe Funcion�rio, que extende Pessoa, serve para generalizar todos os funcion�rios.</p> 
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
	 * M�todo abstrato que calcula o sal�rio de cada funcion�rio de acordo com a fun��o.
	 * @return sal�rio do funcion�rio.
	 * 
	 */
	public abstract double calcularSalario();
}
