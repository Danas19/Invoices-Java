package com.vtvpmc.DanasMikelionis.invoice.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vtvpmc.DanasMikelionis.invoice.CreateInvoiceCommand;
import com.vtvpmc.DanasMikelionis.invoice.CreateItemCommand;
import com.vtvpmc.DanasMikelionis.invoice.model.Invoice;
import com.vtvpmc.DanasMikelionis.invoice.model.Item;
import com.vtvpmc.DanasMikelionis.invoice.service.InvoiceService;

@RestController
@RequestMapping(value="/api/invoices")
@CrossOrigin
public class InvoiceController {
	@Autowired
	private InvoiceService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public Collection<Invoice> getInvoices() {
		return this.service.getInvoices();
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Invoice getInvoice(@PathVariable @Valid Long id) {
		return this.service.getInvoice(id);
	}
	
	@RequestMapping(path = "/{id}/items", method = RequestMethod.GET)
	public Collection<Item> getItems(@PathVariable final Long id) {
		return this.service.getItems(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Invoice addInvoice(@RequestBody @Valid CreateInvoiceCommand createInvoiceCommand) {
		return this.service.addInvoice(createInvoiceCommand);
	}
	
	@RequestMapping(value = "{id}/item", method = RequestMethod.POST)
	public Item addItem(@PathVariable @Valid final Long id,
			@RequestBody @Valid CreateItemCommand createItemCommand) {
		return this.service.addItem(id, createItemCommand);
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public Invoice modifyInvoice(@PathVariable final Long id,
			@RequestBody CreateInvoiceCommand createInvoiceCommand) {
		return this.service.modifyInvoice(id, createInvoiceCommand);
	}
	
	@RequestMapping(path = "{id}/item", method = RequestMethod.PUT)
	public Item modifyItem(@PathVariable final Long id,
			@RequestBody @Valid CreateItemCommand createItemCommand) {
		return this.service.modifyItem(id, createItemCommand);
	}
	
	@RequestMapping(path = "{id}", method = RequestMethod.DELETE)
	public String deleteInvoice(@PathVariable final Long id) {
		return this.service.deleteInvoice(id);
	}
	
	@RequestMapping(path = "/delete/item/{itemId}", method = RequestMethod.DELETE)
	public String deleteItem(@PathVariable final long itemId) {
		return this.service.deleteItem(itemId);
	}
}
