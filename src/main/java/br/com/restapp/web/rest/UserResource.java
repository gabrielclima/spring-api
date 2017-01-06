package br.com.restapp.web.rest;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

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

import br.com.restapp.data.model.User;
import br.com.restapp.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

@RestController
public class UserResource {
	
	@Autowired
	private UserService userService;
	
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

		Key key = MacProvider.generateKey();
		
		String token = Jwts.builder()
				.setSubject("Joe")
				.signWith(SignatureAlgorithm.HS512, key)
				.setExpiration(new Date(System.currentTimeMillis() + 30*60*1000))
				.compact();
		
		user.setToken(token);
//		String token = JWT.create()
//				.withIssuer("auth0")
//				.sign(Algorithm.RSA512(key));
				
		user.setCreated(LocalDateTime.now());
		userService.createUser(user);
		
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	

}
