package crdm.nomenclature.controller;

import java.text.ParseException;
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
import crdm.nomenclature.entity.Purchase;
import crdm.nomenclature.rest.exception.NotFoundException;
import crdm.nomenclature.service.ContractService;
import crdm.nomenclature.service.PurchaseService;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private ContractService contractService;

	
	@GetMapping("/list")
	public String all(@ModelAttribute("purchase") Purchase purchase, Model model) throws ParseException {
		
		List<Purchase> purchases = purchaseService.all();
		model.addAttribute("purchases", purchases);
		model.addAttribute("purchase", purchase);
		
		List<Contract> contracts = contractService.all();
		model.addAttribute("contracts", contracts);

		return "purchases";
	}
	
	
	@PostMapping("/store")
	public String save(@ModelAttribute("purchase") Purchase purchase, 
			@RequestParam("contract_id") int contract_id) {
		
		Contract contract = contractService.find(contract_id);

		if(purchase.getId() == null) {
			purchase.setOld_quantity(purchase.getQuantity());
			purchase.setRemainder(purchase.getQuantity());
		}else {
			Float remainder = purchase.getOld_quantity() - purchase.getQuantity();
			if(purchase.getRemainder() - remainder < 0) {
				throw new NotFoundException("Invalid update:  " 
						+ (purchase.getOld_quantity() - purchase.getRemainder()) + " already ordered. "
						+ "Set quantity at least " + purchase.getRemainder());
			}
			purchase.setRemainder(purchase.getRemainder() - remainder);
			purchase.setOld_quantity(purchase.getQuantity());
		}
		contract.add(purchase);									
		
		purchaseService.save(purchase);
		
		
		return "redirect:/purchase/list";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam("Id") int id, final RedirectAttributes redirectAttributes) {

		Purchase purchase = purchaseService.find(id);

		redirectAttributes.addFlashAttribute("purchase", purchase);
		
		return "redirect:/purchase/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("Id") int id, Model model) {

		purchaseService.delete(id);
		
		return "redirect:/purchase/list";
	}
	
}
