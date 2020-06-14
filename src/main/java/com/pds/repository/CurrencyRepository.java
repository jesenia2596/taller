package com.pds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pds.entity.Currency;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {

	Currency findByNameOrSymbol(String name, String symbol);
	
	Currency findByName(String name);
	
	@Query( value = "select * from currency c join quote q on c.id = q.currency_id order by q.price desc", nativeQuery = true)
	List<Currency> findByOrderByQuotePriceDesc();
	
}
