package com.simpl.service.news.newsservice.newsapi;

import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyDataNews;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Article;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Document;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentsResult;
import com.simpl.service.news.newsservice.api.client.NewsListItemDto;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NewsArticle {

    public NewsArticle() {


    }

    public List<NewsListItemDto> getNewsListByCategory(String arg1, String arg2) {

        AlchemyDataNews service = new AlchemyDataNews();
        service.setApiKey("355266491b345cda940733f6558d1db3373c9780");
        Map<String, Object> params = new HashMap<String, Object>();
        String[] fields =
                new String[]{"enriched.url.title", "enriched.url.url", "enriched.url.author",
                        "enriched.url.publicationDate", "enriched.url.enrichedTitle.entities",
                        "enriched.url.enrichedTitle.docSentiment"};
        params.put(AlchemyDataNews.RETURN, StringUtils.join(fields, ","));
        params.put(AlchemyDataNews.START,"1440720000");
        params.put(AlchemyDataNews.END, "1441407600");
        params.put(AlchemyDataNews.COUNT, 7);

        DocumentsResult result = service.getNewsDocuments(params).execute();
        List<NewsListItemDto> listOfNews = new ArrayList<>();
        for (Document i: result.getDocuments().getDocuments()) {
            Article article = i.getSource().getEnriched().getArticle();
            //article url -> sentiment.get
            listOfNews.add(NewsListItemDto.builder()
                    .withId(i.getId())
                    .withTitle(article.getTitle())
                    .withSummary(i.getSource().getEnriched().getArticle().getText())
                    .withSentiment("positive")
                    .withReputation(NewsListItemDto.Reputation.GOOD)
                    .withPublishDate(new DateTime()).build());
        }

        return listOfNews;
    }
}