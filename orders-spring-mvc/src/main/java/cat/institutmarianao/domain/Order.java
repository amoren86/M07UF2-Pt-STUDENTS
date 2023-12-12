package cat.institutmarianao.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	// TODO enum
	public static final String[] STATES = { "order.state.pending", "order.state.transit", "order.state.delivery",
			"order.state.absent", "order.state.pending.collection", "order.state.returned" };

	private Long reference;

	@NotNull
	@Valid
	private User client;

	@NotNull
	@Valid
	private PostalAddress deliveryAddress;

	private Date startDate;

	private Date deliveryDate;

	@NotEmpty
	private Map<Item, Integer> items;

	@Min(value = 0)
	@Max(value = 5)
	private Integer state = 0;

	public Long getReference() {
		return reference;
	}

	public void setReference(Long reference) {
		this.reference = reference;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public PostalAddress getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(PostalAddress deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Map<Item, Integer> getItems() {
		if (items == null) {
			items = new HashMap<>();
		}
		return items;
	}

	public void setItems(Map<Item, Integer> items) {
		this.items = Objects.requireNonNullElse(items, new HashMap<>());
	}

	public Integer getTotalQuantity() {
		return items.values().stream().reduce(0, Integer::sum);
	}

	public Double getTotalAmount() {
		return items.entrySet().stream().map(e -> e.getKey().getPrice() * e.getValue()).reduce(0d, Double::sum);
	}

	@Override
	public int hashCode() {
		return Objects.hash(reference);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object instanceof Order order) {
			return Objects.equals(reference, order.reference);
		}
		return false;
	}

}
