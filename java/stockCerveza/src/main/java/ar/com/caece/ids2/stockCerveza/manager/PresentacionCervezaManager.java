package ar.com.caece.ids2.stockCerveza.manager;

import java.util.List;

import javax.persistence.Query;

import ar.com.caece.ids2.stockCerveza.exception.NoEncontroEntidadException;
import ar.com.caece.ids2.stockCerveza.model.PresentacionCerveza;

public class PresentacionCervezaManager extends Manager{

	public PresentacionCervezaManager(){
		super();
		
	}
	
	/**
	 * Metodo que crea una nueva presentacion de cerveza
	 * @param descripcion
	 */
	public void crearPresentacionCerveza(String descricion) throws RuntimeException, Exception{
		
		PresentacionCerveza presentacion = new PresentacionCerveza();
	
		presentacion.setDesripcion(descricion);
		
		em.getTransaction().begin();
		em.persist(presentacion);
		em.flush();
		em.getTransaction().commit();
	}
	
	/**
	 * Metodo que busca una presentacion de cerveza dado un id de presentacion de cerveza
	 * @param idPresentacion
	 */
	public PresentacionCerveza buscarPresentacionCerveza(int idPresentacion) throws NoEncontroEntidadException{

		PresentacionCerveza presentacionCerveza = em.find(PresentacionCerveza.class, idPresentacion);
		if(presentacionCerveza == null){
			throw new NoEncontroEntidadException(PresentacionCerveza.class);
		}
		return presentacionCerveza;
		
		
	}
	
	/**
	 * Metodo que devuelve todas las instancias de estilos de marcas de cerveza
	 */
	@SuppressWarnings("unchecked")
	public List<PresentacionCerveza> listarPresentacionCerveza() {
		
		Query q = em.createNamedQuery("PresentacionCerveza.listarPresentacionCerveza");
		return (List<PresentacionCerveza>)q.getResultList();
	}
	
	
	/**
	 * Metodo que modifica una presentacion de cerveza
	 * @param presentacionCerveza
	 */
	public void modificarPresentacionCerveza(PresentacionCerveza presentacionCerveza){
		em.getTransaction().begin();
		em.merge(presentacionCerveza);
		em.getTransaction().commit();
	}
	

}
