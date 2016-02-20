package ar.com.caece.ids2.stockCerveza.manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Manager {
	protected EntityManager em;
	
	public Manager(){
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "pedidosBE" );
		
		em = emfactory.createEntityManager();
	}
	
}

