package me.akhil.jaxrs.repository;

import java.util.List;

import me.akhil.jaxrs.model.Customer;

public interface CustomerDAO {
	
	public Integer saveCustomer(Customer customer);
	public List<Customer> getCustomerList();
	public Customer getCustomerById(int id);
	
}
