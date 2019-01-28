package crdm.nomenclature.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crdm.nomenclature.dao.SettingsDAO;
import crdm.nomenclature.entity.Settings;


@Service
public class SettingsServiceImpl implements SettingsService {

	@Autowired
	private SettingsDAO settingsDAO;
	
	
	@Override
	@Transactional
	public Settings all() {
		return settingsDAO.all();
	}

	@Override
	@Transactional
	public Settings save(Settings settings) {
		return settingsDAO.save(settings);
	}

}
