package com.simpl.service.news.newsservice.resource;

import io.dropwizard.jersey.caching.CacheControl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Status endpoint that supports health-checking by infrastructure components.
 */
@Path("/status")
@Produces("text/plain;charset=UTF-8")
public class StatusResource {

    /**
     * Status report.
     *
     * @return Status report
     */
    @GET
    @CacheControl(noCache = true)
    public String status() {
        //TODO Actually check some things!
        return "checkout-service: OK\n<!-- zxtm -->\n<!-- zxtm_alliswell -->\n";
    }
}
