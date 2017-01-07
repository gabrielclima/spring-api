package br.com.restapp.web.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiResource {

	@RequestMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
	public String index() {
		return "Olá, seja bem-vindo!<br> " + "Visite <a href=\"https://github.com/gabrielclima/java-spring-rest\"> "
				+ "github.com/gabrielclima/java-spring-rest</a> "
				+ "para visualizar a documentação";
	}
	
}
