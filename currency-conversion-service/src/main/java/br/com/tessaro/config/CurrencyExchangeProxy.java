package br.com.tessaro.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.tessaro.model.CurrencyConversion;

//@FeignClient(name="currency-exchange-service", url="localhost:8000/currency-exchange/")
//Abaixo teremos o nome que estara registrado no eureka 'currency-exchange-service' 
//somado ao path:'/currency-exchange/', do controller no outro microservice que estou tentado acessar
@FeignClient(name="currency-exchange-service/currency-exchange/")
public interface CurrencyExchangeProxy {
	
// Deve criar uma interface para o metodo get que estara presente no controller do microservice que estou fazendo a requisicao, nesse caso utilizei o CurrencyConversion, 
//	pois ele tem os mesmos atributos que o CurrencyExchange, dai o feing faz o mapeamento automatico.
	@GetMapping("from/{from}/to/{to}")
	public ResponseEntity<CurrencyConversion> retrieveExcahngeValue(@PathVariable String from, @PathVariable String to);
}
