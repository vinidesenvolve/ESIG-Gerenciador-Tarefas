package br.com.esig.gerenciador.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

	public static EntityManagerFactory emFactory;
	
	static {
		emFactory = Persistence.createEntityManagerFactory("TarefasPU");
	}
	
	public static EntityManager getEntityManager() {
		return emFactory.createEntityManager();
	}
}