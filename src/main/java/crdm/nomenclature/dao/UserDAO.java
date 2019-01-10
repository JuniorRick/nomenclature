package crdm.nomenclature.dao;


import java.util.List;

import crdm.nomenclature.entity.User;

public interface UserDAO {
    User findByEmail(String email);

    void delete(User user);

	User save(User user);

	List<User> all();

}