package crdm.nomenclature.dao;

import java.util.List;

import crdm.nomenclature.entity.Contract;

public interface ContractDAO {
	
	public List<Contract> all();
	
	public Contract save(Contract contract);
	
	public Contract find(Integer id);
	
	void delete(Integer id);
}
