package br.com.restapp.web.rest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.restapp.data.dao.UserDao;
import br.com.restapp.data.model.User;
import br.com.restapp.service.UserService;
import br.com.restapp.web.erros.ErrorResponse;

@RestController
public class UserResource {

	@Autowired
	private UserService userService;

	@Autowired
	private UserDao userDao;
	
	
	/**
     * POST  /register : registra o usuário.
     *
     * @param User o usuário a ser registrado
     * @return ResponseEntity com status 201 (Created) se o usuário foi criado, 409 (Conflito) se o e-mail já foi utilizado
     */
	@RequestMapping(value = "/register", 
			method = RequestMethod.POST, 
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<?> registerUser(@Valid @RequestBody User inputUser, HttpServletRequest request) {

		User user = userDao.findUserByEmail(inputUser.getEmail());

		if (user != null) {
			return new ResponseEntity<>(new ErrorResponse("Este e-mail já está em uso."), HttpStatus.CONFLICT);
		} else {
			User createdUser = userService.createUser(inputUser);
			return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
		}
		
	}


	/**
     * POST  /login : autentica o usuário e cria um token de sessão.
     *
     * @param User o usuário a ser logado
     * @return ResponseEntity com status 200 (Ok) se o usuário foi autenticado com sucesso, 400 (Bad Request) se os dados não correspondem
     */
	@RequestMapping(value = "/login", 
			method = RequestMethod.POST, 
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<?> login(@RequestBody User inputUser, HttpServletRequest request) {

		User user = userDao.findUserByEmail(inputUser.getEmail());

		if(user != null){
			if(userService.authenticate(user)){
				return new ResponseEntity<>(user, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new ErrorResponse("Usuário e/ou senha inválidos."), HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Usuário e/ou senha inválidos.", HttpStatus.BAD_REQUEST);
		}

	}

	/**
     * POST  /profile : retorna o perfil do usuário.
     *
     * @param User o usuário desejado
     * @param Authorization token de autorização, gerado no endpoint /login
     * @return ResponseEntity com dados e http status code 200 (Ok) caso encontrado, 404 (Not found) caso não encontrado, 
     * 401 (Unauthorized) caso o token esteja inválido
     */
	@RequestMapping(value = "/profile", 
			method = RequestMethod.POST, 
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<?> getProfile(
			@RequestBody User inputUser,
			@RequestHeader(value = "Authorization") String token, 
			HttpServletRequest request) {

		User user = userDao.findUserByEmail(inputUser.getEmail());

		if(user != null){
			if(userService.validate(user, token)){
				return new ResponseEntity<>(user, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new ErrorResponse("Não autorizado"), HttpStatus.UNAUTHORIZED);
			}
		} else {
			return new ResponseEntity<>(new ErrorResponse("Perfi não encontrado."), HttpStatus.NOT_FOUND);
		}
		
	}
	 
}
