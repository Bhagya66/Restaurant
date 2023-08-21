package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dto.Custmor;

public class Mydao {
	EntityManagerFactory e = Persistence.createEntityManagerFactory("dev");
	EntityManager em = e.createEntityManager();
	EntityTransaction t = em.getTransaction();

	public void save(Custmor C) {
		t.begin();
		em.persist(C);
		t.commit();
	}

	public Custmor fetchByEmail(String email) {
		//check the email matching
List<Custmor> list =em.createQuery("select B from Custmor B where email=?1").setParameter(1, email) .getResultList();;
		
		if(list.isEmpty())
			return null;
		else
			return list.get(0);
	}

	public Custmor fetchByMobile(long mobile) {
		List<Custmor> list =em.createQuery("select B from Custmor B where mobile=?1").setParameter(1, mobile).getResultList();;
		 
		if(list.isEmpty())
			return null;
		else
			return list.get(0);
	}
}
