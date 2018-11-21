package crdm.nomenclature.service;

import java.util.List;

import crdm.nomenclature.entity.Contract;

public interface ContractService {
	public List<Contract> all();

	public Contract save(Contract contract);

	public Contract find(Integer id);

	void delete(Integer id);

}
