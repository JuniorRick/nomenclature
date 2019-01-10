package crdm.nomenclature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import crdm.nomenclature.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		List<User> users = session.createQuery("from User u where u.email = :email", User.class)
				.setParameter("email", email).getResultList();
		if(users == null || users.isEmpty()) {
			return null;
		}
		
		return users.get(0);
	}

	@Override
	public void delete(User user) {
		Session session = sessionFactory.getCurrentSession();
		
		session.delete(user);

	}

	@Override
	public User save(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
		
		return user;
	}

	@Override
	public List<User> all() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from User u order by email", User.class).getResultList();
	}

}
