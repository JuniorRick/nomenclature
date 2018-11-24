package crdm.nomenclature.dao;

import crdm.nomenclature.entity.Role;

public interface RoleDAO {
	Role findByName(String name);

	void delete(Role role);

	Role save(Role role);
}
