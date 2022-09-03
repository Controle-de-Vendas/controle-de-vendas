package controle;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import interfaces.CRUD;
import modelo.Produto;

public class ControleProduto implements CRUD {
	
	public static List<Produto> bancoDeDadosProduto = new ArrayList<Produto>();
	public static List<String> itensProduto = new ArrayList<String>();

	@Override
	public void cadastrar(Object produto) {
		bancoDeDadosProduto.add((Produto)produto);
		JOptionPane.showMessageDialog(null,"Cadastro realizado com sucesso!");
	}
	
	public void atualizarItensProduto() {
		for(Produto p : bancoDeDadosProduto) {
    		itensProduto.add(p.getNome());
    	}
	}
	
	public void limparItensProduto() {
		itensProduto.clear();
	}

	@Override
	public void deletar(Object produto) {
		for(Produto p : bancoDeDadosProduto) {
			if(p == produto) {
				bancoDeDadosProduto.remove(produto);
				break;
			}
		}
		JOptionPane.showMessageDialog(null,"Venda deletada com sucesso!");
	}

}
