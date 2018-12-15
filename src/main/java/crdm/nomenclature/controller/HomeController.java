package crdm.nomenclature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import crdm.nomenclature.service.RequestService;

@Controller

public class HomeController {
	
	@Autowired
	private RequestService requestService;
	
	@GetMapping("/")
	public String index(Model model) {
		
		Integer approvedCount = requestService.count(true);
		Integer requestsCount = requestService.count(false);
		
		model.addAttribute("approvedCount", approvedCount);
		model.addAttribute("requestsCount", requestsCount);
		
		return "index";
	}
	
}
