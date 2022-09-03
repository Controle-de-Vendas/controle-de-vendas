package controle;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import interfaces.CRUD;
import modelo.Venda;
import modelo.Vendedor;
/**  
 * 
 * <p>A classe ControleVendedor serve como "banco de dados" do sistema, onde é possivel adicionar, modificar e deletar vendedores no sistema.</p>  
 * @author Pedro Henrique Rodrigues, Chaydson Ferreira
 * 
 */
public class ControleVendedor implements CRUD {
	
	public static List<Vendedor> bancoDeDadosVendedor = new ArrayList<Vendedor>();
	public static List<String> listaVendedores = new ArrayList<String>();
	
	/**
	 * Método associa cada vendedor com sua venda realizada, adicionando um acréscimo a comissão do mesmo.
	 * 
	 */
	public void atualizarSalarioVendedor() {
		for(Vendedor v : bancoDeDadosVendedor) {
			for(Venda e : ControleVenda.bancoDeDadosVenda) {
				if(v.getNome() == e.getVendedor()) {
					v.addVendas(e);
				}
			}
		}
	}
	
	/**
	 * Método que cadastra um objeto vendedor no "banco de dados" vendedor.
	 * @param Vendedor que será cadastrado.
	 */
	@Override
	public void cadastrar(Object vendedor) {
		bancoDeDadosVendedor.add((Vendedor) vendedor);
		ControleGerente.bancoDeDadosFuncionario.add((Vendedor) vendedor);
		JOptionPane.showMessageDialog(null,"Cadastro realizado com sucesso!");
	}
	
	/**
	 * Método que adiciona o nome de todos os vendedores em listaVendedores.
	 * 
	 */
	public void atualizarListaVendedores() {
		for(Vendedor v : bancoDeDadosVendedor) {
    		listaVendedores.add(v.getNome());
    	}
	}
	
	/**
	 * Método que deleta um objeto vendedor no "banco de dados" vendedor.
	 * @param Vendedor que será deletado.
	 */
	@Override
	public void deletar(Object vendedor) {
		for(Vendedor v : bancoDeDadosVendedor) {
			if(v == vendedor) {
				bancoDeDadosVendedor.remove(vendedor);
			}
		}
		JOptionPane.showMessageDialog(null,"Vendedor deletado com sucesso!");
	}
}
