package com.simpl.service.news.newsservice;

import com.simpl.service.news.newsservice.configuration.server.Service;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * CheckoutService configuration.
 */
public class SimplPressServiceConfiguration extends Configuration {

    @Valid
    @NotNull
    private Service service;

    public Service getService() {
        return service;
    }

    public void setService(final Service service) {
        this.service = service;
    }
}
