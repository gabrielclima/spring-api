package br.com.restapp.data.dao.impl;

import org.springframework.stereotype.Component;

import br.com.restapp.data.dao.UserDao;
import br.com.restapp.data.model.User;

@Component("userDao")
public class UserDaoJpaImpl extends GenericDaoJpaImpl<User, Long> implements UserDao {

	
	
}
