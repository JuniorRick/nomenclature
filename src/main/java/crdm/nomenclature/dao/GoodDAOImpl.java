package crdm.nomenclature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import crdm.nomenclature.component.YearComponent;
import crdm.nomenclature.entity.Good;


@Repository
public class GoodDAOImpl implements GoodDAO{

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private YearComponent year;
	
	@Override
	public List<Good> all() {
		Session session = sessionFactory.getCurrentSession();
		
		return session.createQuery("from Good g where year(g.contract.startDate) = :year order by good", Good.class)
				.setParameter("year", year.getYear()).getResultList();
	}

	@Override
	public Good save(Good good) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(good);
		return good;
	}

	@Override
	public Good find(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		
		return session.find(Good.class, id);
	}

	@Override
	public void delete(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Good good = session.find(Good.class, id);
		
		session.delete(good);
		
	}

}
