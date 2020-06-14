package com.pds.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pds.entity.Quote;
import com.pds.service.QuoteService;

@RestController
@RequestMapping("quote")
public class QuoteController {

	@Autowired 
	private QuoteService quoteService;	

	@PostMapping
	public ResponseEntity<Quote> save(@Valid @RequestBody Quote quote) {
		List<String> divisas = Arrays.asList("USD", "EUR", "GBP");
		
		if (!divisas.stream().filter(divisa -> divisa.equals(quote.getSymbol())).findAny().isPresent()) {
			return new ResponseEntity<Quote>(HttpStatus.BAD_REQUEST);
		} else if (this.quoteService.findBySymbol(quote) == null) {
			return new ResponseEntity<Quote>(this.quoteService.save(quote), HttpStatus.OK);
		} else {
			return new ResponseEntity<Quote>(HttpStatus.BAD_REQUEST);
		}
	}
}
