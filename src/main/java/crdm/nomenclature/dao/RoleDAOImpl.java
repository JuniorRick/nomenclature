package crdm.nomenclature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import crdm.nomenclature.entity.Role;

@Repository
public class RoleDAOImpl implements RoleDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Role findByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		List<Role> roles = session.createQuery("from Role where name = :name", Role.class)
				.setParameter("name", name).getResultList();
		
		if(roles == null || roles.isEmpty()) {
			return null;
		}
		return roles.get(0);
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

	@Override
	public List<Role> all() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Role order by name", Role.class).getResultList();
	}

}
