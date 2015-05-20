package me.akhil.jaxrs.repository;

import java.util.List;

import me.akhil.jaxrs.model.Customer;
import me.akhil.jaxrs.utils.HBUtils;

public class HBCustomerDAO implements CustomerDAO{
	
	@Override
	public Integer saveCustomer(Customer customer) {		
		return HBUtils.save(customer);
	}

	@Override
	public List<Customer> getCustomerList() {
		return HBUtils.getList(Customer.class);
	}

	@Override
	public Customer getCustomerById(int id) {
		return (Customer)HBUtils.get(Customer.class, id);			
	}

}
