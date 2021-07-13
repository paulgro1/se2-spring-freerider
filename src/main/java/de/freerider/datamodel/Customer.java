package de.freerider.datamodel;

import java.util.Optional;

import de.freerider.repository.CrudRepository;

public class Customer {

	private String id = null;
	private String lastName;
	private String firstName;
	private String contact;

	public enum Status {
		New, InRegistration, Active, Suspended, Deleted
	}

	private Status status = Status.New;

	public Customer(String lastName, String firstName, String contact) {
		setLastName(lastName);
		setFirstName(firstName);
		setContact(contact);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if (this.id == null || id == null)
			this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if (lastName == null)
			this.lastName = "";
		else
			this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if (firstName == null)
			this.firstName = "";
		else
			this.firstName = firstName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		if (contact == null)
			this.contact = "";
		else
			this.contact = contact;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}