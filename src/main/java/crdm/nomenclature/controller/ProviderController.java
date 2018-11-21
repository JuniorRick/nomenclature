package crdm.nomenclature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import crdm.nomenclature.entity.Provider;
import crdm.nomenclature.service.ProviderService;

@Controller
@RequestMapping("/provider")
public class ProviderController {

	@Autowired
	private ProviderService providerService;
	
	@GetMapping("/list")
	public String all(@ModelAttribute("provider") Provider provider, Model model) {
		
		List<Provider> providers = providerService.all();
		
		if(provider == null) {
			provider = new Provider();
		}
		
		model.addAttribute("providers", providers);
		model.addAttribute("provider", provider);
		
		return "providers";
	}
	
	
	@PostMapping("/store")
	public String save(@ModelAttribute("provider") Provider provider) {
		
		providerService.save(provider);
		
		return "redirect:/provider/list";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam("Id") int id, final RedirectAttributes redirectAttributes) {

		Provider provider= providerService.find(id);

		redirectAttributes.addFlashAttribute("provider", provider);
		
		return "redirect:/provider/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("Id") int id, Model model) {

		providerService.delete(id);
		
		return "redirect:/provider/list";
	}
	
}
