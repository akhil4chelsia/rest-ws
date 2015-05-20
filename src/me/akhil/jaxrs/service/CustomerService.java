package me.akhil.jaxrs.service;

import java.util.List;

import me.akhil.jaxrs.model.Customer;

public interface CustomerService {
	public Integer saveCustomerService(Customer customer);
	public List<Customer> getCustomerListService();
	public Customer getCustomerByIdService(int id);
}
