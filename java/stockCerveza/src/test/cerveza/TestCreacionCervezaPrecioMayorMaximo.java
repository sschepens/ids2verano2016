package test.cerveza;

import static org.junit.Assert.*;

import java.util.List;

import manager.CervezaManager;
import manager.EstiloCervezaManager;
import manager.MarcaCervezaManager;
import manager.PresentacionCervezaManager;
import model.Cerveza;
import model.EstiloCerveza;
import model.MarcaCerveza;
import model.PresentacionCerveza;

import org.junit.Test;

import exception.ValorFueraDeRangoException;

public class TestCreacionCervezaPrecioMayorMaximo {

	@Test
	public void test() {
		
		try{
			
			
			int cantidadCerveza = 100;
			Double precioCerveza = new Double(Double.MAX_VALUE *10);
			
			EstiloCervezaManager ecm = new EstiloCervezaManager();
			MarcaCervezaManager mcm = new MarcaCervezaManager();
			PresentacionCervezaManager pcm = new PresentacionCervezaManager();
			CervezaManager cm = new CervezaManager();
			
			
			List<PresentacionCerveza> presentacionCerveza = pcm.listarPresentacionCerveza();
			MarcaCerveza marcaCerveza = mcm.buscarMarcaCerveza(1);
			EstiloCerveza estiloCerveza = ecm.buscarEstiloCerveza(1); 
			
			cm.crearCerveza(marcaCerveza, presentacionCerveza,estiloCerveza, cantidadCerveza, precioCerveza);
	
			fail("Se creo una cerveza con un valor no valido en el precio");
		}catch(ValorFueraDeRangoException vfre){
			//todo ok
		}catch(Exception e ){
			fail("hubo un error al ejecutar el test: " + e.getMessage());
		}
	
	}
	

}
