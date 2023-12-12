package cat.institutmarianao.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.ApplicationScope;

import cat.institutmarianao.domain.User;
import cat.institutmarianao.repository.UserRepository;

@Repository
@ApplicationScope
public class InMemoryUserRepositoryImpl implements UserRepository {

	private static final List<User> users = new ArrayList<>();

	public InMemoryUserRepositoryImpl() {
		users.add(createUser("admin", "admin", "Admin", null, "ADMIN"));
		users.add(createUser("lisa", "lisa", "Lisa", "Brennan-Jobs", "USER"));
		users.add(createUser("paul", "paul", "Paul", "Allen", "USER"));
	}

	@Override
	public User get(String username) {
		return users.stream().filter(user -> Objects.equals(user.getUsername(), username)).findAny().orElse(null);
	}

	private User createUser(String username, String password, String firstName, String lastName, String role) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setRole(role);
		return user;
	}
}
