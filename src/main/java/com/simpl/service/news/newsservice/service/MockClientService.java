package com.simpl.service.news.newsservice.service;

import com.google.common.collect.ImmutableList;
import com.simpl.service.news.newsservice.api.client.ClientApi;
import com.simpl.service.news.newsservice.api.client.NewsCategoryDto;
import com.simpl.service.news.newsservice.api.client.NewsItemDto;
import com.simpl.service.news.newsservice.api.client.NewsListItemDto;
import com.simpl.service.news.newsservice.newsapi.NewsArticle;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Mock Client API implementation.
 */
public class MockClientService implements ClientApi {

    Map<String, String> idToUrl;
    String[] list = {"POSITIVE", "NEGATIVE", "NEUTRAL"};
    Random r = new Random();

    public MockClientService() {
        idToUrl = new HashMap<>();
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
    public List<NewsListItemDto> getNewsForCategory(String category) {

        NewsArticle a= new NewsArticle();
        a.getNewsListByCategory();
        idToUrl = a.getIdUrl();
        return a.getNewsListByCategory();
       }

    @Override
    public NewsItemDto getMoreNewsForThis(final String  newsId) throws IOException {

        NewsArticle a = new NewsArticle();
        String summaryUrl = idToUrl.get(newsId);

        String summary = a.getSummary(summaryUrl);
        System.out.println(summary);
        System.out.println(newsId);
        return NewsItemDto.builder()
                .withId(newsId)
                .withSummary(summary)
                .withSentiment(list[r.nextInt(list.length)])
                .withReputation("GOOD")
                .withUrl(summaryUrl)
                .build();
    }

    @Override
    public List<NewsListItemDto> getCategorizedNews(String filter){
        NewsArticle a= new NewsArticle();
        a.getFilteredList(filter);
        idToUrl = a.getIdUrl();
        return a.getFilteredList(filter);
    }
}
