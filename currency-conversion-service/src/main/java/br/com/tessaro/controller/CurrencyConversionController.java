package br.com.tessaro.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tessaro.model.CurrencyConversion;
import br.com.tessaro.service.CurrencyConversionService;

@RestController
@RequestMapping("currency-conversion")
public class CurrencyConversionController {

	@Autowired
	CurrencyConversionService currencyService;
	
	@GetMapping("from/{from}/to/{to}/quantity/{quantity}")
	public ResponseEntity<CurrencyConversion> caculateCurrencyConversion (@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		CurrencyConversion currencyCovnersion= currencyService.conversion(from, to, quantity);
		return new ResponseEntity<>(currencyCovnersion, HttpStatus.OK);
	}
	
}
