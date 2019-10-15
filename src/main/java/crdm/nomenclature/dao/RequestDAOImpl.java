package crdm.nomenclature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import crdm.nomenclature.component.YearComponent;
import crdm.nomenclature.entity.Request;

@Repository
public class RequestDAOImpl implements RequestDAO {


	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private YearComponent year;
	
	@Override
	public List<Request> requestList() {
		Session session = sessionFactory.getCurrentSession();
		
		return session.createQuery("from Request where approved = false and deposited = false and year(date) = :year", Request.class)
				.setParameter("year", year.getYear()).getResultList();
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
		
		return session.createQuery("from Request where approved = true and deposited = false and year(date) = :year", Request.class)
				.setParameter("year", year.getYear()).getResultList();
	}

	@Override
	public Integer count(Boolean approved, Boolean deposited) {
		Session session = sessionFactory.getCurrentSession();
		return ((Long)session.createQuery("select count(*) from Request where approved = :approved and deposited = :deposited and year(date) = :year")
				.setParameter("approved", approved)
				.setParameter("deposited", deposited)
				.setParameter("year", year.getYear())
				.uniqueResult()).intValue();
	}

	@Override
	public List<Request> depositedList() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Request where deposited = true and year(date) = :year", Request.class)
				.setParameter("year", year.getYear())
				.getResultList();
	}

}
