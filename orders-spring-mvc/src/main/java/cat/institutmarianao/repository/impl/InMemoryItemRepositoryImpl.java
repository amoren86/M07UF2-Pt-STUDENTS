package cat.institutmarianao.repository.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.ApplicationScope;

import cat.institutmarianao.domain.Item;
import cat.institutmarianao.repository.ItemRepository;

@Repository
@ApplicationScope
public class InMemoryItemRepositoryImpl implements ItemRepository {

	private static final List<Item> items = new ArrayList<>();

	private static long reference = 1l;

	public InMemoryItemRepositoryImpl() {
		items.add(createItem("Big Pig", "Cerdito bonito y apestoso de gran calidad", 23.90, "cerdo.jpg"));
		items.add(createItem("Silly Monkey", "Simpático monito que hará las delícias de los más pequeños", 24.90,
				"mono.jpg"));
		items.add(createItem("Fat Bear", "El oso amoroso que le hará coger el sueño con sólo abrazarlo", 25.00,
				"oso.jpg"));
		items.add(createItem("Vaca Paca", "La vaca más realista del mundo de los peluches. Sólo le falta andar", 23.80,
				"vaca.jpg"));
	}

	@Override
	public Item get(Long reference) {
		return items.stream().filter(item -> Objects.equals(item.getReference(), reference)).findAny().orElse(null);
	}

	@Override
	public List<Item> getAll() {
		return items;
	}

	private Item createItem(String name, String description, Double price, String image) {
		Item item = new Item();
		item.setReference(reference++);
		item.setName(name);
		item.setDescription(description);
		item.setPrice(price);
		item.setImage(readImage(image));
		return item;
	}

	private byte[] readImage(String filename) {
		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("images/" + filename);
			return inputStream.readAllBytes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new byte[0];
	}
}
