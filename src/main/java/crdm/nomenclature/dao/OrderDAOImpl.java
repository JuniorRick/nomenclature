package crdm.nomenclature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import crdm.nomenclature.entity.Command;


@Repository
public class OrderDAOImpl implements OrderDAO{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Command> all() {
		Session session = sessionFactory.getCurrentSession();
		
		return session.createQuery("from Command", Command.class).getResultList();
	}
	
	@Override
	public Command save(Command order) {
		Session session = sessionFactory.getCurrentSession();
		
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
		
		session.delete(order);
	}

	@Override
	public List<Command> requests() {
		Session session = sessionFactory.getCurrentSession();
		
		return session.createQuery("from Command where approved != true", Command.class).getResultList();
	}


}
