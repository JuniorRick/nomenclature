package crdm.nomenclature.service;

import java.util.List;

import crdm.nomenclature.entity.Good;

public interface GoodService {
	public List<Good> all();

	public Good save(Good good);

	public Good find(Integer id);

	void delete(Integer id);

}
