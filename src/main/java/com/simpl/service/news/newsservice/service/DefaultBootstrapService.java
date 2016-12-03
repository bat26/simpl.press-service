package com.simpl.service.news.newsservice.service;

import com.simpl.service.news.newsservice.api.bootstrap.BootstrapApi;
import com.simpl.service.news.newsservice.api.bootstrap.ConfigResponse;
import com.simpl.service.news.newsservice.api.bootstrap.GetConfigRequest;
import com.moo.website.WebsiteLocale;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.BadRequestException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Default Bootstrap API implementation.
 */
public class DefaultBootstrapService implements BootstrapApi {

    @Override
    public ConfigResponse getConfig(final GetConfigRequest request) {
        checkNotNull(request, "request required");

        final WebsiteLocale websiteLocale = websiteLocaleFor(request.getWebsiteLocale());
        return ConfigResponse.builder()
                .withWebsiteLocale(websiteLocale.getId())
                .withWebsiteId(websiteLocale.getWebsiteId())
                .withLanguageCode(websiteLocale.getLanguageCode())
                .withDefaultCurrencyCode(websiteLocale.getDefaultCurrencyCode())
                .build();
    }

    /**
     * Resolve a website locale object from a string identifier.
     *
     * @param websiteLocaleId Website locale ID (e.g. 'es-us')
     * @return Website locale
     * @throws BadRequestException when the ID is missing or not recognised as a valid website locale
     */
    private WebsiteLocale websiteLocaleFor(final String websiteLocaleId) {
        if (StringUtils.isBlank(websiteLocaleId)) {
            throw new BadRequestException("Website locale missing");
        }
        try {
            return WebsiteLocale.fromString(websiteLocaleId);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid website locale: " + websiteLocaleId);
        }
    }
}
