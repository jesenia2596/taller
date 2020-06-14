package com.pds.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pds.entity.Currency;
import com.pds.service.CurrencyService;

@RestController
@RequestMapping("currency")
public class CurrencyController {

	@Autowired
	private CurrencyService currencyService;

	@PostMapping
	public ResponseEntity<Currency> save(@Valid @RequestBody Currency currency) {
		if (this.currencyService.findByNameOrSymbol(currency) == null) {
			return new ResponseEntity<Currency>(this.currencyService.save(currency), HttpStatus.CREATED);
		}
		return new ResponseEntity<Currency>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping
	public ResponseEntity<Map<String, List<Currency>>> findAll() {
		Map<String, List<Currency>> response = new HashMap<>();
		response.put("data", this.currencyService.findAll());
		return new ResponseEntity<Map<String, List<Currency>>>(response, HttpStatus.CREATED);
	}

	@GetMapping("{name}")
	public ResponseEntity<Currency> findByName(@PathVariable String name) {
		Currency currency = this.currencyService.findByName(name);
		if (currency != null) {
			return new ResponseEntity<Currency>(currency, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Currency>(HttpStatus.NOT_FOUND);
		}
	}
} 
