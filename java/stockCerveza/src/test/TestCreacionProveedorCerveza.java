package test;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import manager.EstiloCervezaManager;
import manager.ProveedorManager;
import model.EstiloCerveza;
import model.Proveedor;

import org.junit.Test;

public class TestCreacionProveedorCerveza {

	@Test
	public void test() {
		try{
			
			String proveedorNuevo = "Maltas Santafecinas";
			String telefonoNuevo = "444-4444";
			
			ProveedorManager pm = new ProveedorManager();
			
			pm.crearProveedor(proveedorNuevo, telefonoNuevo);;
			List<Proveedor> Proveedores = pm.listarProveedores();
			
			boolean encontre = false;
			for(Proveedor proveedor : Proveedores){
				
				if(proveedor.getRazonSocial().equals(proveedorNuevo) && proveedor.getTelefono().equals(telefonoNuevo)){
					encontre = true;
				}
			}
			
			if(!encontre){
				fail("No se creo correctamente el proveedor");
			}
		

		}catch(Exception e){
			fail("Hubo un error al correr el caso de test: "+e.getMessage());
			
		}
	}

}
