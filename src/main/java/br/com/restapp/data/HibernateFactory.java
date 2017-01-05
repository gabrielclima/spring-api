package br.com.restapp.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateFactory {
	
	private static final EntityManagerFactory emf;
	static {
		emf = Persistence.createEntityManagerFactory("users");
	}
	public static EntityManager getEntityManager(){
		return emf.createEntityManager();
	}
	public static void close(){
		emf.close();
	}

}
