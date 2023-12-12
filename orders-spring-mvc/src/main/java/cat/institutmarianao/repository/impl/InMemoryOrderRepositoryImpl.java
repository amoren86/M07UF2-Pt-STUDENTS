package cat.institutmarianao.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.ApplicationScope;

import cat.institutmarianao.domain.Order;
import cat.institutmarianao.domain.User;
import cat.institutmarianao.repository.OrderRepository;

@Repository
@ApplicationScope
public class InMemoryOrderRepositoryImpl implements OrderRepository {
	private static final Map<Long, Order> referencesOrders = new HashMap<>();
	private static final Map<User, Set<Order>> clientsOrders = new HashMap<>();

	private static long reference = 1l;

	@Override
	public Order get(Long reference) {
		return referencesOrders.get(reference);
	}

	@Override
	public List<Order> getAll() {
		return new ArrayList<>(referencesOrders.values());
	}

	@Override
	public List<Order> findByUser(User client) {
		return new ArrayList<>(clientsOrders.getOrDefault(client, new HashSet<>()));
	}

	@Override
	public void save(Order order) {
		order.setReference(reference++);
		update(order);
	}

	@Override
	public Order update(Order order) {
		saveToReferencesOrders(order);
		saveToClientsOrders(order);
		return order;
	}

	private void saveToReferencesOrders(Order order) {
		referencesOrders.put(order.getReference(), order);
	}

	private void saveToClientsOrders(Order order) {
		User client = order.getClient();
		Set<Order> orders = clientsOrders.get(client);
		if (orders == null) {
			orders = new HashSet<>();
		}
		orders.add(order);
		clientsOrders.put(client, orders);
	}
}
