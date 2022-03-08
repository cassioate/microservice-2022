package br.com.tessaro.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.tessaro.config.CurrencyExchangeProxy;
import br.com.tessaro.model.CurrencyConversion;

@Service
public class CurrencyConversionService {
	
	@Autowired
	private CurrencyExchangeProxy proxy;
	
	// Utilizando Feign
	public CurrencyConversion conversion(String from, String to, BigDecimal quantity) {
		ResponseEntity<CurrencyConversion> currencyConversion = proxy.retrieveExcahngeValue(from, to);
		CurrencyConversion resposneEntity = currencyConversion.getBody();
		
		return new CurrencyConversion(resposneEntity.getId(), resposneEntity.getFrom(), resposneEntity.getTo(), 
				resposneEntity.getQuantity(), resposneEntity.getConversionMultiple(), 
				quantity.multiply(resposneEntity.getConversionMultiple()) ,resposneEntity.getEnvironment());
	}
	
	// Utilizando RestTemplate
//	public CurrencyConversion conversion(String from, String to, BigDecimal quantity) {
//		CurrencyConversion resposneEntity = responseGet(from, to).getBody();
//		return new CurrencyConversion(resposneEntity.getId(), resposneEntity.getFrom(), resposneEntity.getTo(), resposneEntity.getQuantity(), resposneEntity.getConversionMultiple(), quantity.multiply(resposneEntity.getConversionMultiple()) ,resposneEntity.getEnvironment());
//	}
//	
//	public ResponseEntity<CurrencyConversion> responseGet(String from, String to) {
//		HashMap<String, String> uriVariables = new HashMap<>();
//		uriVariables.put("from", from);
//		uriVariables.put("to", to);
//		ResponseEntity<CurrencyConversion> resposneEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);
//		return resposneEntity;
//	}	

}
