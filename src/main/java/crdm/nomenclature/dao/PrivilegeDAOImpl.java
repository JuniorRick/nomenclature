package crdm.nomenclature.dao;

import java.util.List;

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
		List<Privilege> privileges = session.createQuery("from Privilege where name = :name", Privilege.class)
				.setParameter("name", name).getResultList();
		
		if(privileges == null || privileges.isEmpty()) {
			return null;
		}
		return privileges.get(0);
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
