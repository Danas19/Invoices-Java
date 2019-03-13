package com.vtvpmc.DanasMikelionis.invoice.service;

import java.util.Collection;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vtvpmc.DanasMikelionis.invoice.CreateInvoiceCommand;
import com.vtvpmc.DanasMikelionis.invoice.CreateItemCommand;
import com.vtvpmc.DanasMikelionis.invoice.model.Invoice;
import com.vtvpmc.DanasMikelionis.invoice.model.Item;
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
	
	public Invoice getInvoice(Long id) {
		return this.invoiceRepository.findById(id).orElse(null);
	}
	
	public Invoice addInvoice(CreateInvoiceCommand createInvoiceCommand) {
		Invoice invoice = new Invoice(createInvoiceCommand.getNumber(),
				createInvoiceCommand.getWritingDate(),
					createInvoiceCommand.getWhatCompanyWrote(),
						createInvoiceCommand.getReciever());
		
		this.invoiceRepository.save(invoice);
		return invoice;
	}
	
	public Item addItem(Long id, CreateItemCommand createItemCommand) {
		Item item = new Item(createItemCommand.getName(), createItemCommand.getQuantity(),
				createItemCommand.getHeightCm(), createItemCommand.getWidthCm(),
					createItemCommand.getPriceEuroCents());
		item.setInvoice(invoiceRepository.getOne(id));
		this.itemRepository.save(item);
		//this.invoiceRepository.getOne(createItemCommand.getInvoiceId()).addItem(item);
		
		return item;
	}
	
	public Invoice deleteInvoice(Long id) {
		Invoice invoice = this.invoiceRepository.getOne(id);
		this.invoiceRepository.deleteById(id);
		return invoice;
	}
	
	public Item modifyItem(Long id, CreateItemCommand createItemCommand) {
		this.itemRepository.getOne(id).setHeightCm(createItemCommand.getHeightCm());
		this.itemRepository.getOne(id)
			.setInvoice(this.itemRepository.getOne(id).getInvoice());
		this.itemRepository.getOne(id).setName(createItemCommand.getName());
		this.itemRepository.getOne(id)
			.setPriceEuroCents(createItemCommand.getPriceEuroCents());
		this.itemRepository.getOne(id).setQuantity(createItemCommand.getQuantity());
		this.itemRepository.getOne(id).setWidthCm(createItemCommand.getWidthCm());
		
		return itemRepository.getOne(id);
	}
	
	public Invoice modifyInvoice(Long id, CreateInvoiceCommand createInvoiceCommand) {
		this.invoiceRepository.getOne(id).setNumber(createInvoiceCommand.getNumber());
		this.invoiceRepository.getOne(id).setReciever(createInvoiceCommand.getReciever());
		this.invoiceRepository.getOne(id)
			.setWhatCompanyWrote(createInvoiceCommand.getWhatCompanyWrote());
		this.invoiceRepository.getOne(id).setWritingDate(createInvoiceCommand.getWritingDate());
		
		return invoiceRepository.getOne(id);
	}

}
