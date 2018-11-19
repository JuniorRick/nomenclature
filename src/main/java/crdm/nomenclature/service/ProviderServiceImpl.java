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
	public Provider save(Provider provider) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Provider get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

}
