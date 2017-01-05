package br.com.restapp.web.rest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.restapp.data.model.User;
import br.com.restapp.service.UserService;

@RestController
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String index(){
		return "Olá, seja bem-vindo!";
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public @ResponseBody User user() {	
		User user = new User();
		user.setName("Gabriel");
		user.setCreated(new Date());
		user.setEmail("teste@teste.com");
		user.setPassword("tetqweqweq");
		user.setLastLogin(new Date());
		
		userService.createUser(user);
		
		return user;
	}
	
	

}
