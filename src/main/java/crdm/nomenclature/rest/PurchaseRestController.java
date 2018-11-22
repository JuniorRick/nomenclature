package crdm.nomenclature.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crdm.nomenclature.entity.Purchase;
import crdm.nomenclature.rest.exception.NotFoundException;
import crdm.nomenclature.service.PurchaseService;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseRestController {

	@Autowired
	private PurchaseService purchaseService;
	
	@CrossOrigin
	@GetMapping("")
	public List<Purchase> all() {
		
		List<Purchase> purchases = purchaseService.all();
		
		return purchases;
	}
	
	
	@GetMapping("/{id}")
	public Purchase get(@PathVariable Integer id) {

		Purchase purchase = purchaseService.find(id);
		if(purchase == null) {
			throw new NotFoundException("Purchase id not found - " + id);
		}
		
		return purchase;
	}
	
	@PostMapping("")
	public Purchase save(@RequestBody Purchase purchase) {
		
		purchase.setId(null);
		purchaseService.save(purchase);
		
		return purchase;
	}
	
	@PutMapping("")
	public Purchase update(@RequestBody Purchase purchase) {

		purchaseService.save(purchase);
		
		return purchase;
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable int id) {

		Purchase purchase = purchaseService.find(id);
		
		if(purchase == null) {
			throw new NotFoundException("Purchase id not found - " + id);
		}
		
		purchaseService.delete(id);
		return "Deleted customer id - " + id;
	}
}
