package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.restapp.data.dao.UserDao;
import br.com.restapp.data.model.User;
import br.com.restapp.service.UserService;
import br.com.restapp.service.impl.UserServiceImpl;

public class UserTest {

	@InjectMocks
	private UserService userService = new UserServiceImpl();

	@Mock
	private UserDao userDaoMock;

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void TestRegisterUser() {
		Mockito.when(userDaoMock.save(Mockito.any(User.class))).thenReturn(mockUser());

		User createdUser = userService.createUser(mockUser());
		assertNotNull(createdUser);
		assertEquals(createdUser.getName(), mockUser().getName());
		assertEquals(createdUser.getEmail(), mockUser().getEmail());
		assertEquals(createdUser.getPassword(), mockUser().getPassword());
	}

	private User mockUser() {
		User user = new User();
		user.setName("Fake User");
		user.setEmail("fake@mail.com");
		user.setPassword("pass");
		return user;
	}

}
