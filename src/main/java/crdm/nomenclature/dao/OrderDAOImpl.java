package crdm.nomenclature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import crdm.nomenclature.entity.Command;
import crdm.nomenclature.entity.Purchase;


@Repository
public class OrderDAOImpl implements OrderDAO{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Command> all() {
		Session session = sessionFactory.getCurrentSession();
		
		return session.createQuery("from Command where approved != true", Command.class).getResultList();
	}

	public List<Command> approvedList() {
		Session session = sessionFactory.getCurrentSession();
		
		return session.createQuery("from Command where approved = true", Command.class).getResultList();
	}
	
	public void approve(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		
		Command order = find(id);
		order.setApproved(true);
		Purchase purchase = order.getPurchase();
		
		float remainder = purchase.getRemainder() - order.getQuantity();
		if(remainder < 0.0f) {
			throw new RuntimeException();
		}
		
		purchase.setRemainder(remainder);
		
		session.update(purchase);
		session.update(order);
		
	}
	
	@Override
	public Command save(Command order) {
		Session session = sessionFactory.getCurrentSession();
		
		Purchase purchase = order.getPurchase();
		
		float remainder = purchase.getRemainder() - order.getQuantity();
		if(remainder < 0.0f) {
			throw new RuntimeException();
		}
		
		purchase.setRemainder(remainder);
		
		session.update(purchase);
		
		session.saveOrUpdate(order);
		return order;
	}

	@Override
	public Command find(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		
		return session.find(Command.class, id);
	}

	@Override
	public void delete(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		
		Command order = session.find(Command.class, id);
		
			Purchase purchase = order.getPurchase();
			float remainder = order.getQuantity() + purchase.getRemainder();
			purchase.setRemainder(remainder);
			
			session.update(purchase);
		
		session.delete(order);
		
	}

	@Override
	public List<Command> requests() {
		Session session = sessionFactory.getCurrentSession();
		
		return session.createQuery("from Command where approved != true", Command.class).getResultList();
	}

}
