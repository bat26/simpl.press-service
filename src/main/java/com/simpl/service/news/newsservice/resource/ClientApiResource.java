package com.simpl.service.news.newsservice.resource;

import com.simpl.service.news.newsservice.api.client.ClientApi;
import com.simpl.service.news.newsservice.api.client.NewsCategoryDto;
import com.simpl.service.news.newsservice.api.client.NewsItemDto;
import com.simpl.service.news.newsservice.api.client.NewsListItemDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 */
@Path(ResourcePath.CLIENT_API_V1)
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = "client", produces = MediaType.APPLICATION_JSON)
public class ClientApiResource implements ClientApi {

    private final ClientApi clientService;

    /**
     * Constructor.
     *
     * @param clientService Service delegate
     */
    @Inject
    public ClientApiResource(final ClientApi clientService) {
        this.clientService = checkNotNull(clientService, "clientService required");
    }

    @GET
    @Path("/getNewsCategories")
    @ApiOperation("Get news categories")
    @Override
    public List<NewsCategoryDto> getNewsCategories(
    ) {
        return clientService.getNewsCategories();
    }

    @GET
    @Path("/getNewsForCategory/{category}")
    @ApiOperation("Retrieves a list of news items for a given category")
    @Override
    public List<NewsListItemDto> getNewsForCategory(@PathParam("category") String category) {
        return clientService.getNewsForCategory(category);
    }

    @GET
    @Path("/getMoreNewsForThis/{newsid}")
    @ApiOperation("Finds more data on this news article")
    @Override
    public NewsItemDto getMoreNewsForThis(@PathParam("newsid")final String newsId) throws IOException {
        return clientService.getMoreNewsForThis(newsId);
    }
}
