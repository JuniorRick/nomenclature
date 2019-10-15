package crdm.nomenclature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import crdm.nomenclature.component.YearComponent;
import crdm.nomenclature.entity.Settings;
import crdm.nomenclature.entity.User;
import crdm.nomenclature.service.ContractService;
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
	
	@Autowired
	private YearComponent year;
	
	@Autowired
	private ContractService contractService;
	
	@GetMapping("/")
	public String index(Model model, @RequestParam(value="year", required=false) Integer y) {
		
		Integer approvedCount = requestService.count(true, false);
		Integer requestsCount = requestService.count(false, false);
		Integer depositedCount = requestService.count(true, true);
		
		
		Settings settings = settingsService.all();
		
		List<Integer> years = contractService.years();
		
		model.addAttribute("settings", settings);
		model.addAttribute("years", years);
		
		
		if(y != null) {
			year.setYear(y);
		}
		model.addAttribute("year", year);
		model.addAttribute("approvedCount", approvedCount);
		model.addAttribute("requestsCount", requestsCount);
		model.addAttribute("depositedCount", depositedCount);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("privileges", authentication.getAuthorities());
		
		User user = userService.findByEmail(authentication.getName());
		
		model.addAttribute("user", user);

		return "index";
	}
	
}
