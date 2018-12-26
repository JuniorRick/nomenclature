package crdm.nomenclature.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


 public class PurchaseWrapper {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private List<String> ids;

	@NotNull
	private List<String> quantities;

	public PurchaseWrapper() {
	}

	public List<Integer> getIds() {
		return ids.stream().map(Integer::parseInt)
	            .collect(Collectors.toList());
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public List<Float> getQuantities() {
		return quantities.stream().map(Float::parseFloat)
	            .collect(Collectors.toList());
	}

	public void setQuantities(List<String> quantities) {
		this.quantities = quantities;
	}

	

}
