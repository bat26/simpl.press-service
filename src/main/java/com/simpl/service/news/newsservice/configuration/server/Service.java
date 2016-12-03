package com.simpl.service.news.newsservice.configuration.server;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Service {

    @NotNull
    private boolean stubbed;

    @Valid
    private StubData stubData;

    /**
     * @return Whether a stubbed service implementation should be used by the running server
     */
    public boolean isStubbed() {
        return stubbed;
    }

    public void setStubbed(final boolean stubbed) {
        this.stubbed = stubbed;
    }

    /**
     * @return Stub configuration
     */
    public StubData getStubData() {
        return stubData;
    }

    public void setStubData(final StubData stubData) {
        this.stubData = stubData;
    }
}
