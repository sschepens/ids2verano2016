package test;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import manager.EstiloCervezaManager;
import model.EstiloCerveza;

import org.junit.Test;

public class TestCreacionEstiloCerveza {

	@Test
	public void test() {
		try{
			String estiloNuevo = "Pilsner";
			
			EstiloCervezaManager ecm = new EstiloCervezaManager();
			
			
			ecm.crearEstiloCerveza(estiloNuevo);
		
			List<EstiloCerveza> estilosCerveza = ecm.listarEstiloCerveza();
			
			boolean encontre = false;
			for(EstiloCerveza estiloCerveza : estilosCerveza){
				
				if(estiloCerveza.getDescripcion().equals(estiloNuevo)){
					encontre = true;
				}
			}
			
			if(!encontre){
				fail("No se creo correctamente el estilo de cerveza");
			}
		

		}catch(Exception e){
			fail("Hubo un error al correr el caso de test: "+e.getMessage());
			
		}
	}

}
