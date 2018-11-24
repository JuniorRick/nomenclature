package crdm.nomenclature.service;

import crdm.nomenclature.entity.Role;

public interface RoleService {
	Role findByName(String name);

	void delete(Role role);

	Role save(Role role);
}
