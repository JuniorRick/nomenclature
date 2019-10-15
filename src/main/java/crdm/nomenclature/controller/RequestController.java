package crdm.nomenclature.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
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

import crdm.nomenclature.component.YearComponent;
import crdm.nomenclature.entity.Good;
import crdm.nomenclature.entity.Purchase;
import crdm.nomenclature.entity.Request;
import crdm.nomenclature.entity.Section;
import crdm.nomenclature.entity.Settings;
import crdm.nomenclature.rest.exception.NotFoundException;
import crdm.nomenclature.service.GoodService;
import crdm.nomenclature.service.RequestService;
import crdm.nomenclature.service.SectionService;
import crdm.nomenclature.service.SettingsService;

@Controller
@RequestMapping("/request")
public class RequestController {

	@Autowired
	private RequestService requestService;
	
	@Autowired 
	private SectionService sectionService;
	
	@Autowired 
	private GoodService goodService;
	
	@Autowired
	private SettingsService settingsService;
	
	@Autowired
	private YearComponent year;
	
	
	@GetMapping("list")
	public String list(@ModelAttribute("request") Purchase purchase, Model model) throws ParseException {

		List<Request> requests = requestService.requestList();

		model.addAttribute("requests", requests);
		model.addAttribute("year", year);
		
		return "requestList";
	}

	@GetMapping("/view/{id}")
	public String approved(@PathVariable("id") Integer id, Model model) throws ParseException {

		Request request = requestService.find(id);

		model.addAttribute("request", request);
		model.addAttribute("year", year);
		
		return "approved";
	}
	
	@GetMapping("/cancel/{id}")
	public String cancel(@PathVariable("id") Integer id, Model model) throws ParseException {

		Request request = requestService.find(id);

		request.setApproved(false);

		
		List<Purchase> purchases = request.getPurchases();
		

		for (Purchase purchase: purchases) {

			
			Good good = purchase.getGood();
			Float quantity = purchase.getQuantity();

			good.setRemainder(quantity + good.getRemainder());
			
			goodService.save(good);
			
			purchase.setGood(good);
			
		}
			
		requestService.save(request);
		

		return "redirect:/request/approved";
	
	}
	
	
	@GetMapping("/pdf/{id}")
	public void pdf(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response)
			throws ParseException, DocumentException, URISyntaxException, IOException {

		Settings settings = settingsService.all();
		List <Purchase> purchases = requestService.find(id).getPurchases()
				.stream().filter(o -> o.getQuantity() > 0.0f).collect(Collectors.toList());
				
		PdfGenerator pdfGenerator = new PdfGenerator(purchases, settings);
		

		String filePath = "/var/www/crdm/data/public/contract_" + System.currentTimeMillis() + ".pdf";
//		String filePath = "/home/estinca/contract_" + System.currentTimeMillis() + ".pdf";
		pdfGenerator.generatePDF(filePath); 
		
		File file = new File(filePath);
		
		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		
		String mimeType = URLConnection.guessContentTypeFromStream(inputStream);
		
		if(mimeType == null) {
			mimeType = "application/octet-stream";
		}
		response.setContentType(mimeType);
		response.setContentLength((int) file.length());
		response.setHeader( "Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
		
		
		FileCopyUtils.copy(inputStream, response.getOutputStream());
		

	}
	
	
	@GetMapping("{id}")
	public String request(@PathVariable("id") Integer id, Model model) throws ParseException {

		Request request = requestService.find(id);

		model.addAttribute("request", request);
		
		return "request";
	}

	@GetMapping("/approved")
	public String approvedList(@ModelAttribute("purchase") Purchase purchase, Model model) throws ParseException {

		List<Request> requests = requestService.approvedList();

		model.addAttribute("requests", requests);

		return "approvedList";
	}
	
	
	@GetMapping("/deposit")
	public String depositList(@ModelAttribute("purchase") Purchase purchase, Model model) throws ParseException {

		List<Request> requests = requestService.depositedList();

		model.addAttribute("requests", requests);

		return "depositList";
	}
	
	@GetMapping("/depositing/{id}")
	public String depositing(@PathVariable("id") Integer id, @RequestParam("details") String details, Model model) throws ParseException {

		Request request = requestService.find(id);

		request.setDeposited(true);
		request.setApproved(true);
		request.setDetails(details);
		
		requestService.save(request);

		return "redirect:/request/approved";
	
	}
	
	@GetMapping("/deposit/view/{id}")
	public String deposited(@PathVariable("id") Integer id, Model model) throws ParseException {

		Request request = requestService.find(id);

		model.addAttribute("request", request);

		return "deposit";
	}
	
	@GetMapping("/deposit/cancel/{id}")
	public String cancelDepositing(@PathVariable("id") Integer id, Model model) throws ParseException {

		Request request = requestService.find(id);

		request.setDeposited(false);
		request.setApproved(true);
			
		requestService.save(request);

		return "redirect:/request/approved";
	
	}
	
	@CrossOrigin
	@ResponseBody
	@PostMapping("send/{id}")
	public Request send(@PathVariable("id") Integer id, @RequestBody PurchaseWrapper wrapper) {

		Section section = sectionService.find(id);
		if (section == null) {
			throw new NotFoundException("Section id not found - " + id);
		}

		Request request = new Request();
		request.setSection(section);
		
		java.util.Date curDate = new java.util.Date();
		request.setDate(new Date(curDate.getTime()));
		request.setApproved(false);
		request.setDeposited(false);
		
		Good good = goodService.find(wrapper.getIds().get(0));
		request.setContract(good.getContract());

		for (int ii = 0; ii < wrapper.getIds().size(); ii++) {
			Purchase purchase = new Purchase();

			Float quantity = wrapper.getQuantities().get(ii);

			purchase.setQuantity(quantity);

			good = goodService.find(wrapper.getIds().get(ii));
			purchase.setGood(good);
			purchase.setRequest(request);
			request.add(purchase);
		}

		requestService.save(request);
		
		return request;
	}
	
	@CrossOrigin
	@ResponseBody
	@PostMapping("approve/{id}")
	public List<Purchase> approve(@PathVariable("id") Integer id, @RequestBody PurchaseWrapper wrapper) {

		Request request = requestService.find(id);
		if (request == null) {
			throw new NotFoundException("Request not found - " + id);
		}

		List<Purchase> purchases = request.getPurchases();

		for (int ii = 0; ii < purchases.size(); ii++) {
			Purchase purchase = purchases.get(ii);
			int index = wrapper.getIds().indexOf(purchase.getId());

			Float quantity = wrapper.getQuantities().get(index);
			purchase.setQuantity(quantity);
			
			Float remainder = purchase.getGood().getRemainder() - quantity;			
			Good good = purchase.getGood();
			if(remainder < 0 ) {
				remainder = 0.0f;
				quantity = purchase.getQuantity();
			}

			good.setRemainder(remainder);
			goodService.save(good);
			purchase.setGood(good);
			
		}

		request.setApproved(true);
		requestService.save(request);

		return purchases;
	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("Id") Integer id, Model model) {

		requestService.delete(id);
		
		return "redirect:/request/approved";
	}
}
