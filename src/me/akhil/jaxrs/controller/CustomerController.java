package me.akhil.jaxrs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import me.akhil.jaxrs.model.Customer;
import me.akhil.jaxrs.service.CustomerService;
import me.akhil.jaxrs.service.DefaultCustomerService;

@Path("/customers")
public class CustomerController {

	CustomerService customerService;

	public CustomerController() {
		customerService = new DefaultCustomerService();
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> getNRICustomer() {
		return customerService.getCustomerListService();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Customer getThisCustomer(@PathParam("id") int id) {
		return customerService.getCustomerByIdService(id);
	}
	
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCustomer(Customer customer){
		int status = customerService.saveCustomerService(customer);
		return  Response.status(201).entity(status).build();
	}

}
