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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Provider get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
