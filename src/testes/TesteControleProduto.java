package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controle.ControleProduto;
import modelo.Produto;

class TesteControleProduto {

	@Test
	void testeExcluir() {
		
		Produto computador = new Produto("Computador I5 8gb ram",3200.00,100);
        Produto celular = new Produto("Celular",1300.90,100);
        
        ControleProduto controleProduto = new ControleProduto();
        ControleProduto.bancoDeDadosProduto.add(computador);
        ControleProduto.bancoDeDadosProduto.add(celular);
        
        controleProduto.deletar(computador);
        
        boolean resultado = true;
        
        
        for(Produto p : ControleProduto.bancoDeDadosProduto) {
        	if(p.getId() == 1) resultado = false;
        }
        
        assertEquals(true, resultado);
        
        
		
	}

}
