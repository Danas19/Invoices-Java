package com.vtvpmc.DanasMikelionis.invoice.model;

import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Invoice {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String number;
	private Date writingDate;
	private String whatCompanyWrote;
	private String reciever;
	
	@OneToMany(mappedBy = "invoice", cascade=CascadeType.ALL)
	private Set<Item> items = new HashSet<>();
	
	public Collection<Item> getItems() {
		return this.items;
	}
	
	public void addItem(Item item) {
		this.items.add(item);
		item.setInvoice(this); 
	}
	
	public Invoice() { }
	
	public Invoice(String number, Date writingDate, String whatCompanyWrote, String reciever) {
		this.number = number;
		this.writingDate = writingDate;
		this.whatCompanyWrote = whatCompanyWrote;
		this.reciever = reciever;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getNumber() {
		return number;
	}
	
	public Date getWritingDate() {
		return writingDate;
	}
	
	public String getWhatCompanyWrote() {
		return whatCompanyWrote;
	}
	
	public String getReciever() {
		return reciever;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public void setWritingDate(Date writingDate) {
		this.writingDate = writingDate;
	}
	
	public void setWhatCompanyWrote(String whatCompanyWrote) {
		this.whatCompanyWrote = whatCompanyWrote;
	}
	
	public void setReciever(String reciever) {
		this.reciever = reciever;
	}
	
}
