package cat.institutmarianao.repository;

import java.util.List;

import cat.institutmarianao.domain.Item;

public interface ItemRepository {
	Item get(Long reference);

	List<Item> getAll();
}
