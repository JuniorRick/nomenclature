package crdm.nomenclature.dao;

import java.util.List;

import crdm.nomenclature.entity.Purchase;

public interface PurchaseDAO {
	
	public List<Purchase> all();
	
	public Purchase save(Purchase purchase);
	
	public Purchase find(Integer id);
	
	void delete(Integer id);
}
