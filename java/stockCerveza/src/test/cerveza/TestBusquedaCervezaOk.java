package test.cerveza;

import static org.junit.Assert.*;
import manager.CervezaManager;
import model.Cerveza;

import org.junit.Test;

public class TestBusquedaCervezaOk {

	@Test
	public void test() {
		try{
			
			CervezaManager cm = new CervezaManager();
			Cerveza cerveza = cm.buscarCerveza(5);
			
			System.out.println("Precio:"+cerveza.getPrecio());
			
		}catch(Exception e){
			fail("Fallo la busqueda de cerveza: "+e.getMessage());
		}
	}

}
