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
    private String sentiment;
    private String[] tags;
    private String url;
    private String reputation;


    public NewsItemDto() {
    }

    public NewsItemDto(final Builder builder) {
        this.id = builder.id;
        this.summary = builder.summary;
        this.sentiment = builder.sentiment;
        this.tags = builder.tags;
        this.url = builder.url;
        this.reputation = builder.reputation;
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

    public void setSummary(final String summary) {
        this.summary = summary;
    }

    @ApiModelProperty(value = "News sentiment", required = true, example = "Good bad")
    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(final String sentiment) {
        this.sentiment = sentiment;
    }

    @ApiModelProperty(value = "News tags", required = true, example = "a,b,c")
    public String[] getTags() {
        return tags;
    }

    public void setTags(final String[] tags) {
        this.tags = tags;
    }

    @ApiModelProperty(value = "News url", required = true, example = "www.google.com")
    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getReputation() {
        return reputation;
    }

    public void setReputation(final String reputation) {
        this.reputation = reputation;
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
        private String sentiment;
        private String[] tags;
        private String url;
        private String reputation;

        public Builder withId(final String id) {
            this.id = id;
            return this;
        }

        public Builder withSummary(final String summary) {
            this.summary = summary;
            return this;
        }

        public Builder withSentiment(final String sentiment) {
            this.sentiment = sentiment;
            return this;
        }

        public Builder withTags(final String[] tags) {
            this.tags = tags;
            return this;
        }

        public Builder withUrl(final String url) {
            this.url = url;
            return this;
        }

        public Builder withReputation(final String reputation) {
            this.reputation = reputation;
            return this;
        }

        public NewsItemDto build() {
            return new NewsItemDto(this);
        }
    }
}
