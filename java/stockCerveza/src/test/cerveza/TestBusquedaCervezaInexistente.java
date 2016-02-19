package test.cerveza;

import static org.junit.Assert.*;
import manager.CervezaManager;
import model.Cerveza;

import org.junit.Test;

import exception.NoEncontroEntidadException;

public class TestBusquedaCervezaInexistente {

	@Test
	public void test() {
		try{
			
			CervezaManager cm = new CervezaManager();
			Cerveza cerveza = cm.buscarCerveza(1);
		
		
		}catch(NoEncontroEntidadException nex){
			System.out.println(nex.getMessage());
		
		}catch(Exception e){
			fail("Fallo la busqueda de cerveza: "+e.getMessage());
		}
	}

}
