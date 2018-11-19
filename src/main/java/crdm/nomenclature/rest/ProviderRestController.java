package crdm.nomenclature.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crdm.nomenclature.entity.Provider;
import crdm.nomenclature.service.ProviderService;

@RestController
@RequestMapping("/api")
public class ProviderRestController {

	@Autowired
	private ProviderService providerService;
	
	@CrossOrigin
	@GetMapping("/providers")
	public List<Provider> all() {
		
		List<Provider> providers = providerService.all();
		
		return providers;
	}
	
}
