package br.com.restapp.web.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.restapp.data.dao.UserDao;
import br.com.restapp.data.model.User;
import br.com.restapp.service.UserService;

@RestController
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping("/")
	public String index(){
		return "Olá, seja bem-vindo!";
	}
	
	@RequestMapping(
			value = "/register", 
			method = RequestMethod.POST, 
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user, HttpServletRequest request) {	

		userService.createUser(user);
		
		List<User> users = userDao.getUsers();
		
		return new ResponseEntity<>(users, HttpStatus.CREATED);
	}
	
	

}
