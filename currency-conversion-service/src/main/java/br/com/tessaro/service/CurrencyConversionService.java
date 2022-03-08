package br.com.tessaro.service;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.tessaro.model.CurrencyConversion;

@Service
public class CurrencyConversionService {
	
	public CurrencyConversion conversion(String from, String to, BigDecimal quantity) {
		CurrencyConversion resposneEntity = responseGet(from, to).getBody();
		return new CurrencyConversion(resposneEntity.getId(), resposneEntity.getFrom(), resposneEntity.getTo(), resposneEntity.getQuantity(), resposneEntity.getConversionMultiple(), quantity.multiply(resposneEntity.getConversionMultiple()) ,resposneEntity.getEnvironment());
	}
	
	public ResponseEntity<CurrencyConversion> responseGet(String from, String to) {
		HashMap<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConversion> resposneEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);
		return resposneEntity;
	}	

}
