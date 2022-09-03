package telas;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import controle.ControleVenda;
import controle.ControleVendedor;
import enumerações.Pagamento;
import enumerações.StatusDaVenda;
import modelo.Produto;
import modelo.Venda;
import modelo.Vendedor;
import utilitarios.JtextFieldSomenteNumeros;
import modelo.Cliente;
import modelo.Item;
import controle.ControleCliente;
import controle.ControleProduto;


public class CadastroVenda extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private Integer id = null;
	private Object[] cadastroVenda = null;
    private JButton botaoAdicionar = new JButton();
    private JButton botaoRemover = new JButton();
    private JButton botaoSalvar = new JButton();
    private JComboBox<String> comboBoxCliente = new JComboBox<>();
    private JComboBox<String> comboBoxFormaPagamento = new JComboBox<>();
    private JComboBox<String> comboBoxItens = new JComboBox<>();
    private JComboBox<String> comboBoxStatusVenda = new JComboBox<>();
    private JComboBox<String> comboBoxVendedor = new JComboBox<>();
    private JLabel labCadastroVenda = new JLabel();
    private JLabel labCpfCliente = new JLabel();
    private JLabel labFormaPagamento = new JLabel();
    private JLabel labItens = new JLabel();
    private JLabel labQuantidade = new JLabel();
    private JLabel labStatusVenda = new JLabel();
    private JLabel labVendedor = new JLabel();
    private JScrollPane scroll = new JScrollPane();
    private JtextFieldSomenteNumeros textFieldQuantidade = new JtextFieldSomenteNumeros();
    private static DefaultTableModel modeloItem;
    private static JTable tabelaItens;
    private ControleVenda controleVenda = new ControleVenda();
    
    public void setId(Integer id) {
		this.id = id;
	}

	public Object[] getCadastroVenda() {
		return cadastroVenda;
	}
    
    public JComboBox<String> getComboBoxCliente() {
		return comboBoxCliente;
	}

	public JComboBox<String> getComboBoxFormaPagamento() {
		return comboBoxFormaPagamento;
	}

	public JComboBox<String> getComboBoxItens() {
		return comboBoxItens;
	}

	public JComboBox<String> getComboBoxStatusVenda() {
		return comboBoxStatusVenda;
	}

	public JComboBox<String> getComboBoxVendedor() {
		return comboBoxVendedor;
	}

	public JtextFieldSomenteNumeros getTextFieldQuantidade() {
		return textFieldQuantidade;
	}

	public static DefaultTableModel getModelItem() {
		return modeloItem;
	}

	public CadastroVenda(Frame parent, boolean modal) throws HeadlessException {
    	super(parent, modal);
    	iniciarLayout();
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	reverterAdicionarListaItens();
            }
        });
    }
	
	public static void limpaTabelaProduto() {
    	while (tabelaItens.getModel().getRowCount() > 0)
    		modeloItem.removeRow(0);
    }
    
    List<Item> itens = new ArrayList<Item>();
    private void botaoAdicionarActionPerformed(ActionEvent evt) {
    	try {
    		Integer capturaQuantidade = Integer.parseInt(textFieldQuantidade.getText());
    		String nome = comboBoxItens.getSelectedItem().toString();
        	for(Produto p : ControleProduto.bancoDeDadosProduto){
        		if(p.getNome() == nome) {
        			if(p.getQuantidade() >= capturaQuantidade) {
        				Item item = new Item(capturaQuantidade, p);
        				if(id != null) {
        					for(Venda v : ControleVenda.bancoDeDadosVenda) {
        						if(id == v.getId()) {
        							v.getItens().add(item);
        						}
        					}
        				}
        				else {
        					itens.add(item);
        				}
        				modeloItem.addRow(new Object[]{nome,capturaQuantidade});
        			}
        			else {
            			String error = p.getQuantidade().toString();
            			JOptionPane.showMessageDialog(null,"Existem apenas "+ error +" desse item em estoque!");
            		}
        		}
        	}
    	}
    	catch(Exception e){
    		JOptionPane.showMessageDialog(null,"Digite a quantidade dos itens comprados!");
    	}	
    }
    
    private void botaoRemoverActionPerformed(ActionEvent evt) {
    	int linhaSelecionada = tabelaItens.getSelectedRow();  	
    	DefaultTableModel ModelTabelaItem = (DefaultTableModel) tabelaItens.getModel();
    	if(linhaSelecionada == -1) {
    		JOptionPane.showMessageDialog(this,"Selecione uma linha, por favor.");
    	}
    	else if(id != null) {
    		String produto = ModelTabelaItem.getValueAt(linhaSelecionada, 0).toString();
    		for(Venda v :ControleVenda.bancoDeDadosVenda) {
    			for(Item i : v.getItens()) {
    				if(i.getProduto() == produto) {
    					v.getItens().remove(i);
    					i.devolverProduto();
    					i.reverterProdutosVendidos();
    					break;
    				}
    			}
    		}
    		ModelTabelaItem.removeRow(linhaSelecionada);
    	}
    	else {
    		String produto = ModelTabelaItem.getValueAt(linhaSelecionada, 0).toString();
    		for(Item i : itens) {
    			if(i.getProduto() == produto) {
    				i.devolverProduto();
					i.reverterProdutosVendidos();
    				itens.remove(i);
    				break;
    			}
    		}
    		ModelTabelaItem.removeRow(linhaSelecionada);
    	}
    }
    
    private void reverterAdicionarListaItens() {
    	if(itens == null) {
    		dispose();
    	}
    	else {
    		limpaTabelaProduto();
    		for(Item i : itens) {
    			i.devolverProduto();
    			i.reverterProdutosVendidos();
    		}
    		dispose();
    	}
    }
    
    private Vendedor filtarVendedor() {
    	String nomeVendedor = comboBoxVendedor.getSelectedItem().toString();
    	Vendedor vendedor = null;
    	for(Vendedor v : ControleVendedor.bancoDeDadosVendedor) {
    		if(nomeVendedor == v.getNome()) {
    			vendedor = v;
    		}
    	}
    	return vendedor;
    }
    
    private Cliente filtarCliente() {
    	String nomeCliente = comboBoxCliente.getSelectedItem().toString();
    	Cliente cliente = null;
    	for(Cliente c : ControleCliente.bancoDeDadosCliente) {
    		if(nomeCliente == c.getNome()) {
    			cliente = c;
    		}
    	}
    	return cliente;
    }
    
    private void botaoSalvarActionPerformed(ActionEvent evt) {
    	StatusDaVenda statusConvertido;
    	Pagamento metodoPagamento = Pagamento.valueOf(comboBoxFormaPagamento.getSelectedItem().toString().toUpperCase());
    	try {
    		statusConvertido = StatusDaVenda.valueOf(comboBoxStatusVenda.getSelectedItem().toString().toUpperCase());
    	} catch(Exception e) {
    		statusConvertido = StatusDaVenda.PAGAMENTO_PENDENTE;
    	}
    	
    	
    	if(id != null) {
			for(Venda v : ControleVenda.bancoDeDadosVenda) {
				if(id == v.getId()) {
					v.setVendedor(filtarVendedor());
					v.setCliente(filtarCliente());
					v.setStatus(statusConvertido);
					v.setPagamento(metodoPagamento);
					v.setValor();
					break;
				}
			}
		}
    	else {
    		Venda venda = new Venda(filtarVendedor(),
        			filtarCliente(),
        			itens,
        			statusConvertido,
        			metodoPagamento);
        	
    		for(Vendedor v : ControleVendedor.bancoDeDadosVendedor) {
    			if(v.getNome() == venda.getVendedor()) {
    				v.addVendas(venda);
    				break;
    			}
    		}
        	controleVenda.cadastrar(venda);
        	
        	Integer id = venda.getId();
        	String vendedor = venda.getVendedor();
        	String cliente = venda.getCliente();
        	String data = venda.getData();
        	Double valor = venda.getValor();
        	String situacao = venda.getStatus();
        	String pagamento = venda.getPagamento();
        	Object[] cadastroVenda = new Object[]{id,vendedor,cliente,data,valor,situacao,pagamento};
        	this.cadastroVenda = cadastroVenda;
    	}
    	dispose();
    }
                        
    private void iniciarLayout() {
    	
    	modeloItem = new DefaultTableModel(){
    		private static final long serialVersionUID = 1L;
    		public boolean isCellEditable(int row, int column) {
        		if(column == 2) {
        			return true;
        		}
        		else {
        			return false;
        		}
        	};
        };
     
        tabelaItens = new JTable(modeloItem);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Vendas");

        labVendedor.setFont(new Font("Segoe UI", 1, 17));
        labVendedor.setText("Vendedor");

        labCpfCliente.setFont(new Font("Segoe UI", 1, 17));
        labCpfCliente.setText("Cliente");

        comboBoxCliente.setModel(new DefaultComboBoxModel<>(ControleCliente.listaClientes.toArray(new String[ControleCliente.listaClientes.size()])));

        labStatusVenda.setFont(new Font("Segoe UI", 1, 17));
        labStatusVenda.setText("Situação da venda");

        comboBoxFormaPagamento.setModel(new DefaultComboBoxModel<>(new String[] { "Dinheiro", "Crédito", "Débito", "Pix" }));

        labFormaPagamento.setFont(new Font("Segoe UI", 1, 17));
        labFormaPagamento.setText("Forma de pagamento");

        botaoSalvar.setText("Salvar");
        botaoSalvar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botaoSalvarActionPerformed(evt);
            }
        });

        labCadastroVenda.setFont(new Font("Segoe UI", 1, 24));
        labCadastroVenda.setHorizontalAlignment(SwingConstants.CENTER);
        labCadastroVenda.setText("Cadastro de Vendas");

        labItens.setFont(new Font("Segoe UI", 1, 17));
        labItens.setText("Itens");
        
        modeloItem.addColumn("Produto");
        modeloItem.addColumn("quantidade");
        
        scroll.setViewportView(tabelaItens);

        comboBoxItens.setModel(new DefaultComboBoxModel<>(ControleProduto.itensProduto.toArray(new String[ControleProduto.itensProduto.size()])));

        botaoAdicionar.setText("Adicionar");
        botaoAdicionar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botaoAdicionarActionPerformed(evt);
            }
        });

        labQuantidade.setFont(new Font("Segoe UI", 1, 17));
        labQuantidade.setText("Quant.");

        comboBoxVendedor.setModel(new DefaultComboBoxModel<>(ControleVendedor.listaVendedores.toArray(new String[ControleVendedor.listaVendedores.size()])));

        comboBoxStatusVenda.setModel(new DefaultComboBoxModel<>(new String[] { "Pagamento Pendente", "Processando", "Enviado", "Entregue" }));

        botaoRemover.setText("Remover");
        botaoRemover.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	botaoRemoverActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addComponent(labCadastroVenda, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGap(138, 138, 138))
            .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(labStatusVenda, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
            .addComponent(comboBoxCliente, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
            .addComponent(comboBoxFormaPagamento, GroupLayout.Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
            .addComponent(labFormaPagamento, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
            .addGap(116, 116, 116)))
            .addComponent(comboBoxVendedor, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
            .addComponent(comboBoxStatusVenda, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
            .addComponent(labCpfCliente)
            .addComponent(labVendedor))
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
            .addGroup(layout.createSequentialGroup()
            .addComponent(labItens)
            .addGap(110, 110, 110))
            .addGroup(layout.createSequentialGroup()
            .addComponent(comboBoxItens, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGap(18, 18, 18)))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
            .addComponent(labQuantidade, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(textFieldQuantidade, GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
            .addGap(18, 18, 18)
            .addComponent(botaoAdicionar))
            .addComponent(scroll, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE))
            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(botaoSalvar)
            .addGap(199, 199, 199)
            .addComponent(botaoRemover)
            .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(labCadastroVenda, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addGap(8, 8, 8)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addGap(26, 26, 26)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(comboBoxItens, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
            .addComponent(botaoAdicionar, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
            .addComponent(textFieldQuantidade, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(labItens)
            .addComponent(labQuantidade)
            .addComponent(labVendedor))))
            .addGroup(layout.createSequentialGroup()
            .addGap(44, 44, 44)
            .addComponent(comboBoxVendedor, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
            .addGroup(layout.createSequentialGroup()
            .addComponent(labStatusVenda, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(comboBoxStatusVenda, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(labCpfCliente)
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(comboBoxCliente, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
            .addGap(5, 5, 5)
            .addComponent(labFormaPagamento, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(comboBoxFormaPagamento, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
            .addComponent(scroll, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
            .addComponent(botaoSalvar, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
            .addComponent(botaoRemover, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }                                                                                            
}

