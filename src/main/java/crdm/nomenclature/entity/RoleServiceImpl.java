package crdm.nomenclature.entity;

import org.springframework.beans.factory.annotation.Autowired;

import crdm.nomenclature.dao.RoleDAO;

public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAO roleDAO;
	
	@Override
	public Role findByName(String name) {
		// TODO Auto-generated method stub
		return roleDAO.findByName(name);
	}

	@Override
	public void delete(Role role) {
		roleDAO.delete(role);

	}

	@Override
	public Role save(Role role) {

		return roleDAO.save(role);
	}

}
