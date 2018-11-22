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

import crdm.nomenclature.entity.Command;
import crdm.nomenclature.entity.Purchase;
import crdm.nomenclature.entity.Section;
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
	
	@GetMapping("/list")
	public String all(@ModelAttribute("order") Command order, Model model) throws ParseException {
		
		List<Command> orders = orderService.all();
		
		model.addAttribute("orders", orders);
		model.addAttribute("order", order);
		
		List<Purchase> purchases = purchaseService.all();
		model.addAttribute("purchases", purchases);

		List<Section> sections = sectionService.all();
		model.addAttribute("sections", sections);
		
		return "orders";
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
		
		orderService.save(order);
		
		
		return "redirect:/order/list";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam("Id") int id, final RedirectAttributes redirectAttributes) {

		Command order = orderService.find(id);

		redirectAttributes.addFlashAttribute("order", order);
		
		return "redirect:/order/list";
	}
	
	@GetMapping("/approve")
	public String approve(@RequestParam("Id") int id, Model model) {


		orderService.approve(id);
		
		return "redirect:/order/list";
	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("Id") int id, Model model) {

		orderService.delete(id);
		
		return "redirect:/order/list";
	}
	
}
