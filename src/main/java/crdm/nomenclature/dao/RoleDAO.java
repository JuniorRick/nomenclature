package crdm.nomenclature.dao;

import java.util.List;

import crdm.nomenclature.entity.Role;

public interface RoleDAO {
	Role findByName(String name);

	void delete(Role role);

	Role save(Role role);

	List<Role> all();
}
