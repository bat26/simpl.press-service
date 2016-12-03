package com.simpl.service.news.newsservice.configuration.component;

import com.simpl.service.news.newsservice.resource.IndexResource;
import com.simpl.service.news.newsservice.resource.StatusResource;
import dagger.Module;
import dagger.Provides;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Operational resource dependencies.
 */
@Module
public class OperationsModule {

    /**
     * @return Status resource
     */
    @Provides
    @Singleton
    public StatusResource provideStatusResource() {
        return new StatusResource();
    }

    /**
     * @return Index page resource: service home page for internal use
     */
    @Provides
    @Singleton
    @Inject
    public IndexResource provideIndexResource() {
        return new IndexResource();
    }
}
