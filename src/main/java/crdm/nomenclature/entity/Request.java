package crdm.nomenclature.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "requests")
public class Request {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "section_id")
	private Section section;

	@ManyToOne
	@JoinColumn(name = "contract_id")
	private Contract contract;

	@JsonIgnore
	@OneToMany(mappedBy = "request", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Purchase> purchases;

	@Column(nullable = false, columnDefinition = "boolean default false")
	private Boolean approved;

	@Column(nullable = false, columnDefinition = "boolean default false")
	private Boolean deposited;

	private Date date;

	
	
	public Boolean getDeposited() {
		return deposited;
	}

	public void setDeposited(Boolean deposited) {
		this.deposited = deposited;
	}

	public Request() {
	}

	public void add(Purchase purchase) {
		if (purchases == null) {
			purchases = new ArrayList<>();
		}
		purchases.add(purchase);
		purchase.setRequest(this);
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

}
