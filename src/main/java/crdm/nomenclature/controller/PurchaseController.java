package crdm.nomenclature.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import crdm.nomenclature.component.YearComponent;
import crdm.nomenclature.entity.Contract;
import crdm.nomenclature.entity.Good;
import crdm.nomenclature.entity.Section;
import crdm.nomenclature.service.ContractService;
import crdm.nomenclature.service.GoodService;
import crdm.nomenclature.service.PurchaseService;
import crdm.nomenclature.service.SectionService;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private GoodService goodService;
	
	@Autowired
	private SectionService sectionService;
	
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private YearComponent year;
	
	@GetMapping("/list")
	public String all(Model model) throws ParseException {
		

		List<Contract> contracts = contractService.all();
		model.addAttribute("contracts", contracts);
	
		List<Good> goods = goodService.all();	
		model.addAttribute("goods", goods);

		List<Section> sections = sectionService.all();
		model.addAttribute("sections", sections);
		
		model.addAttribute("year", year);
		return "purchases";
	}
	
	
	@GetMapping("/list/{contract_id}/{section_id}")
	public String contractGoods(@PathVariable("contract_id") Integer contract_id, HttpServletRequest request,
			@PathVariable("section_id") Integer section_id
			, Model model) throws ParseException {
		
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(2 * 60 * 60);
		
		String section_name = sectionService.find(section_id).getName();
		
		model.addAttribute("section_name", section_name);
		model.addAttribute("section_id", section_id);
		
		List<Contract> contracts = contractService.all();
		model.addAttribute("contracts", contracts);
	
		List<Good> goods = null;
		if(contract_id == null || contract_id == 0) {
			goods = goodService.all();
		} else {
			goods = contractService.find(contract_id).getGoods();
		}

		model.addAttribute("goods", goods);

		List<Section> sections = sectionService.all();
		model.addAttribute("sections", sections);
		
		return "purchases";
	}
	
	@GetMapping("/filter")
	public String filter( @RequestParam("section_id") Integer section_id,
			@RequestParam("contract_id") Integer contract_id, final RedirectAttributes redirectAttributes) {
	
		return "redirect:/purchase/list/" + contract_id + "/" + section_id;
		
	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("Id") int id, Model model) {

		purchaseService.delete(id);
		
		return "redirect:/purchase/request";
	}
	
}
