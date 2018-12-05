package crdm.nomenclature.rest;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


 public class OrderWrapper {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private List<String> ids;

	@NotNull
	private List<String> quantities;

	public OrderWrapper() {
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public List<String> getQuantities() {
		return quantities;
	}

	public void setQuantities(List<String> quantities) {
		this.quantities = quantities;
	}

	

}
