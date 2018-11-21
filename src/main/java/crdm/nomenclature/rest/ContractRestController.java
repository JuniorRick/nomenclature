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

import crdm.nomenclature.entity.Contract;
import crdm.nomenclature.rest.exception.NotFoundException;
import crdm.nomenclature.service.ContractService;

@RestController
@RequestMapping("/api/contracts")
public class ContractRestController {

	@Autowired
	private ContractService contractService;
	
	@CrossOrigin
	@GetMapping("")
	public List<Contract> all() {
		
		List<Contract> contracts = contractService.all();
		
		return contracts;
	}
	
	
	@GetMapping("/{id}")
	public Contract get(@PathVariable Integer id) {

		Contract contract = contractService.find(id);
		if(contract == null) {
			throw new NotFoundException("Contract id not found - " + id);
		}
		
		return contract;
	}
	
	
	@PostMapping("")
	public Contract save(@RequestBody Contract contract) {
		
		contract.setId(null);
		contractService.save(contract);
		
		return contract;
	}
	
	@PutMapping("")
	public Contract update(@RequestBody Contract contract) {

		contractService.save(contract);
		
		return contract;
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable int id) {

		Contract contract = contractService.find(id);
		
		if(contract == null) {
			throw new NotFoundException("Contract id not found - " + id);
		}
		
		contractService.delete(id);
		return "Deleted customer id - " + id;
	}
}
