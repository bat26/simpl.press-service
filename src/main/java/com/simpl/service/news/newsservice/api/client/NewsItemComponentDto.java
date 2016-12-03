package com.simpl.service.news.newsservice.api.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * This could be a text component, stats, map, video etc.
 */
@JsonIgnoreProperties(ignoreUnknown = false)
@ApiModel(description = "News Item Compoent")
public class NewsItemComponentDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String componentCode;


    public NewsItemComponentDto() {
    }

    public NewsItemComponentDto(final Builder builder) {
        this.id = builder.id;
    }

    /**
     * @return New builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     */
    @ApiModelProperty(value = "Category code", required = true, example = "UKN")
    public int getId() {
        return id;
    }

    public void setId(final String code) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .toString();
    }

    /**
     * Builder.
     */
    public static class Builder {

        private int id;

        public Builder withId(final int id) {
            this.id = id;
            return this;
        }

        public NewsItemComponentDto build() {
            return new NewsItemComponentDto(this);
        }
    }
}
