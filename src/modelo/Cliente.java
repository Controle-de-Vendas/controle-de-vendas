package modelo;

public class Cliente extends Pessoa {
	private static Integer idCliente = 0;
	private String endereco;

	public Cliente(String name, String cpf, String endereco) {
		super(idCliente + 1, name, cpf);
		this.endereco = endereco;
		idCliente += 1;
	}


	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}
