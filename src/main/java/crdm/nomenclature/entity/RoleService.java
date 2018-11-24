package crdm.nomenclature.entity;

public interface RoleService {
	Role findByName(String name);

	void delete(Role role);

	Role save(Role role);
}
