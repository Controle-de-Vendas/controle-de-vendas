package tela;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import controle.ControleGerente;
import enumeracao.Departamento;
import modelo.Gerente;
import utilitario.CpfValidator;
/**  
 * 
 * <p>A classe CadastroCliente cria um "JDialog" (janela) que cadastra os gerentes.</p>  
 * @author Pedro Henrique Rodrigues, Chaydson Ferreira
 * 
 */
public class CadastroGerente extends JDialog {

	private static final long serialVersionUID = 1L;
	private JButton botaoSalvar = new JButton();
    private JComboBox<String> comboBoxDepartamento = new JComboBox<>();
    private JLabel labCadastroGerentes = new JLabel();
    private JLabel labCpf = new JLabel();
    private JLabel labDepartamento = new JLabel();
    private JLabel labNome = new JLabel();
    private JTextField textFieldCpf = new JTextField();
    private JTextField textFieldNome = new JTextField();
    private ControleGerente controleGerente = new ControleGerente();

    public CadastroGerente(Frame parent, boolean modal) {
    	super(parent, modal);
    	iniciarLayout();
        this.setLocationRelativeTo(null);
    }
    
    /**
	 * Método que salva o gerente.
	 * @param evt É o evento de click.
	 */
    private void botaoSalvar(ActionEvent evt) {
    	String cpf = textFieldCpf.getText();
    	String nome = textFieldNome.getText();
    	
    	if(cpf.equals("") || nome.equals("")) {
    		JOptionPane.showMessageDialog(null,"Preencha todos os campos, porfavor.");
    	}
    	else {
    		if(CpfValidator.Cpf(cpf) == true) {
        		Gerente gerente = (new Gerente(nome,
        				cpf, 
        				Departamento.valueOf(comboBoxDepartamento.getSelectedItem().toString().toUpperCase())));
        		controleGerente.cadastrar(gerente);
            	dispose();
        	}
        	else {
        		JOptionPane.showMessageDialog(null,"CPF invalido!");
        	}
    	}
    }
    
    /**
	 * Método que inicia os componentes, configurações e dependências do layout.
	 * 
	 */
    private void iniciarLayout() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Gerentes");

        botaoSalvar.setText("Salvar");
        botaoSalvar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	botaoSalvar(evt);
            }
        });

        labDepartamento.setFont(new Font("Segoe UI", 1, 17));
        labDepartamento.setText("Departamento");

        labCadastroGerentes.setFont(new Font("Segoe UI", 1, 24));
        labCadastroGerentes.setHorizontalAlignment(SwingConstants.CENTER);
        labCadastroGerentes.setText("Cadastro de Gerentes");

        comboBoxDepartamento.setModel(new DefaultComboBoxModel<>(new String[] { "Tecnologia", "Saúde", "Beleza", "Utilidades", "Esportes" }));

        labNome.setFont(new Font("Segoe UI", 1, 17));
        labNome.setText("Nome");

        labCpf.setFont(new Font("Segoe UI", 1, 17));
        labCpf.setText("CPF");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(labCadastroGerentes, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addComponent(labDepartamento, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
            .addGap(190, 190, 190))
            .addGroup(layout.createSequentialGroup()
            .addComponent(comboBoxDepartamento, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(botaoSalvar)
            .addGap(12, 12, 12))
            .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
            .addComponent(labNome)
            .addComponent(textFieldCpf, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
            .addComponent(labCpf))
            .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(labCadastroGerentes, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(labNome)
            .addGap(4, 4, 4)
            .addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(labCpf)
            .addGap(4, 4, 4)
            .addComponent(textFieldCpf, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(labDepartamento, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(comboBoxDepartamento, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
            .addComponent(botaoSalvar, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }                                                                                             
}
