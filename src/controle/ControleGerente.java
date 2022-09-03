package controle;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import interfaces.CRUD;
import modelo.Funcionario;
import modelo.Gerente;
import modelo.Vendedor;

public class ControleGerente implements CRUD {
	
	public static List<Gerente> bancoDeDadosGerente = new ArrayList<Gerente>();
	public static List<Funcionario> bancoDeDadosFuncionario = new ArrayList<Funcionario>();
	
	
	public void atualizarVendedoresAssociados() {
		for(Vendedor v : ControleVendedor.bancoDeDadosVendedor) {
			for(Gerente g : bancoDeDadosGerente) {
				if(v.getDepartamento() == g.getDepartamento()) {
					g.addVendedoresAssociados(v);
				}
			}
		}
	}

	@Override
	public void cadastrar(Object gerente) {
		bancoDeDadosGerente.add((Gerente) gerente);
		bancoDeDadosFuncionario.add((Gerente) gerente);
		JOptionPane.showMessageDialog(null,"Cadastro realizado com sucesso!");
	}

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
