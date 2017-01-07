package br.com.restapp.data.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public User findUserByEmail(String email) {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();

		sql.append("SELECT u FROM User u ");
		sql.append("where u.email = :email ");

		params.put("email", email);

		List<User> users = findByQueryAndNamedParams(sql.toString(), params);
		if(users.isEmpty()){
			return null;
		} else {
			return users.get(0);
		}
	}

}
