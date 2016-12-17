package sample.repository;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import info.archinnov.achilles.generated.manager.Customer_Manager;
import sample.domain.Customer;

/**
 * @author Andrii Duplyk
 *
 */
@ApplicationScoped
public class CustomerRepository implements Serializable {

	private static final long serialVersionUID = -5546028905961911001L;

	@Inject
	Customer_Manager customerManager;

	public List<Customer> getCustomersList() {

		return customerManager.dsl().select().allColumns_FromBaseTable().without_WHERE_Clause().getList();
	}

	public Customer getCustomer(Long id) {
		return customerManager.dsl().select().allColumns_FromBaseTable().where().id().Eq(id).getOne();
	}

	public Customer saveCustomer(Customer customer) {
		customerManager.crud().insert(customer).ifNotExists().execute();
		return getCustomer(customer.getId());
	}

	public void updateCustomer(Customer customer) {
		customerManager.crud().insert(customer).execute();
	}

	public void deleteCustomer(Long id) {
		customerManager.crud().deleteById(id);
	}

}
