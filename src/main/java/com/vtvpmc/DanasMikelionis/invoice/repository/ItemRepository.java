package com.vtvpmc.DanasMikelionis.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vtvpmc.DanasMikelionis.invoice.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>  {

}
