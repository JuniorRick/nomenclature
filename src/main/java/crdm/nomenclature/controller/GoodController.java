package crdm.nomenclature.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import crdm.nomenclature.entity.Contract;
import crdm.nomenclature.entity.Good;
import crdm.nomenclature.service.ContractService;
import crdm.nomenclature.service.GoodService;

@Controller
@RequestMapping("/good")
public class GoodController {

	@Autowired
	private GoodService goodService;

	@Autowired
	private ContractService contractService;

	@GetMapping("/list")
	public String all(@ModelAttribute("good") Good good, @ModelAttribute("contract") Contract contract, Model model)
			throws ParseException, JsonProcessingException {

		List<Good> goods = null;

		if (contract.getId() != null) {
			goods = contract.getGoods();
		} else {
			goods = goodService.all();
		}
		model.addAttribute("goods", goods);
		model.addAttribute("good", good);

		List<Contract> contracts = contractService.all();
		ObjectMapper om = new ObjectMapper();
		model.addAttribute("contractsList", om.writeValueAsString(contracts));
		model.addAttribute("contracts", contracts);
		model.addAttribute("contract", contract);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("privileges", authentication.getAuthorities());

		return "goods";
	}

	@PostMapping("/store")
	public String save(@ModelAttribute("good") Good good, @RequestParam("contract_id") int contract_id,
			final RedirectAttributes redirectAttributes) {

		Contract contract = contractService.find(contract_id);

		if(good.getId() == null) {
			good.setRemainder(good.getQuantity());
		} else {
			Good g = goodService.find(good.getId());
			good.setRemainder(good.getQuantity() - g.getQuantity() + g.getRemainder());
		}
		
		contract.add(good);
		redirectAttributes.addFlashAttribute("contract", contract);

		goodService.save(good);

		return "redirect:/good/list";
	}

	@GetMapping("/update")
	public String update(@RequestParam("Id") int id, final RedirectAttributes redirectAttributes) {

		Good good = goodService.find(id);

		redirectAttributes.addFlashAttribute("good", good);

		return "redirect:/good/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("Id") int id, Model model) {

		goodService.delete(id);

		return "redirect:/good/list";
	}

}
