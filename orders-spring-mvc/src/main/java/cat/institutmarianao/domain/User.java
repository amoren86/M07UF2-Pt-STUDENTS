package cat.institutmarianao.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final int MAX_USERNAME = 50;
	// public static final int MIN_PASSWORD = 8;
	public static final int MIN_PASSWORD = 4;
	public static final int MAX_PASSWORD = 50;

	@NotBlank
	@Size(max = MAX_USERNAME)
	private String username;

	@NotBlank
	@Size(min = MIN_PASSWORD, max = MAX_PASSWORD)
	private String password;

	@NotBlank
	private String role;

	@NotBlank
	private String firstName;

	private String lastName;

	private List<Order> orders;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Order> getOrders() {
		if (orders == null) {
			orders = new ArrayList<>();
		}
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = Objects.requireNonNullElse(orders, new ArrayList<>());
	}

	@Override
	public int hashCode() {
		return Objects.hash(username);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object instanceof User user) {
			return Objects.equals(username, user.username);
		}
		return false;
	}

}
