package crdm.nomenclature.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import crdm.nomenclature.entity.Privilege;

@Repository
public class PrivilegeDAOImpl implements PrivilegeDAO {


	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Privilege findByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Privilege where name = :name", Privilege.class)
				.setParameter("name", name).getSingleResult();
	}

	@Override
	public void delete(Privilege privilege) {
		Session session = sessionFactory.getCurrentSession();
		
		session.delete(privilege);

	}

	@Override
	public Privilege save(Privilege privilege) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(privilege);
		
		return privilege;
	}

}
