package com.simpl.service.news.newsservice.api.bootstrap;

import com.simpl.service.news.newsservice.api.client.ClientApi;

/**
 * Bootstrap API for building and initialising client code.
 *
 * These operations are not expected to be publicly accessible. They are used internally during client application
 * compilation.
 *
 * @see ClientApi
 */
public interface BootstrapApi {

    /**
     * Get application configuration.
     *
     * Supplies the bootstrapping client application with store, localisation, feature flags and other details.
     *
     * @param request Request
     * @return Configuration
     */
    ConfigResponse getConfig(GetConfigRequest request);
}
