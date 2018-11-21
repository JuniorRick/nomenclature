package crdm.nomenclature.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crdm.nomenclature.entity.Section;
import crdm.nomenclature.rest.exception.NotFoundException;
import crdm.nomenclature.service.SectionService;

@RestController
@RequestMapping("/api/sections")
public class SectionRestController {

	@Autowired
	private SectionService sectionService;
	
	@GetMapping("")
	public List<Section> all() {
		
		return sectionService.all();
	}
	
	@GetMapping("/{id}")
	public Section get(@PathVariable Integer id) {

		Section section = sectionService.find(id);
		if(section == null) {
			throw new NotFoundException("Section id not found - " + id);
		}
		
		return section;
	}
	
	
	@PostMapping("")
	public Section save(@RequestBody Section section) {
		
		section.setId(null);
		sectionService.save(section);
		
		return section;
	}
	
	@PutMapping("")
	public Section update(@RequestBody Section section) {

		sectionService.save(section);
		
		return section;
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable int id) {

		Section section = sectionService.find(id);
		
		if(section == null) {
			throw new NotFoundException("Section id not found - " + id);
		}
		
		sectionService.delete(id);
		return "Deleted customer id - " + id;
	}
	
}
