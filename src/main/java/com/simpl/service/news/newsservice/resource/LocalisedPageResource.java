package com.simpl.service.news.newsservice.resource;

import com.moo.website.WebsiteLocale;
import io.dropwizard.jersey.caching.CacheControl;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Localised pages.
 */
@Path(ResourcePath.PAGE_PATH + "/{websiteLocale:[a-z][a-z](-[a-z][a-z])?}")
@Produces("text/html;charset=UTF-8")
public class LocalisedPageResource {

    /**
     * Default checkout page.
     *
     * @param websiteLocale Website locale
     * @return Page
     */
    @GET
    @Path("checkout")
    @CacheControl(noCache = true)
    public String checkout(@PathParam("websiteLocale") @NotNull final WebsiteLocale websiteLocale) {
        return "Checkout";
    }
}
