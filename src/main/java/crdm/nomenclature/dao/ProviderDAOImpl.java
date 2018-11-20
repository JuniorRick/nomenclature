package crdm.nomenclature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import crdm.nomenclature.entity.Provider;


@Repository
public class ProviderDAOImpl implements ProviderDAO{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Provider> all() {
		Session session = sessionFactory.getCurrentSession();
		
		return session.createQuery("from Provider order by name", Provider.class).getResultList();
	}

	@Override
	public Provider save(Provider provider) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(provider);
		return provider;
	}

	@Override
	public Provider find(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		
		return session.find(Provider.class, id);
	}

	@Override
	public void delete(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Provider provider = session.find(Provider.class, id);
		
		session.delete(provider);
		
	}

}
