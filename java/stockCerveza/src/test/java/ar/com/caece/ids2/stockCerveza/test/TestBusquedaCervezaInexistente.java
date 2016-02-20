package ar.com.caece.ids2.stockCerveza.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.com.caece.ids2.stockCerveza.exception.NoEncontroEntidadException;
import ar.com.caece.ids2.stockCerveza.manager.CervezaManager;
import ar.com.caece.ids2.stockCerveza.model.Cerveza;

public class TestBusquedaCervezaInexistente {

	@Test
	public void test() {
		try{
			
			CervezaManager cm = new CervezaManager();
			Cerveza cerveza = cm.buscarCerveza(1);
			cerveza.getPrecio();
		
		}catch(NoEncontroEntidadException nex){
			System.out.println(nex.getMessage());
		
		}catch(Exception e){
			fail("Fallo la busqueda de cerveza: "+e.getMessage());
		}
	}

}
