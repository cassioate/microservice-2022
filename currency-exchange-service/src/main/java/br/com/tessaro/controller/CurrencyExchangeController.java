package br.com.tessaro.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tessaro.model.CurrencyExchange;
import br.com.tessaro.service.CurrencyExchangeService;

@RestController
@RequestMapping("currency-exchange/")
public class CurrencyExchangeController {
	
	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@Autowired
	private CurrencyExchangeService currencyService;
	
	@GetMapping("from/{from}/to/{to}")
	@Cacheable(value="retrieveExcahngeValueCache") // Para limpar o cache ao realizar um cadastro, deve-se utilizar a anotação @CacheEvict(value = "retrieveExcahngeValueCache", 
	// allEntries = true) em cima do end point que precisa realziar essa atualização nos caches
	// fazendo assim com que o cache seja limpado em caso de modificação nesse "produto" que está em cache via outro end point.
	public ResponseEntity<CurrencyExchange> retrieveExcahngeValue(@PathVariable String from, @PathVariable String to) {
//		logger.info("adress: "+ "from/"+from+"/to/"+to+"RetrieveExchangevalue call received");
		logger.info("adress: from/{}/to/{}. - RetrieveExchangevalue call received", from, to);
		CurrencyExchange currencyExchange = currencyService.retrieveExchangeValue(from, to);
		return new ResponseEntity<>(currencyExchange, HttpStatus.OK);
	}
	
}
