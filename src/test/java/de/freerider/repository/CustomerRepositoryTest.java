package de.freerider.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.freerider.datamodel.Customer;

@SpringBootTest
public class CustomerRepositoryTest {

	@Autowired
	CrudRepository<Customer, String> customerRepository;

	private Customer mats = new Customer("mustermann", "mats", "mats-mustermann@gmail.com");
	private Customer thomas = new Customer("mustermann", "thomas", "thomas-mustermann@gmail.com");

	@BeforeEach
	public void emptyRepo() {
		customerRepository.deleteAll();
	}

	@Test
	void testSaveCustomer() {
		mats.setId("123");
		thomas.setId("456");
		customerRepository.save(mats);
		customerRepository.save(thomas);
		assertEquals(2, customerRepository.count());
		assertEquals(mats, customerRepository.findById("123").get());
		assertEquals(thomas, customerRepository.findById("456").get());
	}

	@Test
	void testSaveNullId() {
		mats.setId(null);
		thomas.setId(null);
		assertNotNull(customerRepository.save(mats).getId());
		assertNotNull(customerRepository.save(thomas).getId());

	}

	@Test
	void testSaveNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			assertNull(customerRepository.save(null));
		});

	}

	@Test
	void testSaveTwice() {
		customerRepository.save(mats);
		customerRepository.save(thomas);
		customerRepository.save(mats);
		customerRepository.save(thomas);
		assertEquals(2, customerRepository.count());
	}

	@Test
	void testSaveSameId() {
		mats.setId("123");
		thomas.setId("123");
		customerRepository.save(mats);
		customerRepository.save(thomas);
		assertEquals(1, customerRepository.count());
		assertEquals(thomas, customerRepository.findById("123").get());
	}

	@Test
	void testSaveAll() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		mats.setId("123");
		thomas.setId("456");
		customers.add(mats);
		customers.add(thomas);
		customerRepository.saveAll(customers);
		assertEquals(2, customerRepository.count());
		assertEquals(mats, customerRepository.findById("123").get());
		assertEquals(thomas, customerRepository.findById("456").get());
	}

	@Test
	void testSaveAllNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			assertNull(customerRepository.saveAll(null));
		});
	}

	@Test
	void testSaveAllTwice() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		mats.setId("123");
		thomas.setId("456");
		customers.add(mats);
		customers.add(thomas);
		customerRepository.saveAll(customers);
		customerRepository.saveAll(customers);
		assertEquals(2, customerRepository.count());
	}

	@Test
	void testFindById() {
		mats.setId("123");
		thomas.setId("456");
		customerRepository.save(mats);
		customerRepository.save(thomas);
		assertEquals(mats, customerRepository.findById("123").get());
		assertEquals(thomas, customerRepository.findById("456").get());

	}

	@Test
	void testFindByIdNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			customerRepository.findById(null);
		});

	}

	@Test
	void testFindByFalseId() {
		assertEquals(Optional.empty(), customerRepository.findById("324"));
	}

	@Test
	void testFindAll() {
		mats.setId("123");
		thomas.setId("456");
		ArrayList<Customer> customers = new ArrayList<Customer>();
		customers.add(mats);
		customers.add(thomas);
		customerRepository.saveAll(customers);
		for (Customer c : customerRepository.findAll()) {
			assertTrue(customers.contains(c));
		}

	}

	@Test
	void testFindAllEmpty() {
		assertEquals("[]", customerRepository.findAll().toString());
	}

	@Test
	void testFindAllById() {
		ArrayList<String> ids = new ArrayList<String>();
		ids.add("123");
		ids.add("456");
		mats.setId("123");
		thomas.setId("456");

		HashSet<Customer> foundCustomers = new HashSet<Customer>();
		foundCustomers.add(mats);
		foundCustomers.add(thomas);

		customerRepository.saveAll(foundCustomers);

		assertTrue(customerRepository.findAllById(ids).equals(foundCustomers));

	}

	@Test
	void testFindAllByIdNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			assertNull(customerRepository.findAllById(null));
		});

	}

	@Test
	void testFindAllByFalseId() {
		ArrayList<String> ids = new ArrayList<String>();
		ids.add("4535");
		ids.add("42334");

		HashSet<Customer> foundCustomers = new HashSet<Customer>();
		foundCustomers.add(mats);
		foundCustomers.add(thomas);

		customerRepository.saveAll(foundCustomers);

		assertFalse(customerRepository.findAllById(ids).equals(foundCustomers));

	}

	@Test
	void testCount() {
		assertEquals(0, customerRepository.count());
		customerRepository.save(mats);
		assertEquals(1, customerRepository.count());
		customerRepository.save(thomas);
		assertEquals(2, customerRepository.count());
	}

	@Test
	void testDeleteById() {
		mats.setId("123");
		thomas.setId("456");
		customerRepository.save(mats);
		customerRepository.save(thomas);
		assertEquals(2, customerRepository.count());
		customerRepository.deleteById("123");
		customerRepository.deleteById("456");
		assertEquals(0, customerRepository.count());
	}

	@Test
	void testDeleteByFalseId() {
		mats.setId("123");
		customerRepository.save(mats);
		assertEquals(1, customerRepository.count());
		customerRepository.deleteById("2323");
		assertEquals(1, customerRepository.count());
	}

	@Test
	void testDelete() {
		customerRepository.save(mats);
		customerRepository.save(thomas);
		assertEquals(2, customerRepository.count());
		customerRepository.delete(mats);
		customerRepository.delete(thomas);
		assertEquals(0, customerRepository.count());

	}

	@Test
	void testDeleteNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			customerRepository.delete(null);
		});
	}

	@Test
	void testDeleteFalseEntity() {
		customerRepository.save(mats);
		customerRepository.save(thomas);
		assertEquals(2, customerRepository.count());
		assertThrows(IllegalArgumentException.class, () -> {
			customerRepository.delete(new Customer("Paul", "Gronemeyer", "017462289"));
		});

		assertEquals(2, customerRepository.count());
	}

	@Test
	void testDeleteAllById() {
		ArrayList<String> ids = new ArrayList<String>();
		ids.add("123");

		mats.setId("123");
		thomas.setId("456");

		customerRepository.save(mats);
		customerRepository.save(thomas);

		assertEquals(2, customerRepository.count());

		customerRepository.deleteAllById(ids);

		assertEquals(1, customerRepository.count());

	}

	@Test
	void testDeleteAllList() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		mats.setId("123");
		thomas.setId("456");
		customers.add(mats);
		customers.add(thomas);
		customerRepository.saveAll(customers);
		assertEquals(2, customerRepository.count());
		customerRepository.deleteAll(customers);
		assertEquals(0, customerRepository.count());
	}

	@Test
	void testDeleteAllFalse() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		mats.setId("123");
		thomas.setId("456");
		customers.add(mats);
		customers.add(thomas);
		customerRepository.saveAll(customers);
		assertEquals(2, customerRepository.count());
		customers.add(new Customer("Gronemeyer", "Paul", "020923981"));
		assertThrows(IllegalArgumentException.class, () -> {
			customerRepository.deleteAll(customers);
		});

		assertEquals(0, customerRepository.count());
	}

	@Test
	void testDeleteAll() {
		customerRepository.save(mats);
		customerRepository.save(thomas);
		assertEquals(2, customerRepository.count());
		customerRepository.deleteAll();
		assertEquals(0, customerRepository.count());
	}

}
