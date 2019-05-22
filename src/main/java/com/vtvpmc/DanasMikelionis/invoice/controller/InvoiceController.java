package com.vtvpmc.DanasMikelionis.invoice.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtvpmc.DanasMikelionis.invoice.model.Invoice;
import com.vtvpmc.DanasMikelionis.invoice.model.Item;
import com.vtvpmc.DanasMikelionis.invoice.model.createCommands.CreateInvoiceCommand;
import com.vtvpmc.DanasMikelionis.invoice.model.createCommands.CreateItemCommand;
import com.vtvpmc.DanasMikelionis.invoice.service.InvoiceService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class InvoiceController {
	@Autowired
	private InvoiceService service;
	
	@GetMapping("/invoices")
	public Collection<Invoice> getInvoices() {
		return this.service.getInvoices();
	}
	
	@GetMapping("/invoices/{invoiceId}")
	public Invoice getInvoice(@PathVariable @Valid Long invoiceId) {
		return this.service.getInvoice(invoiceId);
	}
	
	@GetMapping("/invoices/{invoiceId}/items")
	public Collection<Item> getItemsFromInvoice(@PathVariable final Long invoiceId) {
		return this.service.getItems(invoiceId);
	}
	
	@GetMapping("/admin/checkIfAdmin")
	public String getMessageIfAdmin() {
		return "Only admins can view this message.";
	}
	
	@GetMapping("/admin/checkIfAdmin/{number}")
	public String getMessageIfAdmin(@PathVariable Long number) {
		return "Only admins can view this message. The number you entered: " + number;
	}
	
	@PostMapping(path = "/admin/invoices")
	public Invoice addInvoice(@RequestBody @Valid CreateInvoiceCommand createInvoiceCommand) {
		return this.service.addInvoice(createInvoiceCommand);
	}
	
	@PostMapping("/admin/invoices/{invoiceId}/items")
	public Item addItem(@PathVariable @Valid final Long invoiceId,
			@RequestBody @Valid CreateItemCommand createItemCommand) {
		return this.service.addItem(invoiceId, createItemCommand);
	}
	
	@PutMapping("/admin/invoices/{invoiceId}")
	public Invoice modifyInvoice(@PathVariable final Long invoiceId,
			@RequestBody CreateInvoiceCommand createInvoiceCommand) {
		return this.service.modifyInvoice(invoiceId, createInvoiceCommand);
	}
	
	@PutMapping("/admin/items/{itemId}")
	public Item modifyItem(@PathVariable final Long itemId,
			@RequestBody @Valid CreateItemCommand createItemCommand) {
		return this.service.modifyItem(itemId, createItemCommand);
	}
	
	@DeleteMapping("/admin/invoices/{invoiceId}")
	public String deleteInvoice(@PathVariable final Long invoiceId) {
		return this.service.deleteInvoice(invoiceId);
	}
	
	@DeleteMapping("/admin/items/{itemId}")
	public String deleteItem(@PathVariable final long itemId) {
		return this.service.deleteItem(itemId);
	}
}
