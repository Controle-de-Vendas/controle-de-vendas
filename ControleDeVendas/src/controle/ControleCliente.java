package controle;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import interfaces.CRUD;
import modelo.Cliente;
/**  
 * 
 * <p>A classe ControleCliente serve como "banco de dados" do sistema, onde � possivel adicionar, modificar e deletar clientes no sistema.</p>  
 * @author Pedro Henrique Rodrigues, Chaydson Ferreira
 * 
 */
public class ControleCliente implements CRUD {
	
	public static List<Cliente> bancoDeDadosCliente = new ArrayList<Cliente>();
	public static List<String> listaClientes = new ArrayList<String>();
	
	public ControleCliente() {
		
	}
	
	/**
	 * M�todo que cadastra um objeto cliente no "banco de dados".
	 * @param Cliente que ser� cadastrado
	 * 
	 */
	@Override
	public void cadastrar(Object cliente) {
		bancoDeDadosCliente.add((Cliente) cliente);
		JOptionPane.showMessageDialog(null,"Cadastro realizado com sucesso!");
	}
	
	/**
	 * M�todo que adiciona o nome de todos os clientes na listaClientes.
	 * 
	 */
	public void atuzalizarListaClientes() {
		for(Cliente c : bancoDeDadosCliente) {
    		listaClientes.add(c.getNome());
    	}
	}
	
	/**
	 * M�todo que deleta um objeto cliente no "banco de dados".
	 * @param Cliente que ser� deletado
	 */
	@Override
	public void deletar(Object cliente) {
		for(Cliente c : bancoDeDadosCliente) {
			if(c == cliente) {
				bancoDeDadosCliente.remove(cliente);
			}
		}
		JOptionPane.showMessageDialog(null,"Cliente deletado com sucesso!");
	}	
}
