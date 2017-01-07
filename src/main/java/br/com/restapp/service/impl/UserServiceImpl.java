package br.com.restapp.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.restapp.data.dao.UserDao;
import br.com.restapp.data.model.User;
import br.com.restapp.service.UserService;
import jwt.JWTUtils;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	private JWTUtils jwtUtils;
	
	@Override
	public void createUser(User user) {
		
		user.setCreated(new Date(System.currentTimeMillis()));
		user.setLastLogin(new Date());
		user.setToken(jwtUtils.createToken(user.getEmail()));
		
		userDao.save(user);		
	}

}
