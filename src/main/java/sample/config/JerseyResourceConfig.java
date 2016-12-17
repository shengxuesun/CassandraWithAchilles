package sample.config;

import static org.glassfish.jersey.servlet.ServletProperties.FILTER_FORWARD_ON_404;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import io.swagger.jaxrs.config.BeanConfig;

/**
 * @author Andrii Duplyk
 *
 */
@ApplicationPath("/")
// @ApplicationScoped
public class JerseyResourceConfig extends ResourceConfig {

	public JerseyResourceConfig() {
		packages("sample.rest, io.swagger.jaxrs.listing, sample.config");

		property(FILTER_FORWARD_ON_404, true);

		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("1.0.2");
		beanConfig.setSchemes(new String[] { "http" });
		beanConfig.setHost("localhost:8080");
		beanConfig.setBasePath("/api");
		beanConfig.setResourcePackage("io.swagger.resources");
		beanConfig.setScan(true);
	}

}
