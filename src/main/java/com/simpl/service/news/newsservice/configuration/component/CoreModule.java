package com.simpl.service.news.newsservice.configuration.component;

import com.simpl.service.news.newsservice.SimplPressServiceConfiguration;
import com.simpl.service.news.newsservice.api.client.ClientApi;
import com.simpl.service.news.newsservice.service.MockClientService;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Core dependencies.
 */
@Module
public class CoreModule {

    private final SimplPressServiceConfiguration simplPressServiceConfiguration;

    /**
     * Constructor.
     *
     * @param simplPressServiceConfiguration Service configuration
     */
    public CoreModule(final SimplPressServiceConfiguration simplPressServiceConfiguration) {
        this.simplPressServiceConfiguration =
                checkNotNull(simplPressServiceConfiguration, "simplPressServiceConfiguration required");
    }

    /**
     * Client service: processes requests that are initially handled by the Client API resource.
     *
     * @return Client service
     */
    @Provides
    @Singleton
    public ClientApi provideClientService() {
        if (simplPressServiceConfiguration.getService().isStubbed()) {
            return new MockClientService();
        }

        throw new RuntimeException("Non-stubbed service is currently unsupported");
    }
}
