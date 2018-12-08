package crdm.nomenclature.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import crdm.nomenclature.entity.Command;
import crdm.nomenclature.entity.Request;
import crdm.nomenclature.service.RequestService;

@Controller
@RequestMapping("/request")
public class RequestController {
	
	
	@Autowired
	private RequestService requestService;
	
	@GetMapping("list")
	public String requestList(@ModelAttribute("request") Command order, Model model) throws ParseException {
		
		List<Request> requests = requestService.all();
		
		model.addAttribute("requests", requests);
		
		return "requestsList";
	}
	
	@GetMapping("{id}")
	public String request(@PathVariable("id") Integer id, Model model) throws ParseException {
		
		Request request = requestService.find(id);
		
		model.addAttribute("request", request);
		
		return "request";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("Id") int id, Model model) {

		requestService.delete(id);
		
		return "redirect:/request/list";
	}
	
}
