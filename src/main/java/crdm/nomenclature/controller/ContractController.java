package crdm.nomenclature.controller;

import java.sql.Date;
import java.text.ParseException;
import java.util.Calendar;
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

import crdm.nomenclature.entity.Contract;
import crdm.nomenclature.entity.Provider;
import crdm.nomenclature.service.ContractService;
import crdm.nomenclature.service.ProviderService;

@Controller
@RequestMapping("/contract")
public class ContractController {

	@Autowired
	private ContractService contractService;
	
	@Autowired
	private ProviderService providerService;
	
	@GetMapping("/list")
	public String all(@ModelAttribute("contract") Contract contract, Model model) throws ParseException {
		
		List<Contract> contracts = contractService.all();
		
		if(contract.getExpiryDate() == null ) {
			contract = new Contract();
			Calendar calendar = Calendar.getInstance();
			Integer year = calendar.get(Calendar.YEAR);

			contract.setExpiryDate(Date.valueOf(year + "-12-31"));
		}
		
		model.addAttribute("contracts", contracts);
		model.addAttribute("contract", contract);
		
		List<Provider> providers = providerService.all();
		model.addAttribute("providers", providers);

		return "contracts";
	}
	
	
	@PostMapping("/store")
	public String save(@ModelAttribute("contract") Contract contract, @RequestParam("provider_id") int provider_id) {
		
		contract.setProvider(providerService.find(provider_id));
		contractService.save(contract);
		
		return "redirect:/contract/list";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam("Id") int id, final RedirectAttributes redirectAttributes) {

		Contract contract = contractService.find(id);

		redirectAttributes.addFlashAttribute("contract", contract);
		
		return "redirect:/contract/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("Id") int id, Model model) {

		contractService.delete(id);
		
		return "redirect:/contract/list";
	}
	
}
