package com.simpl.service.news.newsservice.service;

import com.google.common.collect.ImmutableList;
import com.simpl.service.news.newsservice.api.client.ClientApi;
import com.simpl.service.news.newsservice.api.client.NewsCategoryDto;
import com.simpl.service.news.newsservice.api.client.NewsItemDto;
import com.simpl.service.news.newsservice.api.client.NewsListItemDto;
import org.joda.time.DateTime;

import java.util.List;

/**
 * Mock Client API implementation.
 */
public class MockClientService implements ClientApi {

    public MockClientService() {
    }

    @Override
    public List<NewsCategoryDto> getNewsCategories() {

        return ImmutableList.of(
                NewsCategoryDto.builder().withCode("UKN").withName("UK News").build(),
                NewsCategoryDto.builder().withCode("USN").withName("US News").build(),
                NewsCategoryDto.builder().withCode("TEK").withName("Tech News").build()
                );
    }

    @Override
    public List<NewsListItemDto> getNewsForCategory() {
        return ImmutableList.of(
                NewsListItemDto.builder()
                        .withId(123321)
                        .withTitle("Test news title")
                        .withSummary("Test summary")
                        .withSentiment(NewsListItemDto.Sentiment.POSITIVE)
                        .withReputation(NewsListItemDto.Reputation.GOOD)
                        .withPublishDate(new DateTime()).build(),
                NewsListItemDto.builder()
                        .withId(123321)
                        .withTitle("Test news title")
                        .withSummary("Test summary")
                        .withSentiment(NewsListItemDto.Sentiment.NEGATIVE)
                        .withReputation(NewsListItemDto.Reputation.GOOD)
                        .withPublishDate(new DateTime()).build()
        );    }

    @Override
    public NewsItemDto getMoreNewsForThis(final int newsId) {

        return new NewsItemDto();
    }
}
