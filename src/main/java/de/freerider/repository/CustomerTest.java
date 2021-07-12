package de.freerider.repository;

import java.util.HashSet;

import de.freerider.datamodel.Customer;

public class CustomerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Customer c1 = new Customer("Gronemeyer", "Paul", "paul.gronemeyer@gmail.com");
		Customer c2 = new Customer("Flottmeier", "Anja", "anjaistcool@gmail.com");
		Customer c3 = new Customer("Aschenbrenner", "Simon", "simonsimon@gmail.com");
		Customer c4 = new Customer("Iseken", "Till", "t.iseken@gmail.com");
		Customer c5 = new Customer("Lanfranca", "Lili", "lililan@gmail.com");
		
		CustomerRepository cusRep = new CustomerRepository();
		
		cusRep.save(c1);
		cusRep.save(c2);
		
		HashSet<Customer> customers = new HashSet<Customer>();
		customers.add(c3);
		customers.add(c4);
		customers.add(c5);
		
		cusRep.saveAll(customers);
		
		HashSet<String> ids = new HashSet<String>();
		
		for( Customer customer : cusRep.findAll()) {
			System.out.println(customer.getId() + " " + customer.getFirstName() + " " + customer.getLastName() + " " + customer.getContact());
			ids.add(customer.getId());
			System.out.println("findById is " + cusRep.findById(customer.getId()).isPresent() + ", therefore customer exists.");
			System.out.println("existsById is " + cusRep.existsById(customer.getId()) + ", therefore customer exists.\n");
		}
		
		System.out.println(cusRep.findAllById(ids));
		
		System.out.println("\nNumber of customers: " + cusRep.count());
		
		cusRep.save(c1);
		
		System.out.println("\nNumber of customers after adding c1 again: " + cusRep.count());
		
		cusRep.delete(c1);
		
		System.out.println("\nNumber of customers after deleting c1: " + cusRep.count());
		
		cusRep.deleteAll();
		
		System.out.println("\nNumber of customers after all: " + cusRep.count());
		
	}
	
	

}
