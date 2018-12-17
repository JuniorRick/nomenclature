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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.text.DocumentException;

import crdm.nomenclature.entity.Command;
import crdm.nomenclature.entity.Request;
import crdm.nomenclature.service.RequestService;

@Controller
@RequestMapping("/request")
public class RequestController {

	@Autowired
	private RequestService requestService;

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

	@GetMapping("/delete")
	public String delete(@RequestParam("Id") Integer id, Model model) {

		requestService.delete(id);
		
		return "redirect:/request/list";
	}
}
