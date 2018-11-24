package crdm.nomenclature.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import crdm.nomenclature.entity.User;

public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		
		return session.createQuery("from User u where u.email = :email", User.class)
				.setParameter("email", email).getSingleResult();
	}

	@Override
	public void delete(User user) {
		Session session = sessionFactory.getCurrentSession();
		
		session.delete(user);

	}

	@Override
	public User save(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
		
		return user;
	}

}
