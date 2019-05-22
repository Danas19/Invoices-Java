package com.vtvpmc.DanasMikelionis.invoice.service;

import java.util.Collection;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vtvpmc.DanasMikelionis.invoice.model.Invoice;
import com.vtvpmc.DanasMikelionis.invoice.model.Item;
import com.vtvpmc.DanasMikelionis.invoice.model.createCommands.CreateInvoiceCommand;
import com.vtvpmc.DanasMikelionis.invoice.model.createCommands.CreateItemCommand;
import com.vtvpmc.DanasMikelionis.invoice.repository.InvoiceRepository;
import com.vtvpmc.DanasMikelionis.invoice.repository.ItemRepository;

@Service
public class InvoiceService {
	
	private InvoiceRepository invoiceRepository;
	private ItemRepository itemRepository;
	
	@Autowired
	public InvoiceService(InvoiceRepository invoiceRepository,
			ItemRepository itemRepository) {
		super();
		this.invoiceRepository = invoiceRepository;
		this.itemRepository = itemRepository;
	}

	private Logger log = Logger.getLogger(InvoiceService.class.getName());
	
	public Collection<Invoice> getInvoices() {
		return this.invoiceRepository.findAll();
	}
	
	public Invoice getInvoice(Long invoiceId) {
		return this.invoiceRepository.findById(invoiceId).orElse(null);
	}
	
	public Invoice addInvoice(CreateInvoiceCommand createInvoiceCommand) {
		Invoice invoice = new Invoice(createInvoiceCommand.getNumber(),
				createInvoiceCommand.getWritingDate(),
					createInvoiceCommand.getWhatCompanyWrote(),
						createInvoiceCommand.getReciever());
		
		this.invoiceRepository.save(invoice);
		return invoice;
	}
	
	public Item addItem(Long invoiceId, CreateItemCommand createItemCommand) {
		Item item = new Item(createItemCommand.getName(), createItemCommand.getQuantity(),
				createItemCommand.getHeightCm(), createItemCommand.getWidthCm(),
					createItemCommand.getPriceEuroCents());
		item.setInvoice(invoiceRepository.getOne(invoiceId));
		this.itemRepository.save(item);
		
		return item;
	}
	
	public String deleteInvoice(Long invoiceId) {
		if (invoiceRepository.findById(invoiceId).orElse(null) != null) {
			this.invoiceRepository.deleteById(invoiceId);
			return "Invoice with Id " + invoiceId + " was removed.";
		}
		return "! There is no invoice with id: '" + invoiceId + "'. No invoice was deleted.";
	}
	
	public String deleteItem(Long id) {
		if (itemRepository.findById(id).orElse(null) != null) {
			itemRepository.deleteById(id);
			return "Item with Id " + id + " was removed.";
		}
		return "! There is no item with id: '" + id + "'. No item was deleted.";
	}
	
	public Item modifyItem(Long itemId, CreateItemCommand createItemCommand) {
		if (itemId == null || itemRepository.getOne(itemId) == null) {
			return null;
		}
		Item item = this.itemRepository.getOne(itemId);
		item.setHeightCm(createItemCommand.getHeightCm());
		item.setInvoice(this.itemRepository.getOne(itemId).getInvoice());
		item.setName(createItemCommand.getName());
		item.setPriceEuroCents(createItemCommand.getPriceEuroCents());
		item.setQuantity(createItemCommand.getQuantity());
		item.setWidthCm(createItemCommand.getWidthCm());
		itemRepository.save(item);
		
		return item;
	}
	
	public Invoice modifyInvoice(Long invoiceId, CreateInvoiceCommand createInvoiceCommand) {
		if (invoiceId == null || invoiceRepository.findById(invoiceId) == null) {
			return null;
		}
		Invoice invoice = this.invoiceRepository.getOne(invoiceId);
		invoice.setNumber(createInvoiceCommand.getNumber());
		invoice.setReciever(createInvoiceCommand.getReciever());
		invoice.setWhatCompanyWrote(createInvoiceCommand.getWhatCompanyWrote());
		invoice.setWritingDate(createInvoiceCommand.getWritingDate());
		invoiceRepository.save(invoice);
		
		return invoice;
	}
	
	public Collection<Item> getItems(Long invoiceId) {
		return this.invoiceRepository.getOne(invoiceId).getItems();
	}
	
}
