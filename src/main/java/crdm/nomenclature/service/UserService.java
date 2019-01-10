package crdm.nomenclature.service;

import java.util.List;

import crdm.nomenclature.entity.User;

public interface UserService {
	User findByEmail(String email);

	void delete(User user);

	User save(User user);

	List<User> all();

}
