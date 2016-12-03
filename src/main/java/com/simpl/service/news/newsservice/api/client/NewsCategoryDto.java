package com.simpl.service.news.newsservice.api.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * NewsCategory.
 */
@JsonIgnoreProperties(ignoreUnknown = false)
@ApiModel(description = "NewsCategory")
public class NewsCategoryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;
    private String name;

    public NewsCategoryDto() {
    }

    public NewsCategoryDto(final Builder builder) {
        this.code = builder.code;
        this.name = builder.name;
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
    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    /**
     * @return Country name (not necessarily localised)
     */
    @ApiModelProperty(value = "Category name", required = true, example = "UK news")
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("code", code)
                .append("name", name)
                .toString();
    }

    /**
     * Builder.
     */
    public static class Builder {

        private String code;
        private String name;

        public Builder withCode(final String code) {
            this.code = code;
            return this;
        }

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public NewsCategoryDto build() {
            return new NewsCategoryDto(this);
        }
    }
}
