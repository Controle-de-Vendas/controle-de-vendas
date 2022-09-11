package controle;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import interfaces.CRUD;
import modelo.Funcionario;
import modelo.Gerente;
import modelo.Vendedor;
/**  
 * 
 * <p>A classe ControleGerente serve como "banco de dados" do sistema, onde é possivel adicionar, modificar e deletar gerentes no sistema.</p>  
 * @author Pedro Henrique Rodrigues, Chaydson Ferreira
 * 
 */
public class ControleGerente implements CRUD {
	
	public static List<Gerente> bancoDeDadosGerente = new ArrayList<Gerente>();
	public static List<Funcionario> bancoDeDadosFuncionario = new ArrayList<Funcionario>();
	
	/**
	 * Método que associa os vendedores de um departamento com seu gerente.
	 * 
	 */
	public void atualizarVendedoresAssociados() {
		for(Vendedor v : ControleVendedor.bancoDeDadosVendedor) {
			for(Gerente g : bancoDeDadosGerente) {
				if(v.getDepartamento() == g.getDepartamento()) {
					g.addVendedoresAssociados(v);
				}
			}
		}
	}
	
	/**
	 * Método que cadastra um objeto gerente e funcionário no "banco de dados" gerente e funcionário respectivamente.
	 * @param Gerente que será cadastrado.
	 */
	@Override
	public void cadastrar(Object gerente) {
		bancoDeDadosGerente.add((Gerente) gerente);
		bancoDeDadosFuncionario.add((Gerente) gerente);
		JOptionPane.showMessageDialog(null,"Cadastro realizado com sucesso!");
	}
	
	/**
	 * Método que deleta um objeto gerente no "banco de dados" gerente.
	 * @param Gerente que será deletado.
	 */
	@Override
	public void deletar(Object gerente) {
		for(Gerente g : bancoDeDadosGerente) {
			if(g == gerente) {
				bancoDeDadosGerente.remove(gerente);
			}
		}
		JOptionPane.showMessageDialog(null,"Gerente deletado com sucesso!");
	}
}
