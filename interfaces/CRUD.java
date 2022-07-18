package interfaces;

public interface CRUD {
	public Object cadastrar(Object object);
	public Object exibir(Object object);
	public Boolean editar(Object object);
	public Boolean deletar(Object object);
}
