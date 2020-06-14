package com.pds.repository;

import org.springframework.data.repository.CrudRepository;

import com.pds.entity.Quote;

public interface QuoteRepository extends CrudRepository<Quote, Long> {

	public Quote findBySymbolAndCurrencyId(String symbol, Long id);
	
}
