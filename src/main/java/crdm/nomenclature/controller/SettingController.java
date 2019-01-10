package crdm.nomenclature.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import crdm.nomenclature.entity.Role;
import crdm.nomenclature.entity.User;
import crdm.nomenclature.service.RoleService;
import crdm.nomenclature.service.UserService;

@Controller
@RequestMapping("settings")
public class SettingController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("")
	public String settings() {
		return "settings/settings";
	}
	
	
	@GetMapping("users")
	public String users(@ModelAttribute("user") User user, Model model) {
		
		List<User> users = userService.all();
		List<Role> roles = roleService.all();
		
		model.addAttribute("users", users);
		model.addAttribute("roles", roles);
		model.addAttribute("user", user);
		
		return "settings/users";
	}
	
	
	@GetMapping("pdf")
	public String pdf() {
		return "settings/pdf";
	}
	
	@PostMapping("user/store")
	public String save(@ModelAttribute("user") User user, @RequestParam("role_name") String role_name) {
		
		user.setRoles(Arrays.asList(roleService.findByName(role_name)));
		
		if(userService.findByEmail(user.getEmail()) == null || user.getPassword() != userService.findByEmail(user.getEmail()).getPassword()) {			
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		userService.save(user);

		 
		return "redirect:/settings/users";
	}
	
	@GetMapping("user/update")
	public String update(@RequestParam("Email")String email, final RedirectAttributes redirectAttributes) {

		User user = userService.findByEmail(email);
		redirectAttributes.addFlashAttribute("user", user);
		
		return "redirect:/settings/users";
	}
	
	@GetMapping("user/delete")
	public String delete(@RequestParam("Email") String email, Model model) {
		
		User user = userService.findByEmail(email);
		userService.delete(user);
		
		return "redirect:/settings/users";
	}
	
}
