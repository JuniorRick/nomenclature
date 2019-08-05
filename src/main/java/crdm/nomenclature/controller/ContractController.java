package crdm.nomenclature.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import crdm.nomenclature.entity.Contract;
import crdm.nomenclature.entity.Good;
import crdm.nomenclature.entity.Provider;
import crdm.nomenclature.entity.Purchase;
import crdm.nomenclature.entity.Request;
import crdm.nomenclature.service.ContractService;
import crdm.nomenclature.service.ProviderService;
import crdm.nomenclature.service.RequestService;

@Controller
@RequestMapping("/contract")
public class ContractController {

	@Autowired
	private ContractService contractService;
	
	@Autowired
	private ProviderService providerService;
	
	@Autowired
	private RequestService requestService;
	
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
	
	@ResponseBody
	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	@GetMapping("/xls/deposit.xlsx")
	public ResponseEntity<InputStreamResource> report(@RequestParam("Id") int id) throws IOException {
		
		Contract contract = contractService.find(id);
		
		List<Request> listByContract = requestService.depositedList()
				.stream().filter(item -> item.getContract().getId() == contract.getId())
//		.filter(item -> item.getContract().getNumber() == contract.getNumber())
				.collect(Collectors.toList());
		
		System.out.println("getting contract");
		System.out.println(contract.getProvider().getName() + " " + contract.getNumber());
		
		
		ByteArrayInputStream inByteStream;
		XLSGenerator xlsGenerator = new XLSGenerator();
		
		List<Purchase> purchases = new ArrayList<Purchase>();

		for(Request request: listByContract) {
			purchases.addAll(request.getPurchases());
		}
		
		inByteStream = xlsGenerator.depositToXLS(purchases, contract);
		
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=deposit.xlsx");
		
       return ResponseEntity
               .ok()
               .headers(headers)
               .body(new InputStreamResource(inByteStream));
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
