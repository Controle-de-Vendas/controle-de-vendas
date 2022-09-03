package controle;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import interfaces.CRUD;
import modelo.Venda;
import modelo.Vendedor;

public class ControleVendedor implements CRUD {
	
	public static List<Vendedor> bancoDeDadosVendedor = new ArrayList<Vendedor>();
	public static List<String> listaVendedores = new ArrayList<String>();
	
	public void atualizarSalarioVendedor() {
		for(Vendedor v : bancoDeDadosVendedor) {
			for(Venda e : ControleVenda.bancoDeDadosVenda) {
				if(v.getNome() == e.getVendedor()) {
					v.addVendas(e);
				}
			}
		}
	}

	@Override
	public void cadastrar(Object vendedor) {
		bancoDeDadosVendedor.add((Vendedor) vendedor);
		ControleGerente.bancoDeDadosFuncionario.add((Vendedor) vendedor);
		JOptionPane.showMessageDialog(null,"Cadastro realizado com sucesso!");
	}
	
	public void atualizarListaVendedores() {
		for(Vendedor v : bancoDeDadosVendedor) {
    		listaVendedores.add(v.getNome());
    	}
	}

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
