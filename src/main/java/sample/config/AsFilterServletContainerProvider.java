package sample.config;

import java.util.Set;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.internal.spi.ServletContainerProvider;

/**
 * Registering Jersey servlet container as Filter
 *
 * @author Andrii Duplyk
 *
 */

@Provider
public class AsFilterServletContainerProvider implements ServletContainerProvider {

	private static final Logger LOGGER = Logger.getLogger(AsFilterServletContainerProvider.class.getName());

	@Inject
	JerseyResourceConfig jerseyResourceConfig;

	// @Override
	public void preInit(ServletContext context, Set<Class<?>> classes) throws ServletException {
		final Class<? extends Application> applicationCls = getApplicationClass(classes);
		if (applicationCls != null) {
			final ApplicationPath appPath = applicationCls.getAnnotation(ApplicationPath.class);
			if (appPath == null) {
				LOGGER.warning("Application class is not annotated with ApplicationPath");
				return;
			}
			final String mapping = createMappingPath(appPath);
			addFilter(context, applicationCls, classes, mapping);
			// to stop Jersey servlet initializer from trying to register
			// another servlet
			classes.remove(applicationCls);
		}
	}

	private static void addFilter(ServletContext context, Class<? extends Application> cls, Set<Class<?>> classes,
			String mapping) {
		final ResourceConfig resourceConfig = ResourceConfig.forApplicationClass(cls, classes);
		final ServletContainer filter = new ServletContainer(resourceConfig);
		final FilterRegistration.Dynamic registration = context.addFilter(cls.getName(), filter);
		registration.addMappingForUrlPatterns(null, true, mapping);
		registration.setAsyncSupported(true);
	}

	private void addFilter21(ServletContext context) {
		Class<? extends Application> configClass = jerseyResourceConfig.getClass();
		Set<Class<?>> resourceClasses = jerseyResourceConfig.getClasses();

		final ResourceConfig resourceConfig = ResourceConfig.forApplicationClass(configClass, resourceClasses);
		final ServletContainer filter = new ServletContainer(resourceConfig);
		final FilterRegistration.Dynamic registration = context.addFilter(configClass.getName(), filter);

		// to stop Jersey servlet initializer from trying to register
		// another servlet. Place may be wrong !!!
		// resourceClasses.remove(configClass);

		final ApplicationPath appPath = configClass.getAnnotation(ApplicationPath.class);
		if (appPath == null) {
			LOGGER.warning("Application class is not annotated with ApplicationPath");
			return;
		}
		final String mapping = createMappingPath(appPath);

		registration.addMappingForUrlPatterns(null, true, mapping);
		registration.setAsyncSupported(true);

	}

	private static Class<? extends Application> getApplicationClass(Set<Class<?>> classes) {
		Class<? extends Application> applicationCls = null;
		for (Class<?> cls : classes) {
			if (Application.class.isAssignableFrom(cls)) {
				applicationCls = cls.asSubclass(Application.class);
				break;
			}
		}
		if (applicationCls == null) {
			LOGGER.warning("No Application Class Found");
		}
		return applicationCls;
	}

	private static String createMappingPath(final ApplicationPath ap) {
		String path = ap.value();
		if (!path.startsWith("/")) {
			path = "/" + path;
		}

		if (!path.endsWith("/*")) {
			if (path.endsWith("/")) {
				path += "*";
			} else {
				path += "/*";
			}
		}

		return path;
	}

	@Override
	public void configure(ResourceConfig config) throws ServletException {
	}

	@Override
	public void init(ServletContext context) throws ServletException {
		addFilter21(context);

	}

	@Override
	public void onRegister(ServletContext arg0, String... arg1) throws ServletException {
		// TODO Auto-generated method stub

	}

}
