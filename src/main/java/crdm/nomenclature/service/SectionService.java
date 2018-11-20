package crdm.nomenclature.service;

import java.util.List;

import crdm.nomenclature.entity.Section;

public interface SectionService {
	public List<Section> all();

	public Section save(Section provider);

	public Section find(Integer id);

	void delete(Integer id);

}
