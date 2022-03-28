package no.hvl.dat107.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Avdeling;
import no.hvl.dat107.entity.Prosjekt;

public class AvdelingDAO {

	private EntityManagerFactory emf;

	public AvdelingDAO() {
		emf = Persistence.createEntityManagerFactory("AnsattProsjektPU");
	}

	public Avdeling finnAvdelingMedId(int id) {

		EntityManager em = emf.createEntityManager();

		Avdeling avdeling = null;
		try {
			avdeling = em.find(Avdeling.class, id);
		} finally {
			em.close();
		}
		return avdeling;
	}

	public List<Avdeling> finnAlleAvdelinger() {

		EntityManager em = emf.createEntityManager();

		List<Avdeling> personer = null;
		try {
			TypedQuery<Avdeling> query = em.createQuery("SELECT p FROM Avdeling as p order by p.id", Avdeling.class);
			personer = query.getResultList(); // Henter ut basert på spørring
		} finally {
			em.close();
		}
		return personer;
	}

	public void oppdatereAvdelingSjef(int id, int nySjef) {

		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();

			Avdeling p = em.find(Avdeling.class, id); // Finne rad som skal oppdateres
			p.setSjef(nySjef); // Oppdatere managed oject p => sync med database

			em.getTransaction().commit();

		} catch (Throwable e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	public void lageNyAvdeling(Avdeling p) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(p); // Oppretter en ny rad i databasen
			tx.commit();

		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}

	public void sletteAvdeling(int id) {

		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();

			Avdeling p = em.find(Avdeling.class, id); // Finne rad som skal slettes
			em.remove(p); // Slette rad som tilsvarer managed oject p

			em.getTransaction().commit();

		} catch (Throwable e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

}
