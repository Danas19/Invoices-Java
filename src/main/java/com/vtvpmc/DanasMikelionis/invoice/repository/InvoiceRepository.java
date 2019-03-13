package com.vtvpmc.DanasMikelionis.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vtvpmc.DanasMikelionis.invoice.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
	
}
