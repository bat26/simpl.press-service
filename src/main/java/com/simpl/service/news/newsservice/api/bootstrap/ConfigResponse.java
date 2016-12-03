package com.simpl.service.news.newsservice.api.bootstrap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Client configuration.
 *
 * Represents store, localisation, feature flags and other details the bootstrapping client application needs to
 * complete initialisation.
 */
@JsonIgnoreProperties(ignoreUnknown = false)
@ApiModel(description = "Client configuration")
public class ConfigResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String websiteLocale;
    private Integer websiteId;
    private String languageCode;
    private String defaultCurrencyCode;

    public ConfigResponse() { }

    public ConfigResponse(final Builder builder) {
        this.websiteLocale = builder.websiteLocale;
        this.websiteId = builder.websiteId;
        this.languageCode = builder.languageCode;
        this.defaultCurrencyCode = builder.defaultCurrencyCode;
    }

    /**
     * @return New builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * @return Website locale (e.g. "uk", "es-us")
     */
    @ApiModelProperty(value = "Website locale", required = true, example = "es-us")
    public String getWebsiteLocale() {
        return websiteLocale;
    }

    public void setWebsiteLocale(final String websiteLocale) {
        this.websiteLocale = websiteLocale;
    }

    /**
     * @return Website ID
     */
    @ApiModelProperty(value = "Website ID", required = true, example = "2")
    public Integer getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(final Integer websiteId) {
        this.websiteId = websiteId;
    }

    /**
     * @return Two-character ISO language code (e.g. "en")
     */
    @ApiModelProperty(value = "Language code", required = true, example = "es")
    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(final String languageCode) {
        this.languageCode = languageCode;
    }

    /**
     * @return Three-character ISO currency code (e.g. "GBP")
     */
    @ApiModelProperty(value = "Default currency code", required = true, example = "USD")
    public String getDefaultCurrencyCode() {
        return defaultCurrencyCode;
    }

    public void setDefaultCurrencyCode(final String defaultCurrencyCode) {
        this.defaultCurrencyCode = defaultCurrencyCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("websiteLocale", websiteLocale)
                .append("websiteId", websiteId)
                .append("languageCode", languageCode)
                .append("defaultCurrencyCode", defaultCurrencyCode)
                .toString();
    }

    /**
     * Builder.
     */
    public static class Builder {

        private String websiteLocale;
        private Integer websiteId;
        private String languageCode;
        private String defaultCurrencyCode;

        public Builder withWebsiteLocale(final String websiteLocale) {
            this.websiteLocale = websiteLocale;
            return this;
        }

        public Builder withWebsiteId(final Integer websiteId) {
            this.websiteId = websiteId;
            return this;
        }

        public Builder withLanguageCode(final String languageCode) {
            this.languageCode = languageCode;
            return this;
        }

        public Builder withDefaultCurrencyCode(final String defaultCurrencyCode) {
            this.defaultCurrencyCode = defaultCurrencyCode;
            return this;
        }

        public ConfigResponse build() {
            return new ConfigResponse(this);
        }
    }
}
