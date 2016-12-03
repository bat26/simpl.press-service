package com.simpl.service.news.newsservice.api.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * NewsListItem
 */
@JsonIgnoreProperties(ignoreUnknown = false)
@ApiModel(description = "NewsListItem")
public class NewsListItemDto implements Serializable {

    private static final long serialVersionUID = 1L;

    public static enum Sentiment {
        POSITIVE,
        NEGATIVE,
        NEUTRAL
    }

    public static enum Reputation {
        GOOD,
        AVERAGE,
        POOR
    }

    private int id;
    private String title;
    private String summary;
    private DateTime publishDate;
    private Sentiment sentimentLevel;
    private Reputation reputationLevel;


    public NewsListItemDto() {
    }

    public NewsListItemDto(final Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.summary = builder.summary;
        this.publishDate = builder.publishDate;
        this.sentimentLevel = builder.sentimentLevel;
        this.reputationLevel = builder.reputationLevel;
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
    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("title", title)
                .toString();
    }

    @ApiModelProperty(value = "News title", required = true, example = "Old lady finds golden ticket")
    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    @ApiModelProperty(value = "News summary", required = true, example = "The ticket was in a hole under a big tree and was probably there for 10 years. She was very happy")
    public String getSummary() {
        return summary;
    }

    public void setSummary(final String summary) {
        this.summary = summary;
    }

    @ApiModelProperty(value = "News publish date", required = true, example = "27/12/16")
    public DateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(final DateTime publishDate) {
        this.publishDate = publishDate;
    }

    @ApiModelProperty(value = "News sentiment", required = true, example = "Positive")
    public Sentiment getSentimentLevel() {
        return sentimentLevel;
    }

    public void setSentimentLevel(final Sentiment sentimentLevel) {
        this.sentimentLevel = sentimentLevel;
    }

    public Reputation getReputationLevel() {
        return reputationLevel;
    }

    public void setReputationLevel(final Reputation reputationLevel) {
        this.reputationLevel = reputationLevel;
    }

    /**
     * Builder.
     */
    public static class Builder {

        private int id;
        private String title;
        private String summary;
        private DateTime publishDate;
        private Sentiment sentimentLevel;
        private Reputation reputationLevel;

        public Builder withId(final int id) {
            this.id = id;
            return this;
        }

        public Builder withTitle(final String title) {
            this.title = title;
            return this;
        }

        public Builder withSummary(final String summary) {
            this.summary = summary;
            return this;
        }

        public Builder withPublishDate(final DateTime publishDate) {
            this.publishDate = publishDate;
            return this;
        }

        public Builder withSentiment(final Sentiment sentiment) {
            this.sentimentLevel = sentiment;
            return this;
        }

        public Builder withReputation(final Reputation reputation) {
            this.reputationLevel = reputation;
            return this;
        }

        public NewsListItemDto build() {
            return new NewsListItemDto(this);
        }
    }
}
