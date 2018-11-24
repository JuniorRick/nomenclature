package crdm.nomenclature.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import crdm.nomenclature.entity.Role;

public class RoleDAOImpl implements RoleDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Role findByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Role where name = :name", Role.class)
				.setParameter("name", name).getSingleResult();
	}

	@Override
	public void delete(Role role) {
		Session session = sessionFactory.getCurrentSession();
		
		session.delete(role);

	}

	@Override
	public Role save(Role role) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(role);
		
		return role;
	}

}
