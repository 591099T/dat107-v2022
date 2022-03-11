package no.hvl.dat107;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TodoDAO {
	
	private EntityManagerFactory emf 
			= Persistence.createEntityManagerFactory("todoPersistenceUnit", 
			Map.of("javax.persistence.jdbc.password", "5767Killer9248"));
	
	/* --------------------------------------------------------------------- */

	public String/*?*/ finnTodoMedPk(/*TODO*/) {
		
		EntityManager em = emf.createEntityManager();

		try {
			return null; /*TODO*/

		} finally {
			em.close();
		}
	}

	/* --------------------------------------------------------------------- */

	public String/*?*/ finnAlleTodos(/*TODO*/) {
		
		EntityManager em = emf.createEntityManager();
		
		try {
			return null; /*TODO*/
		
		} finally {
			em.close();
		}
	}

	/* --------------------------------------------------------------------- */

	public String/*?*/  finnTodoMedTekst(/*TODO*/) {
		EntityManager em = emf.createEntityManager();
		
		try {
			return null; /*TODO*/
			
		} finally {
			em.close();
		}
	}
	
	/* --------------------------------------------------------------------- */

	public String/*?*/  finnTodosMedTekst(/*TODO*/) {
		EntityManager em = emf.createEntityManager();
		
		try {
			return null; /*TODO*/
		
		} finally {
			em.close();
		}
	}

	/* --------------------------------------------------------------------- */

	public void/*TODO*/ lagreNyTodo(/*TODO*/) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			
			/*TODO*/
			
			tx.commit();

		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
	}

	/* --------------------------------------------------------------------- */

	public void/*TODO*/ slettTodoMedPk(/*TODO*/) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			
			/*TODO*/
			
			tx.commit();

		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
	}

	/* --------------------------------------------------------------------- */

	public void/*TODO*/ oppdaterTodo(/*TODO*/) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			/*TODO*/
			
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
	}

	/* --------------------------------------------------------------------- */

	public void/*TODO*/ oppdaterTekst(/*TODO*/) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			/*TODO*/
						
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
	}
}
