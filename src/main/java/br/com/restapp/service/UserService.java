package br.com.restapp.service;

import org.springframework.transaction.annotation.Transactional;

import br.com.restapp.data.model.User;

public interface UserService {

	@Transactional
	void createUser(User user);
	
}
