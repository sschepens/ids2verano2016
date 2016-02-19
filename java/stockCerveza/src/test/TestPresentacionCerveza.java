package test;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import manager.EstiloCervezaManager;
import manager.PresentacionCervezaManager;
import model.EstiloCerveza;
import model.PresentacionCerveza;

import org.junit.Test;

public class TestPresentacionCerveza {

	@Test
	public void test() {
		try{
					
			String presentacionNueva = "Porron 300cc";
			
			PresentacionCervezaManager pcm = new PresentacionCervezaManager();
			
			pcm.crearPresentacionCerveza(presentacionNueva);
			List<PresentacionCerveza> presentacionesCerveza = pcm.listarPresentacionCerveza();
			
			boolean encontre = false;
			for(PresentacionCerveza presentacionCerveza : presentacionesCerveza ){
				
				
				if(presentacionCerveza.getDesripcion().equals(presentacionNueva)){
					encontre = true;
				}
			}
			
			if(!encontre){
				fail("No se creo correctamente la presentacion de la cerveza");
			}
		

		}catch(Exception e){
			fail("Hubo un error al correr el caso de test: "+e.getMessage());
			
		}
	}

}
