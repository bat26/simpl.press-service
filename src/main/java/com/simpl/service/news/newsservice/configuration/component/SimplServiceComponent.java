package com.simpl.service.news.newsservice.configuration.component;

import com.simpl.service.news.newsservice.resource.ClientApiResource;
import com.simpl.service.news.newsservice.resource.IndexResource;
import com.simpl.service.news.newsservice.resource.StatusResource;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Dependency Injection component for the Checkout Service.
 */
@Singleton
@Component(modules = { ApiResourceModule.class, OperationsModule.class, PageResourceModule.class })
public interface SimplServiceComponent {

    /**
     * @return Client API request handler
     */
    ClientApiResource clientApiResource();

    /**
     * @return Status request handler
     */
    StatusResource statusResource();

    /**
     * @return Service index page resource (internal use only)
     */
    IndexResource indexResource();

}
