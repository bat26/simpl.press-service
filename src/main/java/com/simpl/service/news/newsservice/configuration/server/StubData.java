package com.simpl.service.news.newsservice.configuration.server;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Stub service configuration
 */
public class StubData {

    @NotBlank
    private String paymentUrl;

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(final String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }
}
