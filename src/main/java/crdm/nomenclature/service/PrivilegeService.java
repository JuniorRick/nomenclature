package crdm.nomenclature.service;

import crdm.nomenclature.entity.Privilege;

public interface PrivilegeService {
    Privilege findByName(String name);

    void delete(Privilege privilege);

	Privilege save(Privilege privilege);
}
