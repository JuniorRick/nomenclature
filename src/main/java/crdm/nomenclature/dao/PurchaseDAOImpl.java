package crdm.nomenclature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import crdm.nomenclature.entity.Purchase;


@Repository
public class PurchaseDAOImpl implements PurchaseDAO{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Purchase> all() {
		Session session = sessionFactory.getCurrentSession();
		
		return session.createQuery("from Command", Purchase.class).getResultList();
	}
	
	@Override
	public Purchase save(Purchase purchase) {
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(purchase);
		return purchase;
	}

	@Override
	public Purchase find(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		
		return session.find(Purchase.class, id);
	}

	@Override
	public void delete(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		
		Purchase purchase = session.find(Purchase.class, id);
		
		session.delete(purchase);
	}

	@Override
	public List<Purchase> requests() {
		Session session = sessionFactory.getCurrentSession();
		
		return session.createQuery("from Command where approved != true", Purchase.class).getResultList();
	}


}
