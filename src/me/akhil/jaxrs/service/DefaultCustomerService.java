package me.akhil.jaxrs.service;

import java.util.List;

import me.akhil.jaxrs.model.Customer;
import me.akhil.jaxrs.repository.CustomerDAO;
import me.akhil.jaxrs.repository.HBCustomerDAO;

public class DefaultCustomerService implements CustomerService{

	CustomerDAO customerDAO;
	
	public DefaultCustomerService() {
		customerDAO = new HBCustomerDAO();
	}
	
	@Override
	public Integer saveCustomerService(Customer customer) {
		return customerDAO.saveCustomer(customer);
	}

	@Override
	public List<Customer> getCustomerListService() {
		return customerDAO.getCustomerList();
	}

	@Override
	public Customer getCustomerByIdService(int id) {
		return customerDAO.getCustomerById(id);
	}

}
