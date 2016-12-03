package com.simpl.service.news.newsservice.configuration.component;

import com.simpl.service.news.newsservice.api.client.ClientApi;
import com.simpl.service.news.newsservice.resource.ClientApiResource;
import dagger.Module;
import dagger.Provides;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * API resource dependencies.
 */
@Module(includes = CoreModule.class)
public class ApiResourceModule {

    /**
     * Client API resource (controller).
     *
     * @param clientService Client API delegate
     * @return Resource
     */
    @Provides
    @Singleton
    @Inject
    public ClientApiResource provideClientApiResource(final ClientApi clientService) {
        return new ClientApiResource(clientService);
    }
}
