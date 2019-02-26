package crdm.nomenclature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import crdm.nomenclature.entity.Request;

@Repository
public class RequestDAOImpl implements RequestDAO {


	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Request> requestList() {
		Session session = sessionFactory.getCurrentSession();
		
		return session.createQuery("from Request where approved = false and deposited = false", Request.class).getResultList();
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

	@Override
	public List<Request> approvedList() {
		Session session = sessionFactory.getCurrentSession();
		
		return session.createQuery("from Request where approved = true and deposited = false", Request.class).getResultList();
	}

	@Override
	public Integer count(Boolean approved) {
		Session session = sessionFactory.getCurrentSession();
		return ((Long)session.createQuery("select count(*) from Request where approved = :approved")
				.setParameter("approved", approved)
				.uniqueResult()).intValue();
	}

	@Override
	public List<Request> depositedList() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Request where deposited = true", Request.class).getResultList();
	}

}
