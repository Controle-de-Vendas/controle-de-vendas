package testes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import enumera��es.Departamento;
import enumera��es.Pagamento;
import enumera��es.StatusDaVenda;
import modelo.Cliente;
import modelo.Item;
import modelo.Produto;
import modelo.Venda;
import modelo.Vendedor;

class TesteVendedor {

	@org.junit.jupiter.api.Test
	void test() {
		Vendedor vanessa = new Vendedor("Vanessa Carvalho","887.877.970-97",Departamento.BELEZA);
		
		Cliente Caio = new Cliente("Caio Sampaio", "035.259.180-38","Rua J�o Paulo, Quadra 09, Lote 15, Vila Maira");
		
		Produto celular = new Produto("Celular",1300.90,100);
	
		List<Item> lista = new ArrayList<Item>();
		
    	Item item = new Item(2,celular);
    	
    	lista.add(item);
    	
    	StatusDaVenda status = StatusDaVenda.valueOf("ENVIADO");
    	
    	Pagamento pagamento = Pagamento.valueOf("PIX");
    	
		Venda v = new Venda(vanessa, Caio, lista, status, pagamento);
		
		vanessa.addVendas(v);
		
		Double valorEsperado = 1060.18;
		
		assertEquals(valorEsperado, vanessa.calcularSalario());
	}

}
