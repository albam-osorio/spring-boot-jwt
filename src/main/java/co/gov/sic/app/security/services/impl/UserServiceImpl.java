package co.gov.sic.app.security.services.impl;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import co.gov.sic.app.security.entities.User;
import co.gov.sic.app.security.models.UserModel;
import co.gov.sic.app.security.repositories.UserRepository;
import co.gov.sic.app.security.services.UserService;
import co.gov.sic.config.security.jwt.Constants;
import lombok.val;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username).get();
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = !user.isLocked();
		
		// @formatter:off
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), 
				user.getPassword(), 
				enabled,
				accountNonExpired, 
				credentialsNonExpired, 
				accountNonLocked, 
				getAuthority(user));
		// @formatter:on
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		// authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return authorities;
	}

	public List<UserModel> findAll() {
		List<User> list = new ArrayList<>();
		userRepository.findAll().iterator().forEachRemaining(list::add);
		return list.stream().map(entity -> asModel(entity)).collect(toList());
	}

	@Override
	public UserModel findById(Long id) {
		return asModel(userRepository.findById(id).get());
	}

	@Override
	public UserModel findOne(String username) {
		return asModel(userRepository.findByUsername(username).get());
	}

	@Override
	public UserModel save(UserModel user) {
		User entity;

		val optional = userRepository.findByUsername(user.getUsername());
		if (optional.isPresent()) {
			entity = optional.get();
		} else {
			entity = new User();
			entity.setUsername(user.getUsername());
		}

		entity.setPassword(bcryptEncoder.encode(user.getPassword()));
		entity.setAttempts(0);
		entity.setLocked(false);
		return asModel(userRepository.save(entity));
	}

	@Override
	public void authenticationFailed(String username) {
		val optional = userRepository.findByUsername(username);
		if (optional.isPresent()) {
			val entity = optional.get();
			entity.setAttempts(entity.getAttempts() + 1);
			if (entity.getAttempts() > Constants.MAX_ATTEMPS) {
				entity.setLocked(true);
			}
			userRepository.save(entity);
		}
	}

	@Override
	public void delete(long id) {
		userRepository.deleteById(id);
	}

	private UserModel asModel(final User entity) {
		return new UserModel(entity.getUsername(), "", entity.getAttempts(), entity.isLocked());
	}
}
