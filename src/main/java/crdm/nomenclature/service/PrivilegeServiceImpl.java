package crdm.nomenclature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crdm.nomenclature.dao.PrivilegeDAO;
import crdm.nomenclature.entity.Privilege;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

	@Autowired
	private PrivilegeDAO privilegeDAO;

	@Override
	@Transactional
	public Privilege findByName(String name) {
		return privilegeDAO.findByName(name);
	}

	@Override
	@Transactional
	public void delete(Privilege privilege) {
		privilegeDAO.delete(privilege);
	}

	@Override
	@Transactional
	public Privilege save(Privilege privilege) {

		return privilegeDAO.save(privilege);
	}

}
