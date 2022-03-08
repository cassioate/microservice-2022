package br.com.tessaro.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
// Configuração abaixo é refernte ao limits-service.minimum=55 de dentro do limits-service.properties
// Após ter escolhido o arquvio que será lido através do "spring.application.name" dentro do .properties, aqui iremos escolher qual a "variavel" que será lida, no caso será toda aqulea com o inicio limits-service
@ConfigurationProperties("limits-service")
public class Configuration {
	private int minimum;
	private int maximum;
}
