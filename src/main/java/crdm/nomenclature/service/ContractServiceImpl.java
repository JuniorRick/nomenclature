package crdm.nomenclature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crdm.nomenclature.dao.ContractDAO;
import crdm.nomenclature.entity.Contract;


@Service
public class ContractServiceImpl implements ContractService {

	@Autowired
	private ContractDAO contractDAO;
	
	@Override
	@Transactional
	public List<Contract> all() {
		return contractDAO.all();
	}

	@Override
	@Transactional
	public Contract save(Contract contract) {
		return contractDAO.save(contract);
	}

	@Override
	@Transactional
	public Contract find(Integer id) {
		return contractDAO.find(id);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		contractDAO.delete(id);

	}

}
