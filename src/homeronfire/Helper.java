package homeronfire;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/*
 * creates and executes queries using 
 * JPA 2.0 spec. (stores, deletes, and get the attributes from database) 
 */
public class Helper {

	private static EntityManagerFactory emf;
	private ArrayList<String> nameList;
	private ArrayList<Double> latList;
	private ArrayList<Double> lonList;
	private ArrayList<Long> idList;

	public static void persistLocations(String name, double coordLat,
			double coordLon) {
		emf = Persistence.createEntityManagerFactory("nelson");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Locations loc = new Locations();
		loc.setCityName(name);
		loc.setLat(coordLat);
		loc.setLon(coordLon);

		em.persist(loc);
		tx.commit();
		em.close();
	}

	public void deleteLocation(long id) {
		emf = Persistence.createEntityManagerFactory("nelson");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Query query = em
				.createQuery("select l from Locations l WHERE l.id = :id");
		query.setParameter("id", id);
		Locations loc = (Locations) query.getSingleResult();
		em.remove(loc);
		System.out.println("this is link2; location deleted is "
				+ loc.getCityName());
		tx.commit();
		em.close();

	}

	public ArrayList<Long> getIds() {
		emf = Persistence.createEntityManagerFactory("nelson");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("select l from Locations l");
		List<Locations> locList = (List<Locations>) query.getResultList();
		idList = new ArrayList<Long>();
		for (Locations loc : locList) {
			idList.add(loc.getId());
		}
		em.close();
		return idList;
	}

	public ArrayList<String> getNames() {

		emf = Persistence.createEntityManagerFactory("nelson");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("select l from Locations l");
		List<Locations> locList = (List<Locations>) query.getResultList();

		nameList = new ArrayList<String>();
		for (Locations loc : locList) {
			nameList.add(loc.getCityName());
		}
		em.close();
		return nameList;
	}

	public ArrayList<Double> getLats() {

		emf = Persistence.createEntityManagerFactory("nelson");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("select l from Locations l");
		List<Locations> locList = (List<Locations>) query.getResultList();

		latList = new ArrayList<Double>();
		for (Locations loc : locList) {
			latList.add(loc.getLat());
		}
		em.close();
		return latList;
	}

	public ArrayList<Double> getLons() {

		emf = Persistence.createEntityManagerFactory("nelson");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("select l from Locations l");
		List<Locations> locList = (List<Locations>) query.getResultList();
		lonList = new ArrayList<Double>();
		for (Locations loc : locList) {
			lonList.add(loc.getLon());
		}
		em.close();
		return lonList;
	}

	public Locations findLocationById(Long id) {
		emf = Persistence.createEntityManagerFactory("nelson");
		EntityManager em = emf.createEntityManager();
		Query query = em
				.createQuery("Select l from Locations l WHERE l.id = :id");
		query.setParameter("id", id);
		Locations loc = (Locations) query.getSingleResult();
		return loc;
	}

	public List<Locations> entireLocations() {
		emf = Persistence.createEntityManagerFactory("nelson");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select l from Locations l");
		List<Locations> locList = (List<Locations>) query.getResultList();
		em.close();
		return locList;
	}

}
