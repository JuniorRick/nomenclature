package crdm.nomenclature.dao;

import java.util.List;

import crdm.nomenclature.entity.Provider;

public interface ProviderDAO {
	
	public List<Provider> all();
	
	public Provider save(Provider provider);
	
	public Provider get(Integer id);
	
	void delete(Integer id);
}
