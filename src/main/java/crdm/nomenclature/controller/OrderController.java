package crdm.nomenclature.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import crdm.nomenclature.entity.Command;
import crdm.nomenclature.entity.Contract;
import crdm.nomenclature.entity.Purchase;
import crdm.nomenclature.entity.Section;
import crdm.nomenclature.rest.exception.NotFoundException;
import crdm.nomenclature.service.ContractService;
import crdm.nomenclature.service.OrderService;
import crdm.nomenclature.service.PurchaseService;
import crdm.nomenclature.service.SectionService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private SectionService sectionService;
	
	@Autowired
	private ContractService contractService;
	
	@GetMapping("/list")
	public String all(Model model) throws ParseException {
		

		List<Contract> contracts = contractService.all();
		model.addAttribute("contracts", contracts);
	
		List<Purchase> purchases = purchaseService.all();	
		model.addAttribute("purchases", purchases);

		List<Section> sections = sectionService.all();
		model.addAttribute("sections", sections);
		
		return "orders";
	}
	
	@GetMapping("/list/{contract_id}/{section_id}")
	public String contractPurchases(@PathVariable("contract_id") Integer contract_id,
			@PathVariable("section_id") Integer section_id
			, Model model) throws ParseException {
		
		String section_name = sectionService.find(section_id).getName();
		
		model.addAttribute("section_name", section_name);
		model.addAttribute("section_id", section_id);
		
		List<Contract> contracts = contractService.all();
		model.addAttribute("contracts", contracts);
	
		List<Purchase> purchases = null;
		if(contract_id == null || contract_id == 0) {
			purchases = purchaseService.all();
		} else {
			purchases = contractService.find(contract_id).getPurchases();
		}

		model.addAttribute("purchases", purchases);

		List<Section> sections = sectionService.all();
		model.addAttribute("sections", sections);
		
		return "orders";
	}
	
	@GetMapping("/filter")
	public String filter( @RequestParam("section_id") Integer section_id,
			@RequestParam("contract_id") Integer contract_id, final RedirectAttributes redirectAttributes) {
	
		return "redirect:/order/list/" + contract_id + "/" + section_id;
		
	}
	
	@GetMapping("/request")
	public String requestList(@ModelAttribute("order") Command order, Model model) throws ParseException {
		
		List<Command> orders = orderService.requests();
		
		model.addAttribute("orders", orders);
		model.addAttribute("order", order);
		
		return "requests";
	}
	
	
	
	@GetMapping("/approved")
	public String approvedList(@ModelAttribute("order") Command order, Model model) throws ParseException {
		
		List<Command> orders = orderService.approvedList();
		
		model.addAttribute("orders", orders);
		model.addAttribute("order", order);
		
		List<Purchase> purchases = purchaseService.all();
		model.addAttribute("purchases", purchases);

		List<Section> sections = sectionService.all();
		model.addAttribute("sections", sections);
		
		return "approvedOrders";
	}
	
	
	@PostMapping("/store")
	public String save(@ModelAttribute("order") Command order, 
			@RequestParam("purchase_id") int purchase_id,
			@RequestParam("section_id") int section_id) {
		
		order.setSection(sectionService.find(section_id));
		order.setPurchase(purchaseService.find(purchase_id));
	
		if(order.getQuantity() > order.getPurchase().getRemainder()) {
			throw new NotFoundException("Invalid quantity " + order.getQuantity() + ". Available: " + order.getPurchase().getRemainder());
		}
		
		orderService.save(order);
		
		
		return "redirect:/order/list";
	}
	
	
//	@PostMapping("/send/{ids}/{quantities}/{section_id}")
//	public @ResponseBody String send(	
//			@RequestParam("ids") String[] ids,
//			@RequestParam("quantities") String[] quantities,
//			@RequestParam("section_id") String section_id) {
//		
//		Section section = sectionService.find(Integer.parseInt(section_id));
//		
//		for(int ii = 0; ii < ids.length; ii++) {
//			Command order = new Command();
//			order.setQuantity(Float.parseFloat(quantities[ii]));
//			Purchase purchase = purchaseService.find(Integer.parseInt(ids[ii]));
//			order.setPurchase(purchase);
//			order.setSection(section);
//			
//		}
//		
//		
//		return "redirect:/order/list";
//	}
	
	
	
	@GetMapping("/update")
	public String update(@RequestParam("Id") int id, final RedirectAttributes redirectAttributes) {

		Command order = orderService.find(id);

		redirectAttributes.addFlashAttribute("order", order);
		
		return "redirect:/order/request";
	}
	
	@GetMapping("/approve")
	public String approve(@RequestParam("Id") int id, Model model) {
		
		Command order = orderService.find(id);
		
		if(order.getQuantity() > order.getPurchase().getRemainder()) {
			throw new NotFoundException("Invalid quantity " + order.getQuantity() + ". Available: " + order.getPurchase().getRemainder());
		}
		
		orderService.approve(id);
		
		return "redirect:/order/approved";
	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("Id") int id, Model model) {

		orderService.delete(id);
		
		return "redirect:/order/request";
	}
	
}
