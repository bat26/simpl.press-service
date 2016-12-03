package com.simpl.service.news.newsservice;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.servlets.CacheBustingFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import io.paradoxical.dropwizard.swagger.AppSwaggerConfiguration;
import io.paradoxical.dropwizard.swagger.bundles.SwaggerUIBundle;
import io.swagger.models.Info;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

/**
 * CheckoutService application.
 */
public class SimplPressServiceApplication extends Application<SimplPressServiceConfiguration> {

    /**
     * Run the application.
     *
     * @param args Args
     * @throws Exception Exception
     */
    public static void main(final String[] args) throws Exception {
        new SimplPressServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "Simpl.press Service";
    }

    @Override
    public void initialize(final Bootstrap<SimplPressServiceConfiguration> bootstrap) {
        // Mustache template support
        bootstrap.addBundle(new ViewBundle<SimplPressServiceConfiguration>());

        // Static assets. A specific bundle is configured for /version to support serving this static file from the
        // base path.
        bootstrap.addBundle(new AssetsBundle("/assets/version", "/version", "version.txt", "versionAssets"));
        bootstrap.addBundle(new AssetsBundle("/assets"));

        // Swagger support
        bootstrap.addBundle(createSwaggerUIBundle());
    }

    @Override
    public void run(final SimplPressServiceConfiguration configuration, final Environment environment) {
        // Dependency Injection component

        addCacheBustingFilter(environment);
        addCrossOriginFilter(environment);
    }

    private SwaggerUIBundle createSwaggerUIBundle() {
        return new SwaggerUIBundle(environment -> {
            return new AppSwaggerConfiguration(environment) {
                {
                    setBasePath("/");
                    setResourcePackage(SimplPressServiceApplication.class.getPackage().getName());

                    final Info info = new Info();
                    info.setTitle("Simpl.press Service");
                    info.setDescription("Endpoints for bootstrapping and servicing simpl.press frontend applications");
                    setInfo(info);
                }
            };
        });
    }

    private void addCacheBustingFilter(final Environment environment) {
        // Never cache the current service version (a static asset)
        environment.servlets().addFilter("CacheBustingFilter", new CacheBustingFilter()).addMappingForUrlPatterns(
                EnumSet.of(DispatcherType.REQUEST), true, "/version", "/version/*");
    }

    private void addCrossOriginFilter(final Environment environment) {
        //TODO: Sensible CORS policy
        final FilterRegistration.Dynamic filter =
                environment.servlets().addFilter("CrossOriginFilter", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/api/*");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
    }
}
