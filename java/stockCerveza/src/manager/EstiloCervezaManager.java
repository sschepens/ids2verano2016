package manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.EstiloCerveza;
import exception.NoEncontroEntidadException;

public class EstiloCervezaManager extends Manager{
	
	
	public EstiloCervezaManager(){
		super();
	}
	
	/**
	 * Metodo que crea un nuevo estilo de cerveza
	 * @param descripcion
	 */
	public void crearEstiloCerveza(String descripcion) throws RuntimeException,Exception{
		
		EstiloCerveza estiloCerveza = new EstiloCerveza();
	
		estiloCerveza.setDescripcion(descripcion);
		em.getTransaction().begin();
		em.persist(estiloCerveza);
		em.getTransaction().commit();
	}

	/**
	 * Metodo que busca un estilo de cerveza dado un id de estilo
	 * @param idEstilo
	 */
	public EstiloCerveza buscarEstiloCerveza(int idEstilo) throws NoEncontroEntidadException{
		
		EstiloCerveza estiloCerveza = em.find(EstiloCerveza.class, idEstilo);
		
		if(estiloCerveza == null){
			throw new NoEncontroEntidadException(EstiloCerveza.class);
		}
		return estiloCerveza;
	}

	
	/**
	 * Metodo que devuelve todas las instancias de estilos de cerveza
	 */
	public List<EstiloCerveza> listarEstiloCerveza(){
		
		Query q = em.createNamedQuery("EstiloCerveza.listarEstiloCerveza");
		return (List<EstiloCerveza>)q.getResultList();
	}
	
	/**
	 * Metodo que modifica una instancia de estilo de cerveza
	 * * @param estiloCerveza
	 
	 */
	public void modificarEstiloCerveza(EstiloCerveza EstiloCerveza){
		em.getTransaction().begin();
		em.merge(EstiloCerveza);
		em.flush();
		em.getTransaction().commit();
		
	}
	

}
