package com.simpl.service.news.newsservice.newsapi;

import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyDataNews;
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Article;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Document;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentSentiment;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentsResult;
import com.simpl.service.news.newsservice.api.client.NewsListItemDto;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.core.UriBuilder;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class NewsArticle {

    Map<String, String> idUrl;
    private final String USER_AGENT = "Mozilla/5.0";
    public NewsArticle() {

        idUrl = new HashMap<>();
    }

    public DocumentSentiment getSentiment(String url) {
        AlchemyLanguage service = new AlchemyLanguage();
        service.setApiKey("355266491b345cda940733f6558d1db3373c9780");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(AlchemyLanguage.URL, url);
        return service.getSentiment(params).execute();
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
        params.put(AlchemyDataNews.START, TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - 16400000));
        params.put(AlchemyDataNews.END, TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        params.put(AlchemyDataNews.COUNT, 10);

        DocumentsResult result = service.getNewsDocuments(params).execute();
        List<NewsListItemDto> listOfNews = new ArrayList<>();
        for (Document i : result.getDocuments().getDocuments()) {


            Article article = i.getSource().getEnriched().getArticle();
            String al[]=article.getPublicationDate().getDate().toString().split(" ");
            al[0]= al[0]+al[1]+al[2];
            //article url -> sentiment.get
            listOfNews.add(NewsListItemDto.builder()
                    .withId(i.getId())
                    .withTitle(article.getTitle())
                    .withSummary(i.getSource().getEnriched().getArticle().getText())
                    .withSentiment("positive")
                    .withReputation(NewsListItemDto.Reputation.GOOD)
                    .withPublishDate(al[0]).build());
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
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());
        return response.toString();

    }

    public Map<String, String> getIdUrl() {
        return idUrl;
    }
}