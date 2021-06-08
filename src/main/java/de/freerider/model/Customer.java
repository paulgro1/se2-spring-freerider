package de.freerider.model;

import java.util.Optional;

import de.freerider.repository.CrudRepository;

public class Customer {

	private String id = null;
	private String lastName;
	private String firstName;
	private String contact;
	private enum status {New, InRegistration, Active, Suspended, Deleted}
	
	public Customer(String lastName, String firstName, String contact) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.contact = contact;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	

}