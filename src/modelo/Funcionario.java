package modelo;

import enumerações.Departamento;

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

	public abstract double calcularSalario();
}
