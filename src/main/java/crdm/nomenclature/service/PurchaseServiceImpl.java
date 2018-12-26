package crdm.nomenclature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crdm.nomenclature.dao.PurchaseDAO;
import crdm.nomenclature.entity.Purchase;


@Service
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	private PurchaseDAO purchaseDAO;
	
	@Override
	@Transactional
	public List<Purchase> all() {
		return purchaseDAO.all();
	}

	@Override
	@Transactional
	public Purchase save(Purchase purchase) {
		return purchaseDAO.save(purchase);
	}
	
	@Override
	@Transactional
	public Purchase find(Integer id) {
		return purchaseDAO.find(id);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		purchaseDAO.delete(id);
	}

	@Override
	@Transactional
	public List<Purchase> requests() {
		// TODO Auto-generated method stub
		return purchaseDAO.requests();
	}

}
