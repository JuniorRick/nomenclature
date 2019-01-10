package crdm.nomenclature.controller;

import java.util.Arrays;
import java.util.List;

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
//		model.addAttribute("privileges", privilegeNames);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		User user = userService.findByEmail(authentication.getName());
		
		model.addAttribute("user", user);
		System.out.println("");
		
		return "index";
	}
	
	
		public List<String> privilegeNames() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
//		User currentUser = userDAO.findByEmail(email);		
//		List<Role> roles = new ArrayList<Role>(currentUser.getRoles());
//		List<Privilege> privileges = new ArrayList<Privilege>(roles.get(0).getPrivileges());
//		
//		List<String> privilegeNames = new ArrayList<>();
//		for(Privilege privilege: privileges) {
//			privilegeNames.add(privilege.getName());
//		}
		
//		return privilegeNames;
		return Arrays.asList("TEST");
		
	}
	
}
