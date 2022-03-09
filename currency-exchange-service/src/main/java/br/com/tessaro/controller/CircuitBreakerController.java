package br.com.tessaro.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("sample-api")
public class CircuitBreakerController {
	
	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@GetMapping("")
	@Retry(name = "sample-api", fallbackMethod = "hardcodedMethod")
//	@CircuitBreaker (name = "default", fallbackMethod = "hardcodedMethod")
//	@RateLimiter(name="default")
//	@Bulkhead(name="sample-api")
	public ResponseEntity<String> sampleApi() {
		logger.info("Sample Api call received");
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
		return forEntity;
	}

	// O tipo do retorno deve ser igual ao do method que utilizará esse fallback
	public ResponseEntity<String> hardcodedMethod(Exception ex) {
		return new ResponseEntity<>("fallback-Response in the controller circuitBreakerController", HttpStatus.OK);
	}
	
}
