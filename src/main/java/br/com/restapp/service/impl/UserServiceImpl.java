package br.com.restapp.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.restapp.data.dao.UserDao;
import br.com.restapp.data.model.User;
import br.com.restapp.jwt.JWTUtils;
import br.com.restapp.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	private JWTUtils jwtUtils;
	
	@Override
	public User createUser(User user) {
		
		user.setCreated(new Date(System.currentTimeMillis()));
		user.setLastLogin(new Date());
		user.setToken(jwtUtils.createToken(user.getEmail()));
		
		return userDao.save(user);		
	}

	@Override
	public boolean validate(User user, String token) {
		if(user.getToken().equals(token)){
			JWTUtils.validateToken(token);
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean authenticate(User user){
		if(!user.getPassword().equals(user.getPassword())){
			return false;
		} else {
			user.setToken(jwtUtils.createToken(user.getEmail()));
			userDao.merge(user);
			return true;
		}

	}
	

}
