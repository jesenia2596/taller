package com.pds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pds.entity.Currency;
import com.pds.entity.Quote;
import com.pds.repository.QuoteRepository;

@Service
public class QuoteService {

	@Autowired
	private QuoteRepository quoteRepository;
	
	@Autowired
	private CurrencyService currencyService;

	public Quote save(Quote quote) {
		Currency currency = currencyService.findById( quote.getCurrency().getId() );
		currency.setRank(Long.sum(currency.getRank(), Long.valueOf(1L)));
		
		quote.setCurrency(currency);
		return this.quoteRepository.save(quote);
	}

	public Quote findBySymbol(Quote quote) {
		return this.quoteRepository.findBySymbolAndCurrencyId(quote.getSymbol(), quote.getCurrency().getId());
	}

}
