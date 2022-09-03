package controle;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import interfaces.CRUD;
import modelo.Produto;
/**  
 * 
 * <p>A classe ControleProduto serve como "banco de dados" do sistema, onde é possivel adicionar, modificar e deletar produtos no sistema.</p>  
 * @author Pedro Henrique Rodrigues, Chaydson Ferreira
 * 
 */
public class ControleProduto implements CRUD {
	
	public static List<Produto> bancoDeDadosProduto = new ArrayList<Produto>();
	public static List<String> itensProduto = new ArrayList<String>();
	
	/**
	 * Método que cadastra um objeto produto no "banco de dados" produto.
	 * @param Produto que será cadastrado.
	 */
	@Override
	public void cadastrar(Object produto) {
		bancoDeDadosProduto.add((Produto)produto);
		JOptionPane.showMessageDialog(null,"Cadastro realizado com sucesso!");
	}
	
	/**
	 * Método que adiciona o nome de todos os produtos em itensProduto.
	 * 
	 */
	public void atualizarItensProduto() {
		for(Produto p : bancoDeDadosProduto) {
    		itensProduto.add(p.getNome());
    	}
	}
	
	/**
	 * Método que esvazia a lista itensProduto.
	 * 
	 */
	public void limparItensProduto() {
		itensProduto.clear();
	}
	
	/**
	 * Método que deleta um objeto produto no "banco de dados" produto.
	 * @param Produto que será deletado.
	 */
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
