package crdm.nomenclature.dao;

import crdm.nomenclature.entity.Privilege;

public interface PrivilegeDAO {
    Privilege findByName(String name);

    void delete(Privilege privilege);

	Privilege save(Privilege privilege);
}
