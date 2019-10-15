package crdm.nomenclature.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import crdm.nomenclature.component.YearComponent;
import crdm.nomenclature.entity.Contract;


@Repository
public class ContractDAOImpl implements ContractDAO{

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private YearComponent year;
	
	@Override
	public List<Contract> all() {
		Session session = sessionFactory.getCurrentSession();
		
		return session.createQuery("from Contract where year(startDate) = :year order by name", Contract.class)
				.setParameter("year", year.getYear()).getResultList();
	}

	@Override
	public Contract save(Contract contract) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(contract);
		
		return contract;
	}

	@Override
	public Contract find(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		
		return session.find(Contract.class, id);
	}

	@Override
	public void delete(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Contract contract = session.find(Contract.class, id);
		
		session.delete(contract);
		
	}

	public List<Integer> years() {
		
		Session session = sessionFactory.getCurrentSession();
		
		TypedQuery<Integer> query =
			      session.createQuery("select distinct(Year(startDate)) as year from Contract order by year desc", Integer.class);
			  List<Integer> results = query.getResultList();
		
		return results;
	}
	

}
