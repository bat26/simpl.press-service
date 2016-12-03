package com.simpl.service.news.newsservice.resource;

import com.simpl.service.news.newsservice.api.bootstrap.BootstrapApi;
import com.simpl.service.news.newsservice.api.bootstrap.ConfigResponse;
import com.simpl.service.news.newsservice.api.bootstrap.GetConfigRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Checkout bootstrap API resource.
 *
 * This resource is responsible for configuring routing, resolving request parameters and other properties, then
 * forwarding request handling to a delegate.
 */
@Path(ResourcePath.BOOTSTRAP_API_V1)
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = "bootstrap", produces = MediaType.APPLICATION_JSON)
public class BootstrapApiResource implements BootstrapApi {

    private final BootstrapApi delegate;

    /**
     * Constructor.
     *
     * @param delegate Delegate
     */
    @Inject
    public BootstrapApiResource(final BootstrapApi delegate) {
        this.delegate = checkNotNull(delegate, "delegate required");
    }

    @POST
    @Path("/config")
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get client configuration", consumes = MediaType.APPLICATION_JSON)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid website locale: xx"),
    })
    @Override
    public ConfigResponse getConfig(final GetConfigRequest request) {
        return delegate.getConfig(request);
    }
}
