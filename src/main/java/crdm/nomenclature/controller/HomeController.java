package crdm.nomenclature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import crdm.nomenclature.entity.Settings;
import crdm.nomenclature.entity.User;
import crdm.nomenclature.service.RequestService;
import crdm.nomenclature.service.SettingsService;
import crdm.nomenclature.service.UserService;

@Controller

public class HomeController {
	
	@Autowired
	private RequestService requestService;
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private SettingsService settingsService;
	
	@GetMapping("/")
	public String index(Model model) {
		
		Integer approvedCount = requestService.count(true, false);
		Integer requestsCount = requestService.count(false, false);
		Integer depositedCount = requestService.count(true, true);
		
		
		Settings settings = settingsService.all();
		
		model.addAttribute("settings", settings);
		
		model.addAttribute("approvedCount", approvedCount);
		model.addAttribute("requestsCount", requestsCount);
		model.addAttribute("depositedCount", depositedCount);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("privileges", authentication.getAuthorities());
		
		User user = userService.findByEmail(authentication.getName());
		
		model.addAttribute("user", user);
		System.out.println("");
		
		
		
		
		return "index";
	}
	
	
}
