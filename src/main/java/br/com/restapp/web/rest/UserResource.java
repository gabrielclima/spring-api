package br.com.restapp.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {
	
	@RequestMapping("/")
	public String index(){
		return "Olá, seja bem-vindo!";
	}
	
	@RequestMapping("/user")
	private String usuario(){	
		return "Teste";
	}
	
	

}
