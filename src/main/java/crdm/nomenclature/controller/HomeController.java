package crdm.nomenclature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import crdm.nomenclature.entity.User;
import crdm.nomenclature.service.RequestService;
import crdm.nomenclature.service.UserService;

@Controller

public class HomeController {
	
	@Autowired
	private RequestService requestService;
	
	@Autowired 
	private UserService userService;
	
	
	@GetMapping("/")
	public String index(Model model) {
		
		Integer approvedCount = requestService.count(true);
		Integer requestsCount = requestService.count(false);
		
		
		model.addAttribute("approvedCount", approvedCount);
		model.addAttribute("requestsCount", requestsCount);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("privileges", authentication.getAuthorities());
		
		User user = userService.findByEmail(authentication.getName());
		
		model.addAttribute("user", user);
		System.out.println("");
		
		
		
		
		return "index";
	}
	
	
}
