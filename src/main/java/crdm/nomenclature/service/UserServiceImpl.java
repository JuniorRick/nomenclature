package crdm.nomenclature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crdm.nomenclature.dao.UserDAO;
import crdm.nomenclature.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	@Transactional
	public User findByEmail(String email) {
		return userDAO.findByEmail(email);
	}

	@Override
	@Transactional
	public void delete(User user) {
		userDAO.delete(user);

	}

	@Override
	@Transactional
	public User save(User user) {
		return userDAO.save(user);
	}

	@Override
	@Transactional
	public List<User> all() {
		// TODO Auto-generated method stub
		return userDAO.all();
	}

}
