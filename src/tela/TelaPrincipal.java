package tela;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import controle.ControleCliente;
import controle.ControleGerente;
import controle.ControleProduto;
import controle.ControleVenda;
import controle.ControleVendedor;
import enumeracao.Departamento;
import enumeracao.Pagamento;
import enumeracao.StatusDaVenda;
import modelo.Cliente;
import modelo.Funcionario;
import modelo.Gerente;
import modelo.Item;
import modelo.Produto;
import modelo.Venda;
import modelo.Vendedor;
/**  
 * 
 * <p>A classe TelaPrincipal cria um "JFrame" (janela) que serve para chamar outras janelas, visualizar tabelas, e modificar as mesmas bem como o "banco de dados".</p>  
 * @author Pedro Henrique Rodrigues, Chaydson Ferreira
 * 
 */
public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
    private JMenu botaoCadastrar = new JMenu();
    private JButton botaoEditarProdutos = new JButton();
    private JButton botaoEditarVendas = new JButton();
    private JButton botaoExcluirProdutos = new JButton();
    private JButton botaoExcluirVendas = new JButton();
    private JButton botaoNovoProdutos = new JButton();
    private JButton botaoNovoVendas = new JButton();
    private JButton botaoPesquisarProdutos = new JButton();
    private JButton botaoPesquisarVendas = new JButton();
    private JMenuBar menu = new JMenuBar();
    private JMenuItem menuItemCliente = new JMenuItem();
    private JMenuItem menuItemGerente = new JMenuItem();
    private JMenuItem menuItemVendedor = new JMenuItem();
    private JTabbedPane painelFuncionarios = new JTabbedPane();
    private JPanel painelProdutos = new JPanel();
    private JPanel painelVendas = new JPanel();
    private JTabbedPane painelVendasProdutos = new JTabbedPane();
    private JScrollPane scrollFuncionarios = new JScrollPane();
    private JScrollPane scrollProdutos = new JScrollPane();
    private JScrollPane scrollVendas = new JScrollPane();
    private JTextField textFieldPesquisarProdutos = new JTextField();
    private JTextField textFieldPesquisarVendas = new JTextField();
    static DecimalFormat df = new DecimalFormat("#,###.##", new DecimalFormatSymbols(Locale.GERMAN));
    private static DefaultTableModel modeloProduto = new DefaultTableModel(){
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row, int column) {
    		if(column == 4) {
    			return true;
    		}
    		else {
    			return false;
    		}
    	};
    };
    
    
    static DefaultTableModel modeloVenda;
    
    static DefaultTableModel modeloFuncionario = new DefaultTableModel() {
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row, int column) {
    		if(column == 5) {
    			return true;
    		}
    		else {
    			return false;
    		}
    	};
    };
    private static JTable tabelaFuncionarios;
    private static JTable tabelaProdutos;
    private static JTable tabelaVendas;
    
    ControleProduto controleProduto = new ControleProduto();
    ControleVenda controleVenda = new ControleVenda();
    
    public TelaPrincipal() {
    	iniciarLayout();
        this.setLocationRelativeTo(null);
        atualizarTabelaProduto();
        atualizarTabelaVenda();
        atualizarTabelaFuncionario();
    }
    
    
    /**
	 * Método que adiciona linhas da tabela de produtos.
	 * 
	 */
    public static void atualizarTabelaProduto() {
    	for(Produto p : ControleProduto.bancoDeDadosProduto) {
            Integer id = p.getId();
            String nome = p.getNome();
            Double preco = p.getPreco();
            Integer quantidadeVendida = p.getQuantidadeTotalVendida();
            Integer quantidade = p.getQuantidade();
            modeloProduto.addRow(new Object[]{id, nome, df.format(preco), quantidadeVendida, quantidade});
             	
        }
    }
    
    /**
	 * Método que exclui todas as linhas da tabela de produtos.
	 * 
	 */
    public static void limpaTabelaProduto() {
    	while (tabelaProdutos.getModel().getRowCount() > 0)
    		modeloProduto.removeRow(0);
    }
    
    /**
	 * Método que adiciona linhas da tabela de funcionários.
	 * 
	 */
    public static void atualizarTabelaFuncionario() {
    	for(Funcionario f : ControleGerente.bancoDeDadosFuncionario) {
            String nome = f.getNome();
            String cpf = f.getCpf();
            String departamento = f.getDepartamento().toString().toLowerCase();
            Double salario = f.calcularSalario();
            modeloFuncionario.addRow(new Object[]{nome, cpf, departamento, df.format(salario)});
             	
        }
    }
    
    /**
	 * Método que exclui todas as linhas da tabela de funcionários.
	 * 
	 */
    public static void limpaTabelaFuncionario() {
    	while (tabelaFuncionarios.getModel().getRowCount() > 0)
    		modeloFuncionario.removeRow(0);
    }
    
    /**
	 * Método que adiciona linhas da tabela de vendas.
	 * 
	 */
    public static void atualizarTabelaVenda() {
    		for(Venda v : ControleVenda.bancoDeDadosVenda) {
            	Integer id = v.getId();
            	String vendedor = v.getVendedor();
            	String cliente = v.getCliente();
            	String data = v.getData();
            	Double valor = v.getValor();
            	String status = v.getStatus();
            	String pagamento = v.getPagamento();
            	modeloVenda.addRow(new Object[]{id, vendedor, cliente, data, df.format(valor), status, pagamento});
            }
    }
    
    /**
	 * Método que exclui todas as linhas da tabela de vendas.
	 * 
	 */
    public static void limpaTabelaVenda() {
    	while (tabelaVendas.getModel().getRowCount() > 0)
    		modeloVenda.removeRow(0);
    }
    
    /**
	 * Filtra as vendas pelo nome do vendedor, cliente, data, situação da venda e método de pagamento.
	 * 
	 */
    private void botaoPesquisarVendas(ActionEvent evt) {                                                     
    	limpaTabelaVenda();
    	String pesquisa = textFieldPesquisarVendas.getText().toUpperCase();
    	for(Venda v : ControleVenda.bancoDeDadosVenda) {
    		if(v.getVendedor().toUpperCase().contains(pesquisa)) {
    			Integer id = v.getId();
            	String vendedor = v.getVendedor();
            	String cliente = v.getCliente();
            	String data = v.getData();
            	Double valor = v.getValor();
            	String status = v.getStatus();
            	String pagamento = v.getPagamento();
            	modeloVenda.addRow(new Object[]{id, vendedor, cliente, data, valor, status, pagamento});
    		}
    		else if(v.getCliente().toUpperCase().contains(pesquisa)) {
    			Integer id = v.getId();
            	String vendedor = v.getVendedor();
            	String cliente = v.getCliente();
            	String data = v.getData();
            	Double valor = v.getValor();
            	String status = v.getStatus();
            	String pagamento = v.getPagamento();
            	modeloVenda.addRow(new Object[]{id, vendedor, cliente, data, valor, status, pagamento});
    		}
    		else if(v.getData().contains(pesquisa)) {
    			Integer id = v.getId();
            	String vendedor = v.getVendedor();
            	String cliente = v.getCliente();
            	String data = v.getData();
            	Double valor = v.getValor();
            	String status = v.getStatus();
            	String pagamento = v.getPagamento();
            	modeloVenda.addRow(new Object[]{id, vendedor, cliente, data, valor, status, pagamento});
    		}
    		else if(v.getStatus().toUpperCase().contains(pesquisa)) {
    			Integer id = v.getId();
            	String vendedor = v.getVendedor();
            	String cliente = v.getCliente();
            	String data = v.getData();
            	Double valor = v.getValor();
            	String status = v.getStatus();
            	String pagamento = v.getPagamento();
            	modeloVenda.addRow(new Object[]{id, vendedor, cliente, data, valor, status, pagamento});
    		}
    		else if(v.getPagamento().toUpperCase().contains(pesquisa)) {
    			Integer id = v.getId();
            	String vendedor = v.getVendedor();
            	String cliente = v.getCliente();
            	String data = v.getData();
            	Double valor = v.getValor();
            	String status = v.getStatus();
            	String pagamento = v.getPagamento();
            	modeloVenda.addRow(new Object[]{id, vendedor, cliente, data, valor, status, pagamento});
    		}		
    	}
    }
    
    /**
	 * Filtra os produtos pelo nome.
	 * 
	 */
    private void botaoPesquisarProdutos(ActionEvent evt) {                                                       
    	limpaTabelaProduto();
    	String pesquisa = textFieldPesquisarProdutos.getText();
    	for(Produto p : ControleProduto.bancoDeDadosProduto) {
    		if(p.getNome().toUpperCase().contains(pesquisa.toUpperCase())) {
    			Integer id = p.getId();
                String nome = p.getNome();
                Double preco = p.getPreco();
                Integer quantidadeVendida = p.getQuantidadeTotalVendida();
                Integer quantidade = p.getQuantidade();
                modeloProduto.addRow(new Object[]{id, nome, preco, quantidadeVendida, quantidade});
    		}
    			
    	}
    }
    
    /**
	 * Método que exclui uma venda por meio da seleção de uma linha da tabela vendas.
	 * 
	 */
    private void botaoExcluirProdutos(ActionEvent evt) {
    	int linhaSelecionada = tabelaProdutos.getSelectedRow();  	
    	DefaultTableModel ModelTabelaProduto = (DefaultTableModel) tabelaProdutos.getModel();
    	
    	if(linhaSelecionada == -1) {
    		JOptionPane.showMessageDialog(this,"Selecione uma linha, por favor.");
    	}
    	else {
    		int id = Integer.parseInt(ModelTabelaProduto.getValueAt(linhaSelecionada, 0).toString());
        	ModelTabelaProduto.removeRow(linhaSelecionada);
        	tabelaProdutos.setModel(ModelTabelaProduto);
      	
        	for(Produto p : ControleProduto.bancoDeDadosProduto) {
    			if(p.getId() == id) {
    				controleProduto.deletar(p);
    				controleProduto.limparItensProduto();
    				controleProduto.atualizarItensProduto();
    				break;
    			}
    		}
    	}
    }
    
    /**
	 * Método que exclui um produto por meio da seleção de uma linha da tabela produtos.
	 * 
	 */
    private void botaoExcluirVendas(ActionEvent evt) {                                                   
    	int linhaSelecionada = tabelaVendas.getSelectedRow();  	
    	DefaultTableModel ModelTabelaVenda = (DefaultTableModel) tabelaVendas.getModel();
    	
    	if(linhaSelecionada == -1) {
    		JOptionPane.showMessageDialog(this,"Selecione uma linha, por favor.");
    	}
    	else {
    		int id = Integer.parseInt(ModelTabelaVenda.getValueAt(linhaSelecionada, 0).toString());
        	ModelTabelaVenda.removeRow(linhaSelecionada);
        	tabelaVendas.setModel(ModelTabelaVenda);
        	
        	for(Venda v : ControleVenda.bancoDeDadosVenda) {
        		if(v.getId() == id) {
        			v.zerarListaItens();
        			limpaTabelaProduto();
        			atualizarTabelaProduto();
        			controleVenda.deletar(v);
        			for(Vendedor r : ControleVendedor.bancoDeDadosVendedor) {
        				if(v.getVendedor() == r.getNome()) {
        					r.getVendas().remove(v);
        					break;
        				}
        			}
        			break;
        		}
        	}
    	}
    	limpaTabelaFuncionario();
    	atualizarTabelaFuncionario();
    }
    
    /**
	 * Método que abre a janela de cadastro de cliente.
	 * 
	 */
    private void menuItemCliente(ActionEvent evt) {                                                
    	CadastroCliente cadastroCliente = new CadastroCliente(this, true);
    	cadastroCliente.setVisible(true);
    }                                               
    
    /**
	 * Método que abre a janela de cadastro de vendedor e atualiza a tabela funcionários.
	 * 
	 */
    private void menuItemVendedor(ActionEvent evt) {                                                 
    	CadastroVendedor cadastroVendedor = new CadastroVendedor(this, true);
    	cadastroVendedor.setVisible(true);
    	limpaTabelaFuncionario();
    	atualizarTabelaFuncionario();
    }                                                
    
    /**
	 * Método que abre a janela de cadastro de gerente e atualiza a tabela funcionários.
	 * 
	 */
    private void menuItemGerente(ActionEvent evt) {                                                
    	CadastroGerente cadastroGerente = new CadastroGerente(this, true);
    	cadastroGerente.setVisible(true);
    	limpaTabelaFuncionario();
    	atualizarTabelaFuncionario();
    }                                               
    
    /**
	 * Método que abre a janela de cadastro de vendas, adiciona uma linha na tabela vendas e atualiza as tabelas funcionários e produtos.
	 * 
	 */
    private void botaoNovoVendas(ActionEvent evt) {                                                
    	CadastroVenda cadastroVenda = new CadastroVenda(this, true);
    	cadastroVenda.setVisible(true);
    	Object[] cadastroDialog = cadastroVenda.getCadastroVenda();
    	if(cadastroDialog != null) {
    		modeloVenda.addRow(cadastroDialog);
    	}
    	limpaTabelaProduto();
    	atualizarTabelaProduto();
    	limpaTabelaFuncionario();
    	atualizarTabelaFuncionario();
    }                                               
    
    /**
	 * Método que abre a janela de cadastro de produtos, adiciona uma linha na tabela produtos.
	 * 
	 */
    private void botaoNovoProdutos(ActionEvent evt) {                                                  
    	CadastroProduto cadastroProduto = new CadastroProduto(this,true);
    	cadastroProduto.setVisible(true);
        Object[] cadastroDialog = cadastroProduto.getCadastro();
        if(cadastroDialog != null) {
        	modeloProduto.addRow(cadastroDialog);
        }
    }
    
    /**
	 * Método que abre a janela de cadastro de produtos, porém faz a edição de um produto selecionado e atualiza a tabela produtos.
	 * 
	 */
    private void botaoEditarProdutos(ActionEvent evt) {                                                    
    	int linhaSelecionada = tabelaProdutos.getSelectedRow();  	
    	DefaultTableModel ModelTabelaProduto = (DefaultTableModel) tabelaProdutos.getModel();
    	if(linhaSelecionada == -1) {
    		JOptionPane.showMessageDialog(this,"Selecione uma linha, por favor.");
    	}
    	else {
    		Integer id = Integer.parseInt(ModelTabelaProduto.getValueAt(linhaSelecionada, 0).toString());
	    	for(Produto p : ControleProduto.bancoDeDadosProduto) {
	    		if(id == p.getId()) {
	    			CadastroProduto cadastroProduto = new CadastroProduto(this, true);
	    			cadastroProduto.getTextFieldNome().setText(p.getNome());
	    			cadastroProduto.getTextFieldPreco().setText(p.getPreco().toString());
	    			cadastroProduto.getTextFieldQuantidade().setText(p.getQuantidade().toString());
	    			cadastroProduto.setId(id);
	    			cadastroProduto.setVisible(true);
	    			limpaTabelaProduto();
	    			atualizarTabelaProduto();
	    			controleProduto.limparItensProduto();
					controleProduto.atualizarItensProduto();
	    			break;
	    		}
	    	}
    	}
    }                                                                                                             
    
    /**
	 * Método que abre a janela de cadastro de vendas, porém faz a edição de uma venda selecionada e atualiza a tabela vendas e produtos.
	 * 
	 */
    private void botaoEditarVendas(ActionEvent evt) {                                                  
    	int linhaSelecionada = tabelaVendas.getSelectedRow();  	
    	DefaultTableModel ModelTabelaVenda = (DefaultTableModel) tabelaVendas.getModel();
    	if(linhaSelecionada == -1) {
    		JOptionPane.showMessageDialog(this,"Selecione uma linha, por favor.");
    	}
    	else {
    		Integer id = Integer.parseInt(ModelTabelaVenda.getValueAt(linhaSelecionada, 0).toString());
	    	for(Venda v : ControleVenda.bancoDeDadosVenda) {
	    		if(id == v.getId()) {
	    			CadastroVenda cadastroVenda = new CadastroVenda(this, true);
	    			cadastroVenda.getComboBoxCliente().setSelectedItem(v.getCliente());
	    			cadastroVenda.getComboBoxFormaPagamento().setSelectedItem(v.getPagamento());
	    			cadastroVenda.getComboBoxStatusVenda().setSelectedItem(v.getStatus());
	    			cadastroVenda.getComboBoxVendedor().setSelectedItem(v.getVendedor());
	    			for(Item i : v.getItens()) {
	    				i.getProduto();
	    				i.getQuantidadeComprada();
	    				CadastroVenda.getModelItem().addRow(new Object[]{i.getProduto(),i.getQuantidadeComprada()});
	    			}
	    			cadastroVenda.setId(id);
	    			cadastroVenda.setVisible(true);
	    			if(cadastroVenda.getVerificar() == true) {
	    				botaoExcluirVendas(evt);
	    			}
	    			limpaTabelaVenda();
	    			atualizarTabelaVenda();
	    			limpaTabelaProduto();
	    			atualizarTabelaProduto();
	    			break;
	    		}
	    	}
    	}
    	limpaTabelaFuncionario();
    	atualizarTabelaFuncionario();
    }
    
    /**
	 * Método que abre a janela vizualizar itens de acordo com a linha selecionada.
	 * 
	 */
    private void tabelaVendasMouse(MouseEvent evt) {
    	int linhaSelecionada = tabelaVendas.getSelectedRow();  	
    	DefaultTableModel ModelTabelaVenda = (DefaultTableModel) tabelaVendas.getModel();
    	Integer id = Integer.parseInt(ModelTabelaVenda.getValueAt(linhaSelecionada, 0).toString());
    	if (evt.getClickCount() == 2) {
    		VisualizarItens vizualizarItens = new VisualizarItens(this, true);
    		vizualizarItens.setId(id);
    		vizualizarItens.preencherTabela();
    		vizualizarItens.setVisible(true);
    	}
    }
    
    /**
	 * Método que inicia os componentes, configurações e dependências do layout.
	 * 
	 */
    private void iniciarLayout() {
    	
    	modeloVenda = new DefaultTableModel() {
    		private static final long serialVersionUID = 1L;
    		public boolean isCellEditable(int row, int column) {
        		if(column == 8) {
        			return true;
        		}
        		else {
        			return false;
        		}
        	};
        };

        tabelaVendas = new JTable(modeloVenda);   
        tabelaProdutos = new JTable(modeloProduto);
        tabelaFuncionarios = new JTable(modeloFuncionario);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Controle de Vendas");
        setBackground(new Color(0, 255, 255));
        setMinimumSize(new Dimension(500, 300));

        tabelaVendas.setBackground(new Color(242, 242, 242));
        modeloVenda.addColumn("ID");
        modeloVenda.addColumn("Vendedor");
        modeloVenda.addColumn("Cliente");
        modeloVenda.addColumn("Data");
        modeloVenda.addColumn("Valor");
        modeloVenda.addColumn("Situação da Venda");
        modeloVenda.addColumn("Pagamento");
        
        tabelaVendas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
            	tabelaVendasMouse(evt);
            }
        });
        scrollVendas.setViewportView(tabelaVendas);

        botaoEditarVendas.setText("Editar");
        botaoEditarVendas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoEditarVendas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	botaoEditarVendas(evt);
            }
        });

        botaoExcluirVendas.setText("Excluir");
        botaoExcluirVendas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoExcluirVendas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	botaoExcluirVendas(evt);
            }
        });

        botaoNovoVendas.setText("Novo");
        botaoNovoVendas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoNovoVendas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	botaoNovoVendas(evt);
            }
        });
        
        botaoPesquisarVendas.setBackground(new Color(242, 242, 242));
        botaoPesquisarVendas.setForeground(new Color(255, 255, 255));
        botaoPesquisarVendas.setIcon(new ImageIcon(getClass().getResource("/imagem/pesquisar.png")));
        botaoPesquisarVendas.setBorder(null);
        botaoPesquisarVendas.setBorderPainted(false);
        botaoPesquisarVendas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoPesquisarVendas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	botaoPesquisarVendas(evt);
            }
        });

        GroupLayout painelVendasLayout = new GroupLayout(painelVendas);
        painelVendas.setLayout(painelVendasLayout);
        painelVendasLayout.setHorizontalGroup(
            painelVendasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(painelVendasLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(painelVendasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(scrollVendas, GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
            .addGroup(painelVendasLayout.createSequentialGroup()
            .addComponent(botaoEditarVendas)
            .addGap(18, 18, 18)
            .addComponent(botaoExcluirVendas)
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(botaoNovoVendas)))
            .addContainerGap())
            .addGroup(GroupLayout.Alignment.TRAILING, painelVendasLayout.createSequentialGroup()
            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(textFieldPesquisarVendas, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(botaoPesquisarVendas)
            .addGap(261, 261, 261))
        );
        painelVendasLayout.setVerticalGroup(
            painelVendasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, painelVendasLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(painelVendasLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
            .addComponent(botaoPesquisarVendas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(textFieldPesquisarVendas))
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
            .addGroup(painelVendasLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(botaoEditarVendas)
            .addComponent(botaoExcluirVendas)
            .addComponent(botaoNovoVendas))
            .addGap(7, 7, 7)
            .addComponent(scrollVendas, GroupLayout.PREFERRED_SIZE, 373, GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
        );

        painelVendasProdutos.addTab("Vendas", painelVendas);

        tabelaProdutos.setBackground(new Color(242, 242, 242));
        
        modeloProduto.addColumn("Código");
        modeloProduto.addColumn("Nome");
        modeloProduto.addColumn("Preço");
        modeloProduto.addColumn("Quantidade Vendida");
        modeloProduto.addColumn("Quantidade em Estoque");
        
        scrollProdutos.setViewportView(tabelaProdutos);

        botaoEditarProdutos.setText("Editar");
        botaoEditarProdutos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoEditarProdutos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	botaoEditarProdutos(evt);
            }
        });

        botaoExcluirProdutos.setText("Excluir");
        botaoExcluirProdutos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoExcluirProdutos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	botaoExcluirProdutos(evt);
            }
        });

        botaoNovoProdutos.setText("Novo");
        botaoNovoProdutos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoNovoProdutos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	botaoNovoProdutos(evt);
            }
        });

        botaoPesquisarProdutos.setBackground(new Color(242, 242, 242));
        botaoPesquisarProdutos.setIcon(new ImageIcon(getClass().getResource("/imagem/pesquisar.png")));
        botaoPesquisarProdutos.setBorder(null);
        botaoPesquisarProdutos.setBorderPainted(false);
        botaoPesquisarProdutos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoPesquisarProdutos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	botaoPesquisarProdutos(evt);
            }
        });

        GroupLayout painelProdutosLayout = new GroupLayout(painelProdutos);
        painelProdutos.setLayout(painelProdutosLayout);
        painelProdutosLayout.setHorizontalGroup(
            painelProdutosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(painelProdutosLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(painelProdutosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, painelProdutosLayout.createSequentialGroup()
            .addComponent(scrollProdutos, GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
            .addContainerGap())
            .addGroup(GroupLayout.Alignment.TRAILING, painelProdutosLayout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(textFieldPesquisarProdutos, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(botaoPesquisarProdutos)
            .addGap(260, 260, 260))
            .addGroup(GroupLayout.Alignment.TRAILING, painelProdutosLayout.createSequentialGroup()
            .addComponent(botaoEditarProdutos)
            .addGap(21, 21, 21)
            .addComponent(botaoExcluirProdutos)
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(botaoNovoProdutos)
            .addContainerGap())))
        );
        painelProdutosLayout.setVerticalGroup(
            painelProdutosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(painelProdutosLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(painelProdutosLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
            .addComponent(botaoPesquisarProdutos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(textFieldPesquisarProdutos))
            .addGap(28, 28, 28)
            .addGroup(painelProdutosLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(botaoNovoProdutos)
            .addComponent(botaoExcluirProdutos)
            .addComponent(botaoEditarProdutos))
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(scrollProdutos, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE))
        );

        painelVendasProdutos.addTab("Produtos", painelProdutos);
        
        tabelaFuncionarios.setBackground(new Color(242, 242, 242));
        
        modeloFuncionario.addColumn("Nome");
        modeloFuncionario.addColumn("CPF");
        modeloFuncionario.addColumn("Departamento");
        modeloFuncionario.addColumn("Salário");
        
        scrollFuncionarios.setViewportView(tabelaFuncionarios);

        painelFuncionarios.addTab("Lista de funcionários", scrollFuncionarios);
        painelFuncionarios.setBackground(new Color(242, 242, 242));

        painelVendasProdutos.addTab("Funcionários", painelFuncionarios);

        botaoCadastrar.setBorder(BorderFactory.createEtchedBorder());
        botaoCadastrar.setText("Cadastrar ");
        botaoCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
       
        menuItemCliente.setText("Cliente");
        
        menuItemCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	menuItemCliente(evt);
            }
        });
        botaoCadastrar.add(menuItemCliente);

        menuItemVendedor.setText("Vendedor");
        menuItemVendedor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	menuItemVendedor(evt);
            }
        });
        botaoCadastrar.add(menuItemVendedor);

        menuItemGerente.setText("Gerente");
        menuItemGerente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	menuItemGerente(evt);
            }
        });
        botaoCadastrar.add(menuItemGerente);

        menu.add(botaoCadastrar);

        setJMenuBar(menu);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(painelVendasProdutos)
            .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(painelVendasProdutos, GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
            .addContainerGap())
        );

        pack();
    }                                                                                                                                                                          
    
    /**
	 * Método main que é responsável por iniciar o sistema .
	 * 
	 */
    public static void main(String args[]) {
    	try {
            for (UIManager.LookAndFeelInfo template : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(template.getName())) {
                    UIManager.setLookAndFeel(template.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null,"Erro: " + e.getMessage());
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
        
        ControleVendedor controleVendedor = new ControleVendedor();
        ControleProduto controleProduto = new ControleProduto();
        ControleCliente controleCliente = new ControleCliente();
        ControleGerente controleGerente = new ControleGerente();
        
        Produto computador = new Produto("Computador I5 8gb ram",3200.00,100);
        Produto celular = new Produto("Celular",1300.90,100);
        Produto buscopan = new Produto("Buscopan",16.90,100);
        Produto dipirona = new Produto("Dipirona",15.90,100);
        Produto fita = new Produto("Fita Métrica",9.90,100);
        Produto vassoura = new Produto("Vassoura",29.90,100);
        Produto bola = new Produto("Bola de Futebol",99.90,100);
        Produto chuteira = new Produto("Chuteira",250.90,100);
        Produto kit = new Produto("Kit de Maquiagem para meninas iniciantes",220.90,100);
        Produto base = new Produto("Boca Rosa Base Mate",50.90,100);

        ControleProduto.bancoDeDadosProduto.add(computador);
        ControleProduto.bancoDeDadosProduto.add(celular);
        ControleProduto.bancoDeDadosProduto.add(buscopan);
        ControleProduto.bancoDeDadosProduto.add(dipirona);
        ControleProduto.bancoDeDadosProduto.add(fita);
        ControleProduto.bancoDeDadosProduto.add(vassoura);
        ControleProduto.bancoDeDadosProduto.add(bola);
        ControleProduto.bancoDeDadosProduto.add(chuteira);
        ControleProduto.bancoDeDadosProduto.add(kit);
        ControleProduto.bancoDeDadosProduto.add(base);

        controleProduto.atualizarItensProduto();
        
        Vendedor vanessa = new Vendedor("Vanessa Carvalho","887.877.970-97",Departamento.BELEZA);
        Vendedor glauco = new Vendedor("Glauco Silva","492.557.240-70",Departamento.ESPORTE);
        Vendedor maria = new Vendedor("Maria Antonieta","110.585.640-21",Departamento.ESPORTE);
        Vendedor jorge = new Vendedor("Jorge Sagaz","607.363.210-04",Departamento.SAÚDE);
        Vendedor pedro = new Vendedor("Pedro de Melo","451.210.450-00",Departamento.SAÚDE);
        Vendedor claudia = new Vendedor("Claudia Guimarões","825.240.790-07",Departamento.TECNOLOGIA);
        Vendedor ana = new Vendedor("Ana Luiza Ferreira","251.300.680-17",Departamento.TECNOLOGIA);
        Vendedor lucas = new Vendedor("Lucas da Silva","293.578.470-73",Departamento.UTILIDADES);
        Vendedor amanda = new Vendedor("Amanda Soares","802.930.670-90",Departamento.UTILIDADES);
        
        ControleVendedor.bancoDeDadosVendedor.add(vanessa);
        ControleVendedor.bancoDeDadosVendedor.add(glauco);
        ControleVendedor.bancoDeDadosVendedor.add(maria);
        ControleVendedor.bancoDeDadosVendedor.add(jorge);
        ControleVendedor.bancoDeDadosVendedor.add(pedro);
        ControleVendedor.bancoDeDadosVendedor.add(claudia);
        ControleVendedor.bancoDeDadosVendedor.add(ana);
        ControleVendedor.bancoDeDadosVendedor.add(lucas);
        ControleVendedor.bancoDeDadosVendedor.add(amanda);
    	
    	controleVendedor.atualizarListaVendedores();
    	
    	Gerente anderson = new Gerente("Anderson Senna","382.171.510-32",Departamento.BELEZA);
    	Gerente alvaro = new Gerente("Alvaro Marques","831.606.790-04",Departamento.ESPORTE);
    	Gerente chaydson = new Gerente("Chaydson Santos","105.477.840-07",Departamento.SAÚDE);
    	Gerente Fabiana = new Gerente("Fabiana Castro","587.088.660-07",Departamento.TECNOLOGIA);
    	
    	ControleGerente.bancoDeDadosGerente.add(anderson);
    	ControleGerente.bancoDeDadosGerente.add(alvaro);
    	ControleGerente.bancoDeDadosGerente.add(chaydson);
    	ControleGerente.bancoDeDadosGerente.add(Fabiana);
    	
    	ControleGerente.bancoDeDadosFuncionario.add(anderson);
    	ControleGerente.bancoDeDadosFuncionario.add(alvaro);
    	ControleGerente.bancoDeDadosFuncionario.add(chaydson);
    	ControleGerente.bancoDeDadosFuncionario.add(Fabiana);
    	ControleGerente.bancoDeDadosFuncionario.add(vanessa);
    	ControleGerente.bancoDeDadosFuncionario.add(glauco);
    	ControleGerente.bancoDeDadosFuncionario.add(maria);
    	ControleGerente.bancoDeDadosFuncionario.add(jorge);
    	ControleGerente.bancoDeDadosFuncionario.add(pedro);
    	ControleGerente.bancoDeDadosFuncionario.add(claudia);
    	ControleGerente.bancoDeDadosFuncionario.add(ana);
    	ControleGerente.bancoDeDadosFuncionario.add(lucas);
    	ControleGerente.bancoDeDadosFuncionario.add(amanda);
    	
    	Cliente Caio = new Cliente("Caio Sampaio", "035.259.180-38","Rua Jão Paulo, Quadra 09, Lote 15, Vila Maira");
    	Cliente kadef = new Cliente("Kadef Rocha", "931.003.440-81","Rua Jão Paulo, Quadra 03, Lote 09, Vila Mercedes");
    	
    	ControleCliente.bancoDeDadosCliente.add(Caio);
    	ControleCliente.bancoDeDadosCliente.add(kadef);
    	
    	controleCliente.atuzalizarListaClientes();
    	
    	List<Item> lista = new ArrayList<Item>();
    	Item item = new Item(2,celular);
    	lista.add(item);
    	StatusDaVenda status = StatusDaVenda.valueOf("ENVIADO");
    	Pagamento pagamento = Pagamento.valueOf("PIX");
    	
    	ControleVenda.bancoDeDadosVenda.add(new Venda(vanessa, Caio, lista, status, pagamento));
    	
    	controleVendedor.atualizarSalarioVendedor();
    	controleGerente.atualizarVendedoresAssociados();
    	
    }                
}

