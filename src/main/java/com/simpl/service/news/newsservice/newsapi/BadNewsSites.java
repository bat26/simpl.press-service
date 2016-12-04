package com.simpl.service.news.newsservice.newsapi;

/**
 * Created by bruce on 04/12/16.
 */
public class BadNewsSites {

    private String[] badSites = {
            "Occupy Democrats",
            "Bipartisan Report",
            "Winning Democrats",
            "PoliticusUSA",
            "Blue Nation Review",
            "IfYouOnlyNews",
            "USUncut",
            "The Freethought Project",
            "Addicting Info",
            "LiberalAmerica",
            "Newslo",
            "Politicalo",
            "DailyNewsBin",
            "theintellectualist",
            "Being Liberal",
            "American Newsx",
            "The Other 98%",
            "TMZ.com"
    };


    public BadNewsSites() {

    }

    public String[] getBadSites() {
        return badSites;
    }

    public void setBadSites(final String[] badSites) {
        this.badSites = badSites;
    }
}
