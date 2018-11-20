package crdm.nomenclature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crdm.nomenclature.dao.SectionDAO;
import crdm.nomenclature.entity.Section;


@Service
public class SectionServiceImpl implements SectionService {

	@Autowired
	private SectionDAO sectionDAO;
	
	@Override
	@Transactional
	public List<Section> all() {
		return sectionDAO.all();
	}

	@Override
	@Transactional
	public Section save(Section section) {
		return sectionDAO.save(section);
	}

	@Override
	@Transactional
	public Section find(Integer id) {
		return sectionDAO.find(id);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		sectionDAO.delete(id);

	}

}
