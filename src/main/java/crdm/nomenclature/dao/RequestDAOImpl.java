package crdm.nomenclature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import crdm.nomenclature.entity.Privilege;
import crdm.nomenclature.entity.Request;

@Repository
public class RequestDAOImpl implements RequestDAO {


	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Request> all() {
		Session session = sessionFactory.getCurrentSession();
		
		return session.createQuery("from Request", Request.class).getResultList();
	}

	@Override
	public Request save(Request request) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(request);
		return request;
	}

	@Override
	public Request find(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		
		return session.find(Request.class, id);
	}

	@Override
	public void delete(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Request request = session.find(Request.class, id);
		
		session.delete(request);
		
	}

}
