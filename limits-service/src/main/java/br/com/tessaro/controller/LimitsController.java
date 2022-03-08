package br.com.tessaro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tessaro.bean.Limits;
import br.com.tessaro.config.Configuration;

@RestController
@RequestMapping("/limits")
public class LimitsController {
	
	@Autowired
	private Configuration config;
	
	@GetMapping("")
	public Limits retrieveLimits() {
		return new Limits(config.getMinimum(), config.getMaximum());
	}

}
