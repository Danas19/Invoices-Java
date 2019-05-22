package com.vtvpmc.DanasMikelionis.invoice.model.createCommands;

import java.sql.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class CreateInvoiceCommand {
	@NotNull
	@Length(min = 1, max = 20)
	private String number;
	
	@NotNull
	private Date writingDate;
	
	@NotNull
	@Length(min = 1, max = 35)
	private String whatCompanyWrote;
	
	@NotNull
	@Length(min = 1, max = 35)
	private String reciever;
	
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
	
	
}