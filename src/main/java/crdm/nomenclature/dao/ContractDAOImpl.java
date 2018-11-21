package crdm.nomenclature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import crdm.nomenclature.entity.Contract;


@Repository
public class ContractDAOImpl implements ContractDAO{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Contract> all() {
		Session session = sessionFactory.getCurrentSession();
		
		return session.createQuery("from Contract order by name", Contract.class).getResultList();
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

}
