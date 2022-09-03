package telas;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import controle.ControleCliente;
import modelo.Cliente;
import utilitarios.CpfValidator;

public class CadastroCliente extends JDialog {
	
	private JButton botaoSalvar = new JButton();
    private JLabel labCadastroClientes = new JLabel();
    private JLabel labCpf = new JLabel();
    private JLabel labEndereco = new JLabel();
    private JLabel labNome = new JLabel();
    private JTextField textFieldCpf = new JTextField();
    private JTextField textFieldEndereco = new JTextField();
    private JTextField textFieldNome = new JTextField();  
    private ControleCliente controleCliente = new ControleCliente();

	private static final long serialVersionUID = 1L;
	public CadastroCliente(Frame parent, boolean modal) {
		super(parent, modal);
        iniciarLayout();
        this.setLocationRelativeTo(null);
    }
	
    private void botaoSalvarActionPerformed(ActionEvent evt) {
    	String cpf = textFieldCpf.getText();
    	String nome = textFieldNome.getText();
    	String endereco = textFieldEndereco.getText();
    	if (cpf.equals("")  || nome.equals("") || endereco.equals("")) {
    		JOptionPane.showMessageDialog(null,"Preencha todos os campos, por favor.");
    	}
    	else {
    		if(CpfValidator.Cpf(cpf) == true) {
        		Cliente cliente = new Cliente(nome, cpf, endereco);
        		controleCliente.cadastrar(cliente);
        		ControleCliente.listaClientes.add(cliente.getNome());
        		dispose();
        	}
        	else {
        		JOptionPane.showMessageDialog(null,"CPF invalido!");
        	}
    	}
    } 
    
    private void iniciarLayout() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Clientes");

        labCadastroClientes.setFont(new Font("Segoe UI", 1, 24));
        labCadastroClientes.setHorizontalAlignment(SwingConstants.CENTER);
        labCadastroClientes.setText("Cadastro de Clientes");

        botaoSalvar.setText("Salvar");
        botaoSalvar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botaoSalvarActionPerformed(evt);
            }
        });

        labNome.setFont(new Font("Segoe UI", 1, 17)); 
        labNome.setText("Nome");

        labCpf.setFont(new Font("Segoe UI", 1, 17)); 
        labCpf.setText("CPF");

        labEndereco.setFont(new Font("Segoe UI", 1, 17)); 
        labEndereco.setText("Endereço");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(labCadastroClientes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
            .addComponent(labNome)
            .addComponent(textFieldCpf, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
            .addComponent(labCpf)
            .addComponent(textFieldEndereco, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
            .addComponent(labEndereco))
            .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
            .addGroup(layout.createSequentialGroup()
            .addGap(122, 122, 122)
            .addComponent(botaoSalvar)
            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addComponent(labCadastroClientes, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(labNome)
            .addGap(4, 4, 4)
            .addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(labCpf)
            .addGap(4, 4, 4)
            .addComponent(textFieldCpf, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(labEndereco)
            .addGap(4, 4, 4)
            .addComponent(textFieldEndereco, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(botaoSalvar, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
            .addContainerGap())
        );
        pack();
    }                                                                              
}


