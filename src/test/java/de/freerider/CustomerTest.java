package de.freerider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import de.freerider.model.Customer;

@SpringBootTest
class CustomerTest {

	private Customer mats;
	private Customer thomas;

	@BeforeEach
	public void createCustomer() {
		mats = new Customer("mustermann", "mats", "mats-mustermann@gmail.com");
		thomas = new Customer("mustermann", "thomas", "thomas-mustermann@gmail.com");
	}

	@Test
	void testIdNull() {
		assertNull(mats.getId());
		assertNull(thomas.getId());
	}

	@Test
	void testSetId() {
		mats.setId("12345");
		assertEquals("12345", mats.getId());
	}

	@Test
	void testSetIdOnlyOnce() {
		mats.setId("12345");
		mats.setId("6789");
		assertEquals("12345", mats.getId());
	}

	@Test
	void testResetId() {
		mats.setId("12345");
		mats.setId(null);
		assertNull(mats.getId());
	}

	@Test
	void testNamesInitial() {
		assertNotNull(mats.getFirstName());
		assertNotNull(mats.getLastName());
		assertNotNull(thomas.getFirstName());
		assertNotNull(thomas.getLastName());
	}

	@Test
	void testNamesSetNull() {
		mats.setFirstName(null);
		mats.setLastName(null);
		thomas.setFirstName(null);
		thomas.setLastName(null);
		assertEquals("", mats.getFirstName());
		assertEquals("", mats.getLastName());
		assertEquals("", thomas.getFirstName());
		assertEquals("", thomas.getLastName());

	}

	@Test
	void testSetNames() {
		mats.setFirstName("martina");
		mats.setLastName("musterfrau");
		thomas.setFirstName("theresa");
		thomas.setLastName("musterfrau");
		assertEquals("martina", mats.getFirstName());
		assertEquals("musterfrau", mats.getLastName());
		assertEquals("theresa", thomas.getFirstName());
		assertEquals("musterfrau", thomas.getLastName());
	}

	@Test
	void testContactsInitial() {
		assertNotNull(mats.getContact());
		assertNotNull(mats.getContact());
		assertNotNull(thomas.getContact());
		assertNotNull(thomas.getContact());
	}

	@Test
	void testContactSetNull() {
		mats.setContact(null);
		thomas.setContact(null);
		assertEquals("", mats.getContact());
		assertEquals("", mats.getContact());
	}

	@Test
	void testSetContact() {
		mats.setContact("0174746383");
		thomas.setContact("0173563028");
		assertEquals("0174746383", mats.getContact());
		assertEquals("0173563028", thomas.getContact());
	}

	@Test
	void testStatusInitial() {
		assertEquals(Customer.Status.New, mats.getStatus());
	}

	@Test
	void testSetStatus() {
		mats.setStatus(Customer.Status.New);
		assertEquals(Customer.Status.New, mats.getStatus());
		mats.setStatus(Customer.Status.InRegistration);
		assertEquals(Customer.Status.InRegistration, mats.getStatus());
		mats.setStatus(Customer.Status.Active);
		assertEquals(Customer.Status.Active, mats.getStatus());
		mats.setStatus(Customer.Status.Suspended);
		assertEquals(Customer.Status.Suspended, mats.getStatus());
		mats.setStatus(Customer.Status.Deleted);
		assertEquals(Customer.Status.Deleted, mats.getStatus());
	}

}
