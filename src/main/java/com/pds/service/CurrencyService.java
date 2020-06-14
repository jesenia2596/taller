package com.pds.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds.entity.Currency;
import com.pds.repository.CurrencyRepository;

@Service
public class CurrencyService {

	@Autowired
	private CurrencyRepository currencyRepository;

	public Currency save(Currency currency) {
		return this.currencyRepository.save(currency);
	}

	public List<Currency> findAll() {
		List<Currency> currencies = (List<Currency>) this.currencyRepository.findByOrderByQuotePriceDesc();
		List<Currency> response = new ArrayList<Currency>();
		currencies.forEach(item -> {
			item.getQuote().forEach( quoteItem -> quoteItem.setCurrency(null));

			Currency ci = new Currency();
			ci.setId(item.getId());
			ci.setName(item.getName());			
			ci.setRank(item.getRank());
			ci.setSymbol( item.getSymbol());
			ci.setQuote( item.getQuote() );
			response.add( ci );
		});
		return response;
	}

	public Currency findByNameOrSymbol(Currency currency) {
		return this.currencyRepository.findByNameOrSymbol(currency.getName(), currency.getSymbol());
	}

	public Currency findByName(String name) {
		return this.currencyRepository.findByName(name);
	}

	public Currency findById(Long id) {
		return this.currencyRepository.findById(id).get();
	}
}
