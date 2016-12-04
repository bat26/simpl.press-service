package com.simpl.service.news.newsservice.newsapi;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyDataNews;
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Article;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Concepts;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Document;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentSentiment;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentsResult;
import com.simpl.service.news.newsservice.api.client.NewsListItemDto;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;

import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.core.UriBuilder;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NewsArticle {

    BadNewsSites badNewsSites;
    Map<String, String> idUrl;
    private final String USER_AGENT = "Mozilla/5.0";
    public NewsArticle() {
        badNewsSites = new BadNewsSites();
        idUrl = new HashMap<>();
    }

    public DocumentSentiment getSentiment(String url) {
        AlchemyLanguage service = new AlchemyLanguage();
        service.setApiKey("355266491b345cda940733f6558d1db3373c9780");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(AlchemyLanguage.URL, url);
        return service.getSentiment(params).execute();
    }

    public Concepts getConcepts(String url) {
        AlchemyLanguage service = new AlchemyLanguage();
        service.setApiKey("355266491b345cda940733f6558d1db3373c9780");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(AlchemyLanguage.URL, url);
        return service.getConcepts(params).execute();
    }


    public List<NewsListItemDto> getNewsListByCategory() {

        AlchemyDataNews service = new AlchemyDataNews();
        service.setApiKey("355266491b345cda940733f6558d1db3373c9780");
        Map<String, Object> params = new HashMap<String, Object>();
        String[] fields =
                new String[]{"enriched.url.title", "enriched.url.url", "enriched.url.author",
                        "enriched.url.publicationDate", "enriched.url.enrichedTitle.entities",
                        "enriched.url.enrichedTitle.docSentiment"};
        params.put(AlchemyDataNews.RETURN, StringUtils.join(fields, ","));
        params.put(AlchemyDataNews.START,"now-1d");
        params.put(AlchemyDataNews.END,"now");
        params.put(AlchemyDataNews.COUNT, 50);

        DocumentsResult result = service.getNewsDocuments(params).execute();
        List<NewsListItemDto> listOfNews = new ArrayList<>();
        for (Document i : result.getDocuments().getDocuments()) {
            NewsListItemDto.Reputation reputation = NewsListItemDto.Reputation.GOOD;
            if (Arrays.asList(badNewsSites.getBadSites()).contains(i.getSource().getEnriched().getArticle().getAuthor())) {
                reputation = NewsListItemDto.Reputation.POOR;
            }
            JsonParser parser = new JsonParser();
            JsonObject rawJson = parser.parse(i.toString()).getAsJsonObject();
            Long dateAsLong = rawJson.get("timestamp").getAsLong()*1000;


            Article article = i.getSource().getEnriched().getArticle();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String happyDateString = sdf.format(dateAsLong);
            listOfNews.add(NewsListItemDto.builder()
                    .withId(i.getId())
                    .withTitle(article.getTitle())
                    .withSummary(i.getSource().getEnriched().getArticle().getText())
                    .withSentiment("positive")
                    .withReputation(reputation)
                    .withReputation(NewsListItemDto.Reputation.GOOD)
                    .withPublishDate(happyDateString).build());
            idUrl.put(i.getId(), i.getSource().getEnriched().getArticle().getUrl());
        }

        return listOfNews;
    }

    public List<NewsListItemDto> getFilteredList(String filterBy){

        AlchemyDataNews service = new AlchemyDataNews();
        service.setApiKey("355266491b345cda940733f6558d1db3373c9780");
        Map<String, Object> params = new HashMap<String, Object>();
        String[] fields =
                new String[]{"enriched.url.title", "enriched.url.url", "enriched.url.author",
                        "enriched.url.publicationDate", "enriched.url.enrichedTitle.entities",
                        "enriched.url.enrichedTitle.docSentiment"};
        params.put(AlchemyDataNews.RETURN, StringUtils.join(fields, ","));
        params.put(AlchemyDataNews.START,"now-1d");
        params.put(AlchemyDataNews.END,"now");
        params.put(AlchemyDataNews.COUNT, 50);
        params.put("q.enriched.url.enrichedTitle.taxonomy.taxonomy_.label", filterBy);

        DocumentsResult result = service.getNewsDocuments(params).execute();
        List<NewsListItemDto> listOfNews = new ArrayList<>();
        for (Document i : result.getDocuments().getDocuments()) {
            JsonParser parser = new JsonParser();
            JsonObject rawJson = parser.parse(i.toString()).getAsJsonObject();
            Long dateAsLong = rawJson.get("timestamp").getAsLong()*1000;


            Article article = i.getSource().getEnriched().getArticle();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String happyDateString = sdf.format(dateAsLong);
            listOfNews.add(NewsListItemDto.builder()
                    .withId(i.getId())
                    .withTitle(article.getTitle())
                    .withSummary(i.getSource().getEnriched().getArticle().getText())
                    .withSentiment("positive")
                    .withReputation(NewsListItemDto.Reputation.GOOD)
                    .withPublishDate(happyDateString).build());
            idUrl.put(i.getId(), i.getSource().getEnriched().getArticle().getUrl());
        }

        return listOfNews;
    }


    public String getSummary(String url) throws IOException {
        String requestUrl = "https://www.tools4noobs.com/";
        UriBuilder builder = UriBuilder
                .fromPath(requestUrl)
                .queryParam("action", "ajax_summarize")
                .queryParam("url", url)
                .queryParam("text", "")
                .queryParam("threshold", 70)
                .queryParam("threshold_lines", 3)
                .queryParam("min_sentence_length", 50)
                .queryParam("first_best", 10)
                .queryParam("min_word_length", 4);
        URL obj = builder.build().toURL();
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");



        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(obj.getQuery());
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + requestUrl);
        System.out.println("Post parameters : " + obj.getQuery());
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());
        String textonly = Jsoup.parse(response.toString()).text();
        System.out.println(textonly);
        String parts[] = textonly.split(".html:");
        if(parts.length > 1) {
            return parts[1];
        } else {
            return parts[0];
        }

    }

    public Map<String, String> getIdUrl() {
        return idUrl;
    }
}