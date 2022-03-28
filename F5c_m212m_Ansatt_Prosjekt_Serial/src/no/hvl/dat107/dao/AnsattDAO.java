package no.hvl.dat107.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Prosjekt;
import no.hvl.dat107.entity.Prosjektdeltagelse;

public class AnsattDAO {

	private EntityManagerFactory emf;

	public AnsattDAO() {
		emf = Persistence.createEntityManagerFactory("AnsattProsjektPU");
	}

	public Ansatt finnAnsattMedId(int id) {

		EntityManager em = emf.createEntityManager();

		Ansatt ansatt = null;
		try {
			ansatt = em.find(Ansatt.class, id);
		} finally {
			em.close();
		}
		return ansatt;
	}

	public Ansatt finnAnsattMedBrukernavn(String brukernavn) {

		EntityManager em = emf.createEntityManager();

		Ansatt p = null;
		try {
			p = em.find(Ansatt.class, brukernavn); // Henter ut på primærnøkkel
		} finally {
			em.close();
		}

		return p;
	}

	public void registrerProsjektdeltagelse(Ansatt a, Prosjekt p) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			Prosjektdeltagelse pd = new Prosjektdeltagelse(a, p, 0);

			em.merge(a).leggTilProsjektdeltagelse(pd);
			em.merge(p).leggTilProsjektdeltagelse(pd);

			em.persist(pd);

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

	public void slettProsjektdeltagelse(Ansatt a, Prosjekt p) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			// TODO - Må søke med JPQL. Ellers som i b)

//            Prosjektdeltagelse pd = em.find(Prosjektdeltagelse.class, ???);
//            
//            //...
//            
//            em.remove(pd);

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

//    private Prosjektdeltagelse finnProsjektdeltagelse(int ansattId, int prosjektId) {
//        
//        String queryString = "SELECT pd FROM Prosjektdeltagelse pd " 
//                + "WHERE pd.ansatt.id = :ansattId AND pd.prosjekt.id = :prosjektId";
//
//        EntityManager em = emf.createEntityManager();
//
//        Prosjektdeltagelse pd = null;
//        try {
//            TypedQuery<Prosjektdeltagelse> query 
//                    = em.createQuery(queryString, Prosjektdeltagelse.class);
//            query.setParameter("ansattId", ansattId);
//            query.setParameter("prosjektId", prosjektId);
//            pd = query.getSingleResult();
//            
//        } catch (NoResultException e) {
//            // e.printStackTrace();
//        } finally {
//            em.close();
//        }
//        return pd;
//    }
	public void leggTilNyAnsatt(Ansatt p) {

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

	public List<Ansatt> finnAlleAnsatte() {

		EntityManager em = emf.createEntityManager();

		List<Ansatt> personer = null;
		try {
			TypedQuery<Ansatt> query = em.createQuery("SELECT p FROM Ansatt as p order by p.id", Ansatt.class);
			personer = query.getResultList(); // Henter ut basert på spørring
		} finally {
			em.close();
		}
		return personer;
	}

	public List<Ansatt> finnAlleAnsatteNQ() {
		/* Tester ut NamedQuery */

		EntityManager em = emf.createEntityManager();

		List<Ansatt> personer = null;
		try {
			TypedQuery<Ansatt> query = em.createNamedQuery("hentAllePersoner", Ansatt.class);
			personer = query.getResultList(); // Henter ut basert på spørring
		} finally {
			em.close();
		}
		return personer;
	}

	public void oppdatereAnsattNavn(int id, String nyttFornavn, String nyttEtternavn) {

		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();

			Ansatt p = em.find(Ansatt.class, id); // Finne rad som skal oppdateres
			p.setFornavn(nyttFornavn); // Oppdatere managed oject p => sync med database
			p.setEtternavn(nyttEtternavn);

			em.getTransaction().commit();

		} catch (Throwable e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	public void oppdatereAnsatt(int id, String bruker, String nyttFornavn, String nyttEtternavn, String stilling,
			BigDecimal lonn, int avdeling) {

		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();

			Ansatt p = em.find(Ansatt.class, id); // Finne rad som skal oppdateres
			p.setBrukernavn(bruker);
			p.setFornavn(nyttFornavn); // Oppdatere managed oject p => sync med database
			p.setEtternavn(nyttEtternavn);
			p.setStilling(stilling);
			p.setLonn(lonn);
			p.setAvdeling(avdeling);

			em.getTransaction().commit();

		} catch (Throwable e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	public void sletteAnsatt(int id) {

		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();

			Ansatt p = em.find(Ansatt.class, id); // Finne rad som skal slettes
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
