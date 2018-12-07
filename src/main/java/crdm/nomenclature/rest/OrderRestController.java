package crdm.nomenclature.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crdm.nomenclature.entity.Command;
import crdm.nomenclature.entity.Purchase;
import crdm.nomenclature.entity.Section;
import crdm.nomenclature.rest.exception.NotFoundException;
import crdm.nomenclature.service.OrderService;
import crdm.nomenclature.service.PurchaseService;
import crdm.nomenclature.service.SectionService;

@RestController
@RequestMapping("/api/orders/")
public class OrderRestController {

	@Autowired
	private SectionService sectionService;
	
	@Autowired 
	private PurchaseService purchaseService;
	
	@Autowired
	private OrderService orderService;
	

	
	@PostMapping("send/{id}")
	public List<Command> send(@PathVariable("id") Integer id,
			@RequestBody OrderWrapper wrapper){

		Section section = sectionService.find(id);
		if(section == null) {
			throw new NotFoundException("Section id not found - " + id);
		}
		
		List<Command> orders = new ArrayList<>();
		
		for(int ii = 0; ii < wrapper.getIds().size(); ii++) {
			Command order = new Command();
			order.setQuantity(Float.parseFloat(wrapper.getQuantities().get(ii)));
			Purchase purchase = purchaseService.find(Integer.parseInt(wrapper.getIds().get(ii)));
			order.setPurchase(purchase);
			order.setSection(section);
			
			orderService.save(order);
		}
		
		return orders;
	}
	

}
