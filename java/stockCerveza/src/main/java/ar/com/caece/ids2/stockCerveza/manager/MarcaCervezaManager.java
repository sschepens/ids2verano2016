package ar.com.caece.ids2.stockCerveza.manager;

import java.util.List;

import javax.persistence.Query;

import ar.com.caece.ids2.stockCerveza.exception.NoEncontroEntidadException;
import ar.com.caece.ids2.stockCerveza.model.MarcaCerveza;
import ar.com.caece.ids2.stockCerveza.model.Proveedor;


public class MarcaCervezaManager extends Manager {
	
	public MarcaCervezaManager(){
		super();
	}
	
	/**
	 * Metodo que crea una nueva marca de cerveza
	 * @param descripcion
	 * @param proveedor
	 */
	public void crearMarCerveza(String descripcion,Proveedor proveedor) throws RuntimeException, Exception{
		
		MarcaCerveza marcaCerveza = new MarcaCerveza();
	
		marcaCerveza.setDescripcion(descripcion);
		marcaCerveza.setEstado(true);
		marcaCerveza.setProveedor(proveedor);
		
		em.getTransaction().begin();
		em.persist(marcaCerveza);
		em.flush();
		em.getTransaction().commit();
	}
	
	/**
	 * Metodo que busca una marca de cerveza dado un id de marca de cerveza
	 * @param idCerveza
	 */
	public MarcaCerveza buscarMarcaCerveza(int idCerveza) throws NoEncontroEntidadException{
		MarcaCerveza marcaCerveza = null;
		marcaCerveza = em.find(MarcaCerveza.class, idCerveza);
		
		if(marcaCerveza == null){
			throw new NoEncontroEntidadException(MarcaCerveza.class);
			
		}
		return marcaCerveza;
		
	}
	
	/**
	 * Metodo que devuelve todas las instancias de estilos de marcas de cerveza
	 */
	@SuppressWarnings("unchecked")
	public List<MarcaCerveza> listarMarcasCerveza() throws Exception{
		
		Query q = em.createNamedQuery("MarcaCerveza.listarMarcasCerveza");
		return (List<MarcaCerveza>)q.getResultList();
	}
	
	/**
	 * Metodo que modifica una marca de cerveza
	 * @param marcaCerveza
	 */
	public void modificarMarcaCerveza(MarcaCerveza marcaCerveza) throws RuntimeException,Exception{
		em.getTransaction().begin();
		em.merge(marcaCerveza);
		em.flush();
		em.getTransaction().commit();
	}
	
	/**
	 * Metodo que realiza un borrado logico de una marca de cerveza
	 * @param idCerveza
	 * @throws RuntimeException,Exception 
	 */
	public void borrarMarcaCerveza(int idCerveza) throws RuntimeException,Exception{
		MarcaCerveza marcaCerveza = buscarMarcaCerveza(idCerveza);
		
		marcaCerveza.setEstado(false);
		
		em.getTransaction().begin();
		em.merge(marcaCerveza);
		em.flush();
		em.getTransaction().commit();
	}
}
