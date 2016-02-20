package ar.com.caece.ids2.stockCerveza.manager;

import java.util.List;

import javax.persistence.Query;

import ar.com.caece.ids2.stockCerveza.exception.NoEncontroEntidadException;
import ar.com.caece.ids2.stockCerveza.model.Proveedor;

public class ProveedorManager extends Manager{
	
	public ProveedorManager(){
		super();
	}
	
	
	/**
	 * Metodo que crea un nuevo proveedor de cerveza
	 * @param razonsocial
	 * @param telefono
	 */
	public void crearProveedor(String razonsocial,String telefono){
		
		Proveedor proveedor = new Proveedor();
	
		proveedor.setRazonSocial(razonsocial);
		proveedor.setMarcaCerveza(null);
		proveedor.setTelefono(telefono);
		proveedor.setEstado(true);
		
		em.getTransaction().begin();
		em.persist(proveedor);
		em.flush();
		em.getTransaction().commit();
	}
	
	/**
	 * Metodo que busca un proveedor de cerveza dado un id de proveedor de cerveza
	 * @param idProveedor
	 */
	public Proveedor buscarProveedor(int idProveedor) throws NoEncontroEntidadException{
		
		Proveedor proveedor = em.find(Proveedor.class, idProveedor);
		if(proveedor == null){
			throw new NoEncontroEntidadException(Proveedor.class);
		}
		return proveedor;
	}
	
	/**
	 * Metodo que modifica una proveedor de cerveza
	 * @param proveedor
	 */
	public Proveedor modificarProveedor(Proveedor proveedor) {
		
		em.getTransaction().begin();
		em.merge(proveedor);
		em.flush();
		em.getTransaction().commit();
		
		return proveedor;
		
	}
	
	/**
	 * Metodo que realiza un borrado logico de un proveedor de cerveza
	 * @param idProveedor
	 * @throws NoEncontroEntidadException 
	 */
	public void bajaProveedor(int idProveedor) throws NoEncontroEntidadException{
		em.getTransaction().begin();
		em.remove(buscarProveedor(idProveedor));
		em.getTransaction().commit();
	}
	
	/**
	 * Metodo que devuelve todas las instancias de proveedores de cerveza
	 */
	@SuppressWarnings("unchecked")
	public List<Proveedor> listarProveedores(){
		Query q = em.createNamedQuery("Proveedor.listarProveedores");
		return (List<Proveedor>)q.getResultList();
	}
}