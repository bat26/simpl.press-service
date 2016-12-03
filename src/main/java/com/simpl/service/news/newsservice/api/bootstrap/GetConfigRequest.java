package com.simpl.service.news.newsservice.api.bootstrap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Request for client configuration.
 */
@ApiModel(description = "Request for client configuration")
@JsonIgnoreProperties(ignoreUnknown = false)
public class GetConfigRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String websiteLocale;

    public GetConfigRequest() { }

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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("websiteLocale", websiteLocale)
                .toString();
    }
}
