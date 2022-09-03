
package telas;

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
import controle.ControleVendedor;
import enumerações.Departamento;
import modelo.Gerente;
import modelo.Vendedor;
import utilitarios.CpfValidator;

public class CadastroVendedor extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JButton botaoSalvar = new JButton();
    private JComboBox<String> comboBoxDepartamento = new JComboBox<String>();
    private JLabel labCadastroVendedores = new JLabel();
    private JLabel labCpf = new JLabel();
    private JLabel labDepartamento = new JLabel();
    private JLabel labNome = new JLabel();
    private JTextField textFieldCpf = new JTextField();
    private JTextField textFieldNome = new JTextField();
    
    private ControleVendedor controleVendedor = new ControleVendedor();

    public CadastroVendedor(Frame parent, boolean modal) {
    	super(parent, modal);
    	iniciarLayout();
        this.setLocationRelativeTo(null);
    }
    
    private void botaoSalvarActionPerformed(ActionEvent evt) {
    	String cpf = textFieldCpf.getText();
    	String nome = textFieldNome.getText();
    	
    	if(nome.equals("") || cpf.equals("")) {
    		JOptionPane.showMessageDialog(null,"Preencha todos os campos, porfavor.");
    	}
    	else {
        	if(CpfValidator.Cpf(cpf) == true) {
        		Vendedor vendedor = new Vendedor(nome,
        				cpf, 
        				Departamento.valueOf(comboBoxDepartamento.getSelectedItem().toString().toUpperCase()));
        		controleVendedor.cadastrar(vendedor);
            	ControleVendedor.listaVendedores.add(vendedor.getNome());
            	dispose();
            	
            	for(Gerente g : ControleGerente.bancoDeDadosGerente) {
            		if(g.getDepartamento() == vendedor.getDepartamento()) {
            			g.addVendedoresAssociados(vendedor);
            			break;
            		}
            	}
        	}
        	else {
        		JOptionPane.showMessageDialog(null,"CPF invalido!");
        	}
    	}
    }
                         
    private void iniciarLayout() {

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Vendedores");

        labCadastroVendedores.setFont(new Font("Segoe UI", 1, 24));
        labCadastroVendedores.setHorizontalAlignment(SwingConstants.CENTER);
        labCadastroVendedores.setText("Cadastro de Vendedores");

        comboBoxDepartamento.setModel(new DefaultComboBoxModel<>(new String[] { "Tecnologia", "Saúde", "Beleza", "Utilidades", "Esporte" }));

        botaoSalvar.setText("Salvar");
        botaoSalvar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botaoSalvarActionPerformed(evt);
            }
        });

        labDepartamento.setFont(new Font("Segoe UI", 1, 17));
        labDepartamento.setText("Departamento");

        labNome.setFont(new Font("Segoe UI", 1, 17));
        labNome.setText("Nome");

        labCpf.setFont(new Font("Segoe UI", 1, 17));
        labCpf.setText("CPF");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(labCadastroVendedores, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
            .addComponent(textFieldNome, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
            .addComponent(labNome, GroupLayout.Alignment.LEADING)
            .addComponent(textFieldCpf, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
            .addComponent(labCpf, GroupLayout.Alignment.LEADING)
            .addComponent(labDepartamento, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
            .addGap(0, 27, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
            .addComponent(comboBoxDepartamento, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGap(18, 18, 18)
            .addComponent(botaoSalvar)))
            .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(labCadastroVendedores, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
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
