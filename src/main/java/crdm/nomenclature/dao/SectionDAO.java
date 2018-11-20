package crdm.nomenclature.dao;

import java.util.List;

import crdm.nomenclature.entity.Section;

public interface SectionDAO {
	
	public List<Section> all();
	
	public Section save(Section section);
	
	public Section find(Integer id);
	
	void delete(Integer id);
}
