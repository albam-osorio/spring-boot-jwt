package co.gov.sic.app.security.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.gov.sic.app.security.models.LoginModel;
import co.gov.sic.app.security.models.UserModel;
import co.gov.sic.app.security.services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<UserModel> listUser() {
		return userService.findAll();
	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public UserModel getOne(@PathVariable(value = "id") Long id) {
		return userService.findById(id);
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public UserModel saveUser(@RequestBody LoginModel login) {

		return userService.save(new UserModel(login.getUsername(), login.getPassword(), 0, false));
	}
}
