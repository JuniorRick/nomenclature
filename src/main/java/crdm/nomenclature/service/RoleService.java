package crdm.nomenclature.service;

import java.util.List;

import crdm.nomenclature.entity.Role;

public interface RoleService {
	Role findByName(String name);

	void delete(Role role);

	Role save(Role role);

	List<Role> all();
}
