package crdm.nomenclature.controller;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.text.DocumentException;

import crdm.nomenclature.entity.Command;
import crdm.nomenclature.entity.Purchase;
import crdm.nomenclature.entity.Request;
import crdm.nomenclature.entity.Section;
import crdm.nomenclature.rest.exception.NotFoundException;
import crdm.nomenclature.service.OrderService;
import crdm.nomenclature.service.PurchaseService;
import crdm.nomenclature.service.RequestService;
import crdm.nomenclature.service.SectionService;

@Controller
@RequestMapping("/request")
public class RequestController {

	@Autowired
	private RequestService requestService;
	
	@Autowired 
	private SectionService sectionService;
	
	@Autowired 
	private PurchaseService purchaseService;

	@Autowired OrderService orderService;
	
	@GetMapping("list")
	public String list(@ModelAttribute("request") Command order, Model model) throws ParseException {

		List<Request> requests = requestService.requestList();

		model.addAttribute("requests", requests);

		return "requestList";
	}

	@GetMapping("/view/{id}")
	public String approved(@PathVariable("id") Integer id, Model model) throws ParseException {

		Request request = requestService.find(id);

		model.addAttribute("request", request);

		return "approved";
	}
	
	@GetMapping("/cancel/{id}")
	public String cancel(@PathVariable("id") Integer id, Model model) throws ParseException {

		Request request = requestService.find(id);

		request.setApproved(false);

		
		List<Command> orders = request.getOrders();
		

		for (Command order: orders) {

			
			Purchase purchase = order.getPurchase();
			Float quantity = order.getQuantity();

			purchase.setRemainder(quantity + purchase.getRemainder());
			
			purchaseService.save(purchase);
			
			order.setPurchase(purchase);
//			orderService.save(order);
			
		}

		requestService.save(request);
		

		return "redirect:/request/approved";
	
	}

	@GetMapping("/pdf/{id}")
	public String pdf(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response)
			throws ParseException, DocumentException, URISyntaxException, IOException {

		
		PdfGenerator pdfGenerator = new PdfGenerator(requestService.find(id).getOrders()
				.stream().filter(o -> o.getQuantity() > 0.0f).collect(Collectors.toList()));
		
		
		JFrame parentFrame = new JFrame();
		 
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");   
		 
		int userSelection = fileChooser.showSaveDialog(parentFrame);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    if(!fileToSave.getAbsolutePath().endsWith(".pdf"))
		    	pdfGenerator.generatePDF(fileToSave.getAbsolutePath() + ".pdf");
		    else pdfGenerator.generatePDF(fileToSave.getAbsolutePath());
		}
		
		return "redirect:/request/approved";

	}

	@GetMapping("{id}")
	public String request(@PathVariable("id") Integer id, Model model) throws ParseException {

		Request request = requestService.find(id);

		model.addAttribute("request", request);

		return "request";
	}

	@GetMapping("/approved")
	public String approvedList(@ModelAttribute("order") Command order, Model model) throws ParseException {

		List<Request> requests = requestService.approvedList();

		model.addAttribute("requests", requests);

		return "approvedList";
	}

	@CrossOrigin
	@ResponseBody
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
	
	@CrossOrigin
	@ResponseBody
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
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("Id") Integer id, Model model) {

		requestService.delete(id);
		
		return "redirect:/request/approved";
	}
}
