package br.com.restapp.service;

import org.springframework.transaction.annotation.Transactional;

import br.com.restapp.data.model.User;

public interface UserService {

	@Transactional
	User createUser(User user);
	
	boolean validate(User user, String token);

	boolean authenticate(User user);
	
}
