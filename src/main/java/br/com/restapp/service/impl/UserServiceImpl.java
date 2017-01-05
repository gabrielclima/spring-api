package br.com.restapp.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.restapp.data.dao.UserDao;
import br.com.restapp.data.model.User;
import br.com.restapp.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Inject
	private UserDao userDao;
	
	@Override
	public void createUser(User user) {
		userDao.save(user);		
	}

}
