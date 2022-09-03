package controle;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import interfaces.CRUD;
import modelo.Venda;

public class ControleVenda implements CRUD {
	
	public static List<Venda> bancoDeDadosVenda = new ArrayList<Venda>();

	@Override
	public void cadastrar(Object venda) {
		bancoDeDadosVenda.add((Venda) venda);
		JOptionPane.showMessageDialog(null,"Cadastro realizado com sucesso!");
		
	}

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
