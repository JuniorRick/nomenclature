package crdm.nomenclature.service;

import org.springframework.beans.factory.annotation.Autowired;

import crdm.nomenclature.dao.PrivilegeDAO;
import crdm.nomenclature.entity.Privilege;

public class PrivilegeServiceImpl implements PrivilegeService {
	
	@Autowired
	private PrivilegeDAO privilegeDAO;
	
	@Override
	public Privilege findByName(String name) {
		return privilegeDAO.findByName(name);
	}

	@Override
	public void delete(Privilege privilege) {
		privilegeDAO.delete(privilege);
	}

	@Override
	public Privilege save(Privilege privilege) {
		
		return privilegeDAO.save(privilege);
		
	}

}
