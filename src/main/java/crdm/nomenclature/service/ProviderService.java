package crdm.nomenclature.service;

import java.util.List;

import crdm.nomenclature.entity.Provider;

public interface ProviderService {
	public List<Provider> all();

	public Provider save(Provider provider);

	public Provider get(Integer id);

	void delete(Integer id);

}
