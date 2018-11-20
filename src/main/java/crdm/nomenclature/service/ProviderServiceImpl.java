package crdm.nomenclature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crdm.nomenclature.dao.ProviderDAO;
import crdm.nomenclature.entity.Provider;


@Service
public class ProviderServiceImpl implements ProviderService {

	@Autowired
	private ProviderDAO providerDAO;
	
	@Override
	@Transactional
	public List<Provider> all() {
		return providerDAO.all();
	}

	@Override
	@Transactional
	public Provider save(Provider provider) {
		return providerDAO.save(provider);
	}

	@Override
	@Transactional
	public Provider find(Integer id) {
		return providerDAO.find(id);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		providerDAO.delete(id);

	}

}
