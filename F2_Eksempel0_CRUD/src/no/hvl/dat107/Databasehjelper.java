package no.hvl.dat107;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Databasehjelper {

	private EntityManagerFactory emf;
	
	public Databasehjelper() {
		emf = Persistence.createEntityManagerFactory("personPersistenceUnit", 
				Map.of("javax.persistence.jdbc.password", Passwords.AZURE_PASSWORD));
	}
	
	//Create - Hvordan opprette ny(e) rad(er) i databasen
	public void createPerson(Person p) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(p); //Oppretter en ny rad i databasen
			tx.commit();
		
		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}
	
	//Read1 - Hvordan hente ut data fra databasen
	public Person retrievePerson(int id) {

		EntityManager em = emf.createEntityManager();

		Person p = null;
		try {
			p = em.find(Person.class, id); //Henter ut på primærnøkkel
		} finally {
			em.close();
		}
		
		return p;
	}
	
	//Read2 - Hvordan hente ut data fra databasen
	public List<Person> retrieveAllePersoner() {

		EntityManager em = emf.createEntityManager();

		List<Person> personer = null;
		try {
			TypedQuery<Person> query = em.createQuery(
			        "SELECT p FROM Person as p order by p.id", Person.class);
			personer = query.getResultList(); //Henter ut basert på spørring
		} finally {
			em.close();
		}
		return personer;
	}
	
	//Read3 - Hvordan hente ut data fra databasen
	public List<Person> retrieveAllePersonerNQ() {
		/* Tester ut NamedQuery */

		EntityManager em = emf.createEntityManager();

		List<Person> personer = null;
		try {
			TypedQuery<Person> query = em.createNamedQuery(
					"hentAllePersoner", Person.class);
			personer = query.getResultList(); //Henter ut basert på spørring
		} finally {
			em.close();
		}
		return personer;
	}
	
	//Update - Hvordan oppdatere data i databasen
	public void updatePerson(int id, String nyttNavn) {

		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			
			Person p = em.find(Person.class, id); //Finne rad som skal oppdateres
			p.setNavn(nyttNavn); //Oppdatere managed oject p => sync med database
		
			em.getTransaction().commit();
			
		} catch (Throwable e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
	
	//Delete - Hvordan slette rad(er) fra databasen
	public void deletePerson(int id) {
		
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			
			Person p = em.find(Person.class, id); //Finne rad som skal slettes
			em.remove(p); //Slette rad som tilsvarer managed oject p
			
			em.getTransaction().commit();
		
		} catch (Throwable e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	
}
