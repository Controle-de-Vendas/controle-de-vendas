package modelo;
/**  
 * 
 * <p>A classe abstrata Pessoa serve para generalizar as entidades do sistema</p> 
 * @author Pedro Henrique Rodrigues, Chaydson Ferreira
 * 
 */
public abstract class Pessoa {
	
	private Integer id;
	private String nome;
	private String cpf;
	
	public Pessoa(Integer id, String nome, String cpf) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String name) {
		this.nome = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
