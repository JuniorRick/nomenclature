package crdm.nomenclature.service;

import crdm.nomenclature.entity.Settings;

public interface SettingsService {
	
	public Settings all();
	
	public Settings save(Settings settings);
}
