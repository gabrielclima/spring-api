package br.com.restapp.data.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.restapp.data.dao.UserDao;
import br.com.restapp.data.model.User;

@Component("userDao")
public class UserDaoJpaImpl extends GenericDaoJpaImpl<User, Long> implements UserDao {

	@Override
	public List<User> getUsers() {
		List<User> users = (List<User>) em.createQuery("SELECT c FROM User c").getResultList();
		return users;
	}
	
	@Override
	public User findUserByEmail(String email){
		return null;
	}

}
