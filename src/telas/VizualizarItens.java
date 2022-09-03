package telas;

import java.awt.Frame;

import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import controle.ControleVenda;
import modelo.Item;
import modelo.Venda;

public class VizualizarItens extends JDialog {

	private static final long serialVersionUID = 1L;
	private Integer id = null;
	private JScrollPane scroll = new JScrollPane();
	private static DefaultTableModel modeloVizualizar;
    private JTable tabelaItens;

    public VizualizarItens(Frame parent, boolean modal) {
        super(parent, modal);
        iniciarlayout();
        this.setLocationRelativeTo(null);
    }
    
    public void setId(Integer id) {
		this.id = id;
	}
    
    public void preencherTabela() {
    	for(Venda v : ControleVenda.bancoDeDadosVenda) {
    		if(id == v.getId()) {
    			for(Item i : v.getItens()){
    				modeloVizualizar.addRow(new Object[]{i.getProduto(),i.getQuantidadeComprada()});
    			}
    		}
    		else if(id == null) {
    			System.out.println("n entrou");
    		}
    	}
    }
    
    private void iniciarlayout() {
    	
    	modeloVizualizar = new DefaultTableModel(){
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
        
        tabelaItens = new JTable(modeloVizualizar);
        
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        modeloVizualizar.addColumn("Produto");
        modeloVizualizar.addColumn("Quantidade");
        scroll.setViewportView(tabelaItens);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 396, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(scroll, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
            .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(scroll, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
            .addContainerGap()))
        );

        pack();
    }
}

