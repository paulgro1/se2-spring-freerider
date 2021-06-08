package de.freerider.repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.stereotype.Component;

import de.freerider.model.Customer;

@Component
class CustomerRepository implements CrudRepository<Customer, String> {
	//
	private final IDGenerator idGen = new IDGenerator("C", IDGenerator.IDTYPE.NUM, 6);
	private final HashMap<String, Customer> customers = new HashMap<String, Customer>();

	@Override
	public <S extends Customer> S save(S entity) {

		if (entity.getId() == null | entity.getId() == "") {

			entity.setId(idGen.nextId());

			findAll().forEach((element) -> {

				if (entity.getId() == element.getId())
					save(entity);
			});

		}

		customers.put(entity.getId(), entity);
		return entity;
	}

	@Override
	public <S extends Customer> Iterable<S> saveAll(Iterable<S> entities) {
		entities.forEach(this::save);

		return entities;
	}

	@Override
	public Optional<Customer> findById(String id) {

		Optional<Customer> opt = Optional.empty();

		for (Customer customer : findAll()) {
			if (customer.getId() == id)
				opt = Optional.of(customer);

		}
		return opt;
	}

	@Override
	public boolean existsById(String id) {
		if (findById(id).isEmpty())
			return false;
		else
			return true;

	}

	@Override
	public Iterable<Customer> findAll() {

		return customers.values();
	}

	@Override
	public Iterable<Customer> findAllById(Iterable<String> ids) {

		HashSet<Customer> foundCustomers = new HashSet<Customer>();

		ids.forEach(id -> {
			Customer foundCustomer = customers.get(id);
			if (foundCustomer != null) {
				foundCustomers.add(foundCustomer);
			}
		});

		return foundCustomers;
	}

	@Override
	public long count() {
		return customers.size();
	}

	@Override
	public void deleteById(String id) {
		customers.remove(id);

	}

	@Override
	public void delete(Customer entity) {
		if (entity == null) {
			throw new IllegalArgumentException();
		}
		customers.remove(entity.getId());

	}

	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		ids.forEach(this::deleteById);

	}

	@Override
	public void deleteAll(Iterable<? extends Customer> entities) {
		entities.forEach(this::delete);

	}

	@Override
	public void deleteAll() {
		customers.clear();

	}

}
