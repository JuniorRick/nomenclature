package crdm.nomenclature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import crdm.nomenclature.entity.Provider;
import crdm.nomenclature.service.ProviderService;

@Controller
@RequestMapping("/provider")
public class ProviderController {

	@Autowired
	private ProviderService providerService;
	
	@GetMapping("/list")
	public String all(Model model) {
		
		List<Provider> providers = providerService.all();
		Provider provider = new Provider();
		
		model.addAttribute("providers", providers);
		model.addAttribute("provider", provider);
		
		return "providers";
	}
	
}
