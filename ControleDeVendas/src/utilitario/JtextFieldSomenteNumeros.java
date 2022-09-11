package utilitario;

import java.awt.event.KeyEvent;

import javax.swing.JTextField;
/**  
 * 
 * <p>A classe JtextFieldSomenteNumeros serve para restringir o usuario a digitar apenas números em um campo.</p>  
 * @author Pedro Henrique Rodrigues, Chaydson Ferreira
 * 
 */
public final class JtextFieldSomenteNumeros extends JTextField {
	private static final long serialVersionUID = 1L;
	private int maximoCaracteres = -1;

	public JtextFieldSomenteNumeros() {
		super();
		addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent evt) {
				jTextFieldKeyTyped(evt);
			}
		});
	}

	public  JtextFieldSomenteNumeros(int maximo) {
	    super();
	    setMaximoCaracteres(maximo);
	        addKeyListener(new java.awt.event.KeyAdapter() {
	            @Override
	    public void keyTyped(java.awt.event.KeyEvent evt) {
	        jTextFieldKeyTyped(evt);}});
	    }
		
		/**
		 * Método que restringe o usuário digitar apenas números em um campo.
		 * @param evt é o evento do teclado.
		 */
		private void jTextFieldKeyTyped(KeyEvent evt) {

			String caracteres="0987654321";
			if(!caracteres.contains(evt.getKeyChar()+"")){
				evt.consume();
			}
			if((getText().length()>=getMaximoCaracteres())&&(getMaximoCaracteres()!=-1)){
				evt.consume();
				setText(getText().substring(0,getMaximoCaracteres()));

			}
			
		}

	public int getMaximoCaracteres() {
		return maximoCaracteres;
	}

	public void setMaximoCaracteres(int maximoCaracteres) {
		this.maximoCaracteres = maximoCaracteres;
	}
}
