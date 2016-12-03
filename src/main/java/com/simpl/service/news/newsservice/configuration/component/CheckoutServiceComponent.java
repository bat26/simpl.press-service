package com.simpl.service.news.newsservice.configuration.component;

import com.simpl.service.news.newsservice.resource.BootstrapApiResource;
import com.simpl.service.news.newsservice.resource.ClientApiResource;
import com.simpl.service.news.newsservice.resource.IndexResource;
import com.simpl.service.news.newsservice.resource.LocalisedPageResource;
import com.simpl.service.news.newsservice.resource.StatusResource;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Dependency Injection component for the Checkout Service.
 */
@Singleton
@Component(modules = { ApiResourceModule.class, OperationsModule.class, PageResourceModule.class })
public interface CheckoutServiceComponent {

    /**
     * @return Client API request handler
     */
    ClientApiResource clientApiResource();

    /**
     * @return Bootstrap API request handler
     */
    BootstrapApiResource bootstrapApiResource();

    /**
     * @return Status request handler
     */
    StatusResource statusResource();

    /**
     * @return Service index page resource (internal use only)
     */
    IndexResource indexResource();

    /**
     * @return Localised page resource (used to serve initial checkout pages)
     */
    LocalisedPageResource localisedPageResource();

}
