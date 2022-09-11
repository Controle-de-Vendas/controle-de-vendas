package teste;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controle.ControleCliente;
import modelo.Cliente;

class TesteCliente {

	@Test
	void testCadastrar() {
		
		Cliente Caio = new Cliente("Caio Sampaio", "035.259.180-38","Rua Jão Paulo, Quadra 09, Lote 15, Vila Maira");
		ControleCliente controleCliente = new ControleCliente();
		controleCliente.cadastrar(Caio);
		
		boolean resultado = false;
        
        for(Cliente c : ControleCliente.bancoDeDadosCliente) {
        	if(c.getId() == 1) resultado = true;
        }
        
        assertEquals(true, resultado);
	}

}
