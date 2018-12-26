package crdm.nomenclature.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "goods")
public class Good {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "contract_id")
	private Contract contract;

	private String good;

	private Float quantity;

	private Float remainder;

	private Float old_quantity;

	private String unit;

	public Good() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public String getGood() {
		return good;
	}

	public void setGood(String good) {
		this.good = good;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}
	
	public Float getOld_quantity() {
		return old_quantity;
	}

	public void setOld_quantity(Float old_quantity) {
		this.old_quantity = old_quantity;
	}

	public Float getRemainder() {
		return remainder;
	}

	public void setRemainder(Float remainder) {
		this.remainder = remainder;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
