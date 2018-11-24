package crdm.nomenclature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crdm.nomenclature.dao.RoleDAO;
import crdm.nomenclature.entity.Role;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAO roleDAO;
	
	@Override
	@Transactional
	public Role findByName(String name) {
		// TODO Auto-generated method stub
		return roleDAO.findByName(name);
	}

	@Override
	@Transactional
	public void delete(Role role) {
		roleDAO.delete(role);

	}

	@Override
	@Transactional
	public Role save(Role role) {

		return roleDAO.save(role);
	}

}
