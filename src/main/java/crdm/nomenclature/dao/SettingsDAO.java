package crdm.nomenclature.dao;

import crdm.nomenclature.entity.Settings;

public interface SettingsDAO {
	
	public Settings all();
	
	public Settings save(Settings settings);
}
