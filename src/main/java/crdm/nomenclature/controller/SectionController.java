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

import crdm.nomenclature.entity.Section;
import crdm.nomenclature.service.SectionService;

@Controller
@RequestMapping("/section")
public class SectionController {

	@Autowired
	private SectionService sectionService;
	
	@GetMapping("/list")
	public String all(@ModelAttribute("section") Section section, Model model) {
		
		List<Section> sections = sectionService.all();
		
		if(section == null) {
			section = new Section();
		}
		
		model.addAttribute("sections", sections);
		model.addAttribute("section", section);
		
		return "sections";
	}
	
	
	@PostMapping("/store")
	public String save(@ModelAttribute("section") Section section) {
		
		sectionService.save(section);
		
		return "redirect:/section/list";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam("Id") int id, final RedirectAttributes redirectAttributes) {

		Section section= sectionService.find(id);
		redirectAttributes.addFlashAttribute("section", section);
		
		return "redirect:/section/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("Id") int id, Model model) {

		sectionService.delete(id);
		
		return "redirect:/section/list";
	}
	
}
