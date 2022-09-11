package interfaces;
/**  
 * 
 * <p>A interface CRUD � responsavel por definir os metodos abstratos que cadastram e deletam os objetos no "banco de dados"</p>  
 * @author Pedro Henrique Rodrigues, Chaydson Ferreira
 * 
 */
public interface CRUD {
	/**
	 * m�todo abstrato que cadastra um objeto no "banco de dados".
	 * 
	 */
	public void cadastrar(Object object);
	/**
	 * m�todo abstrato que deleta um objeto no "banco de dados".
	 * 
	 */
	public void deletar(Object object);
}
