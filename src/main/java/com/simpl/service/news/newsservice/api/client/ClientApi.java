package com.simpl.service.news.newsservice.api.client;

import java.util.List;

/**
 * Client API for running simpl.press
 *
 * These operations will be publicly accessible.
 *
 */
public interface ClientApi {

    /**
     *
     * Get a list of news categories
     */
    List<NewsCategoryDto> getNewsCategories();

    /**
     * Returns a list of {@link NewsListItemDto} for a given NewsCategory
     *
     * @return List
     */
    List<NewsListItemDto> getNewsForCategory();

    /**
     * Gets more data / stuff on the particular news article
     *
     * @return NewsItemDto
     */
    NewsItemDto getMoreNewsForThis(int id);
}
