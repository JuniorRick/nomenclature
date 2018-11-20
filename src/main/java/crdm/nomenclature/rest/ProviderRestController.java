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

import crdm.nomenclature.entity.Provider;
import crdm.nomenclature.service.ProviderService;

@RestController
@RequestMapping("/api/providers")
public class ProviderRestController {

	@Autowired
	private ProviderService providerService;
	
	@CrossOrigin
	@GetMapping("")
	public List<Provider> all() {
		
		List<Provider> providers = providerService.all();
		
		return providers;
	}
	
	
	@GetMapping("/{id}")
	public Provider get(@PathVariable Integer id) {

		Provider provider = providerService.find(id);
		if(provider == null) {
//			throw new ProviderNotFoundException("Provider id not found - " + id);
			throw new RuntimeException();
		}
		
		return provider;
	}
	
	
	@PostMapping("")
	public Provider save(@RequestBody Provider provider) {
		
		provider.setId(null);
		providerService.save(provider);
		
		return provider;
	}
	
	@PutMapping("")
	public Provider update(@RequestBody Provider provider) {

		providerService.save(provider);
		
		return provider;
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable int id) {

		Provider provider = providerService.find(id);
		
		if(provider == null) {
//			throw new ProviderNotFoundException("Provider id not found - " + id);
			throw new RuntimeException();
		}
		
		providerService.delete(id);
		return "Deleted customer id - " + id;
	}
}
