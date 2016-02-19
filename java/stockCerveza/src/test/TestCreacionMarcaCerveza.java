package test;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import manager.EstiloCervezaManager;
import manager.MarcaCervezaManager;
import manager.ProveedorManager;
import model.EstiloCerveza;
import model.MarcaCerveza;
import model.Proveedor;

import org.junit.Test;

public class TestCreacionMarcaCerveza {

	@Test
	public void test() {
		try{
			
			String marcaNueva = "Boris";
			
			MarcaCervezaManager mcm = new MarcaCervezaManager();
			ProveedorManager pm = new ProveedorManager();
			
			Proveedor proveedor = pm.buscarProveedor(1);
			
			mcm.crearMarCerveza(marcaNueva, proveedor);
			
			List<MarcaCerveza> marcasCerveza = mcm.listarMarcasCerveza();
			
			boolean encontre = false;
			for(MarcaCerveza marcaCerveza : marcasCerveza){
				
				if(marcaCerveza.getDescripcion().equals(marcaNueva) && marcaCerveza.getProveedor().getRazonSocial().equals(proveedor.getRazonSocial())){
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
