package sample.config;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ConsistencyLevel;

import info.archinnov.achilles.generated.ManagerFactory;
import info.archinnov.achilles.generated.ManagerFactoryBuilder;
import info.archinnov.achilles.generated.manager.Customer_Manager;

/**
 * Initialization of generated Customer_Manager
 *
 * @author Andrii Duplyk
 *
 */
@ApplicationScoped
public class EntryPoint implements Serializable {

	private static final long serialVersionUID = -2793069756593443760L;

	private Cluster cluster;

	private ManagerFactory managerFactory;

	@PostConstruct
	public void init() {
		cluster = Cluster.builder().addContactPoints("localhost").withClusterName("test").build();
		managerFactory = ManagerFactoryBuilder.builder(cluster).withDefaultKeyspaceName("sample")
				.withBeanValidation(true).withDefaultReadConsistency(ConsistencyLevel.LOCAL_ONE)
				.doForceSchemaCreation(true).build();

		// final Customer_Manager customerManager =
		// managerFactory.forCustomer();

	}

	@Produces
	public Customer_Manager createCustomerManager() {

		return managerFactory.forCustomer();
	}

}
