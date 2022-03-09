package br.com.tessaro.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import br.com.tessaro.model.CurrencyExchange;
import br.com.tessaro.repository.CurrencyExchangeRepository;

@Service
public class CurrencyExchangeService {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeRepository currencyRepository;
	
	public CurrencyExchange retrieveExchangeValue(String from, String to) {
	
		CurrencyExchange currencyExchange = currencyRepository.findByFromAndTo(from, to);
		
		if (currencyExchange == null) {
			throw new RuntimeException("Unable to find data");
		} else {
			currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
		}
	
		return currencyRepository.save(currencyExchange);
	}

	
//	Futuro post
//	public CurrencyExchange retrieveExchangeValue(String from, String to) {
//		CurrencyExchange currencyExchange = new CurrencyExchange(100L, from, to, BigDecimal.valueOf(50));
//		currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
//		return currencyRepository.save(currencyExchange);
//	}
	
}
