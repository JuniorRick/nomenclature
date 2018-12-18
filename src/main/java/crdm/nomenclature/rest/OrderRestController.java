package crdm.nomenclature.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crdm.nomenclature.controller.OrderWrapper;
import crdm.nomenclature.entity.Command;
import crdm.nomenclature.entity.Purchase;
import crdm.nomenclature.entity.Request;
import crdm.nomenclature.entity.Section;
import crdm.nomenclature.rest.exception.NotFoundException;
import crdm.nomenclature.service.PurchaseService;
import crdm.nomenclature.service.RequestService;
import crdm.nomenclature.service.SectionService;

@RestController
@RequestMapping("/api/orders/")
public class OrderRestController {

	@Autowired
	private SectionService sectionService;

	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private RequestService requestService;

	@PostMapping("send/{id}")
	public Request send(@PathVariable("id") Integer id, @RequestBody OrderWrapper wrapper) {

		Section section = sectionService.find(id);
		if (section == null) {
			throw new NotFoundException("Section id not found - " + id);
		}

		Request request = new Request();
		request.setSection(section);
		request.setApproved(false);

		Purchase purchase = purchaseService.find(wrapper.getIds().get(0));
		request.setContract(purchase.getContract());

		for (int ii = 0; ii < wrapper.getIds().size(); ii++) {
			Command order = new Command();

			Float quantity = wrapper.getQuantities().get(ii);

			order.setQuantity(quantity);

			purchase = purchaseService.find(wrapper.getIds().get(ii));
			order.setPurchase(purchase);
			order.setRequest(request);
			
			request.add(order);
		}

		requestService.save(request);

		return request;
	}

	@PostMapping("approve/{id}")
	public List<Command> approve(@PathVariable("id") Integer id, @RequestBody OrderWrapper wrapper) {

		Request request = requestService.find(id);
		if (request == null) {
			throw new NotFoundException("Request not found - " + id);
		}

		List<Command> orders = request.getOrders();

		for (int ii = 0; ii < orders.size(); ii++) {
			Command order = orders.get(ii);
			int index = wrapper.getIds().indexOf(order.getId());

			Float quantity = wrapper.getQuantities().get(index);
			order.setQuantity(quantity);

			Float remainder = order.getPurchase().getRemainder() - quantity;			
			Purchase purchase = order.getPurchase();
			purchase.setRemainder(remainder);
			purchaseService.save(purchase);
			order.setPurchase(purchase);
			
		}

		request.setApproved(true);
		requestService.save(request);

		return orders;
	}
}
