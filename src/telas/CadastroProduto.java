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

import controle.ControleProduto;
import modelo.Produto;
import utilitarios.JtextFieldSomenteNumeros;

public class CadastroProduto extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private Integer id = null;
	private Object[] cadastro = null;
	private JButton botaoSalvar = new JButton();
    private JLabel labCadastroProdutos = new JLabel();
    private JLabel labNome = new JLabel();
    private JLabel labPreco = new JLabel();
    private JLabel labQuantidade = new JLabel();
    private JTextField textFieldNome = new JTextField();
    private JTextField textFieldPreco = new JTextField();
    private JtextFieldSomenteNumeros textFieldQuantidade = new JtextFieldSomenteNumeros();
    private ControleProduto controleProdutos = new ControleProduto();
    
    public Object[] getCadastro() {
		return cadastro;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public JTextField getTextFieldNome() {
		return textFieldNome;
	}

	public JTextField getTextFieldPreco() {
		return textFieldPreco;
	}

	public JtextFieldSomenteNumeros getTextFieldQuantidade() {
		return textFieldQuantidade;
	}

	public CadastroProduto(Frame parent, boolean modal) {
    	super(parent, modal);
        iniciarLayout();
        this.setLocationRelativeTo(null);
    }
	
	private void botaoSalvarActionPerformed(ActionEvent evt) {
		String nomeProduto = textFieldNome.getText();
		Double precoProduto = null;
		try {
			precoProduto = Double.parseDouble(textFieldPreco.getText());
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null,"Digite valores no formato 0.00");
		}
		String quantidadeProduto = textFieldQuantidade.getText();
		
		if(id != null) {
			if(nomeProduto.equals("") || quantidadeProduto.equals("")) {
				JOptionPane.showMessageDialog(null,"Preencha todos os campos, porfavor.");
			}
			else if(precoProduto != null) {
				for(Produto p : ControleProduto.bancoDeDadosProduto) {
					if(id == p.getId()) {
						p.setNome(nomeProduto);
						p.setPreco(precoProduto);
						p.setQuantidade(Integer.parseInt(quantidadeProduto));
						break;
					}
				}
			}			
		}
		else {
			if(nomeProduto.equals("") || quantidadeProduto.equals("")) {
				JOptionPane.showMessageDialog(null,"Preencha todos os campos, porfavor.");
			}
			else if(precoProduto != null) {
				Produto produto = new Produto(nomeProduto,
						precoProduto,
						Integer.parseInt(quantidadeProduto));
		    	
		    	controleProdutos.cadastrar(produto);
		    	ControleProduto.itensProduto.add(produto.getNome());
		    	
		    	Integer id = produto.getId();
		    	String nome = produto.getNome();
		    	Double preco = produto.getPreco();
		    	Integer quantidadeVendida = produto.getQuantidadeTotalVendida();
		    	Integer quantidade = produto.getQuantidade();
		    	
		    	Object[] cadastro = new Object[]{id, nome, preco, quantidadeVendida, quantidade};
		    	this.cadastro = cadastro;
		    	dispose();
		}   	
	}
	if(precoProduto != null){
		dispose();
	}
 }
                         
    private void iniciarLayout() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Produtos");

        labCadastroProdutos.setFont(new Font("Segoe UI", 1, 24));
        labCadastroProdutos.setHorizontalAlignment(SwingConstants.CENTER);
        labCadastroProdutos.setText("Cadastro de Produtos");

        botaoSalvar.setText("Salvar");
        botaoSalvar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botaoSalvarActionPerformed(evt);
            }
        });

        labNome.setFont(new Font("Segoe UI", 1, 17));
        labNome.setText("Nome");

        labPreco.setFont(new Font("Segoe UI", 1, 17));
        labPreco.setText("Preço");

        labQuantidade.setFont(new Font("Segoe UI", 1, 17));
        labQuantidade.setText("Quantidade");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(labCadastroProdutos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(labQuantidade)
            .addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
            .addComponent(labNome)
            .addComponent(textFieldPreco, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
            .addComponent(labPreco))
            .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
            .addComponent(textFieldQuantidade)
            .addGap(18, 18, 18)
            .addComponent(botaoSalvar)))
            .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(labCadastroProdutos, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(labNome)
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(labPreco)
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(textFieldPreco, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(labQuantidade)
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(textFieldQuantidade, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
            .addComponent(botaoSalvar, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }                                                                               
}

