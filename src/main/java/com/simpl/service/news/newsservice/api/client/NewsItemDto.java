package com.simpl.service.news.newsservice.api.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

/**
 * News Item
 *
 * This descirbes the full news item and its components
 *
 * It could have text, esri map object, or other media
 */
@JsonIgnoreProperties(ignoreUnknown = false)
@ApiModel(description = "News Item")
public class NewsItemDto implements Serializable {

    private static final long serialVersionUID = 1L;


    private List<NewsItemComponentDto> newsItemComponent;
    private String id;
    private String summary;


    public NewsItemDto() {
    }

    public NewsItemDto(final Builder builder) {
        this.id = builder.id;
        this.summary = builder.summary;
    }

    /**
     * @return New builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     */
    @ApiModelProperty(value = "Newsitem id", required = true, example = "123123")
    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    @ApiModelProperty(value = "News summary", required = true, example = "Blah blah")
    public String getSummary() {
        return summary;
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

        private String id;
        private String summary;

        public Builder withId(final String id) {
            this.id = id;
            return this;
        }

        public Builder withSummary(final String summary) {
            this.summary = summary;
            return this;
        }

        public NewsItemDto build() {
            return new NewsItemDto(this);
        }
    }
}
