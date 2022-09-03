package controle;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import interfaces.CRUD;
import modelo.Venda;
/**  
 * 
 * <p>A classe ControleVenda serve como "banco de dados" do sistema, onde � possivel adicionar, modificar e deletar vendas no sistema.</p>  
 * @author Pedro Henrique Rodrigues, Chaydson Ferreira
 * 
 */
public class ControleVenda implements CRUD {
	
	public static List<Venda> bancoDeDadosVenda = new ArrayList<Venda>();
	
	/**
	 * M�todo que cadastra um objeto venda no "banco de dados" venda.
	 * @param Venda que ser� cadastrada.
	 */
	@Override
	public void cadastrar(Object venda) {
		bancoDeDadosVenda.add((Venda) venda);
		JOptionPane.showMessageDialog(null,"Cadastro realizado com sucesso!");
		
	}
	
	/**
	 * M�todo que deleta um objeto venda no "banco de dados" venda.
	 * @param Venda que ser� deletada.
	 */
	@Override
	public void deletar(Object venda) {
		for(Venda v : bancoDeDadosVenda) {
			if(v == venda) {
				bancoDeDadosVenda.remove(venda);
				break;
			}
		}
		JOptionPane.showMessageDialog(null,"Venda deletada com sucesso!");
	}
}
