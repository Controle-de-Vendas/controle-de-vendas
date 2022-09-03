package controle;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import interfaces.CRUD;
import modelo.Cliente;

public class ControleCliente implements CRUD {
	
	public static List<Cliente> bancoDeDadosCliente = new ArrayList<Cliente>();
	public static List<String> listaClientes = new ArrayList<String>();
	
	public ControleCliente() {
		
	}

	@Override
	public void cadastrar(Object cliente) {
		bancoDeDadosCliente.add((Cliente) cliente);
		JOptionPane.showMessageDialog(null,"Cadastro realizado com sucesso!");
	}
	
	public void atuzalizarListaClientes() {
		for(Cliente c : bancoDeDadosCliente) {
    		listaClientes.add(c.getNome());
    	}
	}

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
