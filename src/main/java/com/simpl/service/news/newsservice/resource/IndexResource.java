package com.simpl.service.news.newsservice.resource;

import com.simpl.service.news.newsservice.view.IndexView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Index page.
 */
@Path("/")
@Produces("text/html;charset=UTF-8")
public class IndexResource {

    /**
     * Index page.
     *
     * @return View
     */
    @GET
    public IndexView index() {
        return new IndexView();
    }
}
