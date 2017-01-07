package br.com.restapp.data.dao;

import java.util.List;

import br.com.restapp.data.model.User;

public interface UserDao extends GenericDao<User, Long>{
	
	List<User> getUsers();

	User findUserByEmail(String email);
		
	
}
