package crdm.nomenclature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crdm.nomenclature.dao.GoodDAO;
import crdm.nomenclature.entity.Good;


@Service
public class GoodServiceImpl implements GoodService {

	@Autowired
	private GoodDAO goodDAO;
	
	@Override
	@Transactional
	public List<Good> all() {
		return goodDAO.all();
	}

	@Override
	@Transactional
	public Good save(Good good) {
		return goodDAO.save(good);
	}

	@Override
	@Transactional
	public Good find(Integer id) {
		return goodDAO.find(id);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		goodDAO.delete(id);

	}

}
