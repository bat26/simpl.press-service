package com.simpl.service.news.newsservice.configuration.component;

import com.simpl.service.news.newsservice.resource.LocalisedPageResource;
import dagger.Module;
import dagger.Provides;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Page resource dependencies.
 */
@Module
public class PageResourceModule {

    /**
     * @return Localised page resource: public pages
     */
    @Provides
    @Singleton
    @Inject
    public LocalisedPageResource provideLocalisedPageResource() {
        return new LocalisedPageResource();
    }
}
