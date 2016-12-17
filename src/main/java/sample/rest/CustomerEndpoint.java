package sample.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import sample.domain.Customer;
import sample.repository.CustomerRepository;

/**
 *
 * REST endpoint for customers.
 *
 * @author Andrii Duplyk
 *
 */
@Path("/customers")
public class CustomerEndpoint {

	@Inject
	CustomerRepository customerRepository;

	@GET
	public Response getCustomers() {
		List<Customer> customerList = customerRepository.getCustomersList();
		return Response.status(Response.Status.OK).entity(customerList).build();
	}

	@POST
	public Response insertCustomer(Customer customer) {
		return Response.status(Response.Status.CREATED).entity(customerRepository.saveCustomer(customer)).build();
	}

	@PUT
	@Path("{id}")
	public Response updateCustomer(Customer customer) {
		return Response.status(Response.Status.OK).entity(customer).build();
	}

	@GET
	@Path("{id}")
	public Response getCustomer(@PathParam("id") Long id) {
		return Response.status(Response.Status.OK).entity(customerRepository.getCustomer(id)).build();
	}

	@DELETE
	@Path("{id}")
	public Response deleteCustomer(@PathParam("id") Long id) {
		customerRepository.deleteCustomer(id);
		return Response.noContent().build();
	}

}
