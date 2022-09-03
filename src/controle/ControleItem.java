package controle;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import interfaces.CRUD;
import modelo.Item;

public class ControleItem implements CRUD {
	
	public static List<Item> bancoDeDadosItem = new ArrayList<Item>();

	@Override
	public void cadastrar(Object item) {
		bancoDeDadosItem.add((Item) item);
		JOptionPane.showMessageDialog(null,"Cadastro realizado com sucesso!");
	}

	@Override
	public void deletar(Object item) {
		for(Item i : bancoDeDadosItem) {
			if(i == item) {
				bancoDeDadosItem.remove(item);
			}
		}
		JOptionPane.showMessageDialog(null,"Item deletado com sucesso!");
		
	}

}
