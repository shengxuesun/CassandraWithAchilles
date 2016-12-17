package sample.config;

/**
 * Jersey configuration using JAX-RS standard Application class
 *
 * @author Andrii Duplyk
 *
 */
// @ApplicationPath("/")
public class JerseyApplication {

	// extends Application {

	// public JerseyApplication() {
	// BeanConfig beanConfig = new BeanConfig();
	// beanConfig.setVersion("1.0.2");
	// beanConfig.setSchemes(new String[] { "http" });
	// beanConfig.setHost("localhost:8080");
	// beanConfig.setBasePath("/api");
	// beanConfig.setResourcePackage("io.swagger.resources");
	// beanConfig.setScan(true);
	//
	// }
	//
	// @Override
	// public Set<Class<?>> getClasses() {
	// HashSet<Class<?>> set = new HashSet<>();
	// set.add(CustomerEndpoint.class);
	//
	// // swagger
	// set.add(io.swagger.jaxrs.listing.ApiListingResource.class);
	// set.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
	//
	// return set;
	// }
	//
	// /**
	// * We need to use swagger ui static resources
	// */
	// @Override
	// public Map<String, Object> getProperties() {
	//
	// Map<String, Object> propertiesMap = new HashMap<>();
	// // propertiesMap.put(FILTER_STATIC_CONTENT_REGEX, "/.*html");
	// propertiesMap.put(FILTER_FORWARD_ON_404, true);
	//
	// return propertiesMap;
	//
	// }

}
