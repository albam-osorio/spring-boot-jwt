package co.gov.sic.app.security.web.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.gov.sic.app.security.models.LoginModel;
import co.gov.sic.app.security.models.UserModel;
import co.gov.sic.app.security.services.UserService;
import co.gov.sic.config.security.jwt.JwtTokenUtil;
import lombok.var;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/principal")
	public Map<String, Object> user(Principal principal) {
		Map<String, Object> claims = new HashMap<>();
		if (principal != null && principal instanceof UsernamePasswordAuthenticationToken) {
			var p = ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
			if (p != null && p instanceof User) {
				var user = (User) p;
				var authorities = user.getAuthorities().stream().map(a -> a.getAuthority())
						.collect(Collectors.toList());

				claims.put("username", user.getUsername());
				claims.put("authorities", authorities);
				claims.put("accountNonExpired", user.isAccountNonExpired());
				claims.put("accountNonLocked", user.isAccountNonLocked());
				claims.put("credentialsNonExpired", user.isCredentialsNonExpired());
				claims.put("enabled", user.isEnabled());
			}
		}
		return claims;
	}

	@PostMapping(value = "/signup")
	public UserModel saveUser(@RequestBody LoginModel login) {

		return userService.save(new UserModel(login.getUsername(), login.getPassword(), 0, false));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public List<UserModel> listUser() {
		return userService.findAll();
	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping(value = "/{id}")
	public UserModel getOne(@PathVariable(value = "id") Long id) {
		return userService.findById(id);
	}
}
