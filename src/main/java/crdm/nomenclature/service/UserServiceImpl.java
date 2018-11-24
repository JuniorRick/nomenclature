package crdm.nomenclature.service;

import org.springframework.beans.factory.annotation.Autowired;

import crdm.nomenclature.dao.UserDAO;
import crdm.nomenclature.entity.User;
import crdm.nomenclature.entity.UserService;

public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public User findByEmail(String email) {
		return userDAO.findByEmail(email);
	}

	@Override
	public void delete(User user) {
		userDAO.delete(user);
	}

	@Override
	public User save(User user) {
		return userDAO.save(user);
	}

}
