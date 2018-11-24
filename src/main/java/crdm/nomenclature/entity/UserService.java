package crdm.nomenclature.entity;

public interface UserService {
	 User findByEmail(String email);

	 void delete(User user);

	User save(User user);
}
