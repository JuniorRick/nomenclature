package crdm.nomenclature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import crdm.nomenclature.component.YearComponent;
import crdm.nomenclature.entity.Purchase;


@Repository
public class PurchaseDAOImpl implements PurchaseDAO{

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private YearComponent year;
	
	@Override
	public List<Purchase> all() {
		Session session = sessionFactory.getCurrentSession();
		
		return session.createQuery("from Command c where year(c.request.date) = :year", Purchase.class)
				.setParameter("year", year.getYear()).getResultList();
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
		
		return session.createQuery("from Command c where year(c.request.date) = :year and c.approved != true", Purchase.class)
				.setParameter("year", year.getYear()).getResultList();
	}


}
