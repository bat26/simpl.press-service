package com.simpl.service.news.newsservice.view;

import io.dropwizard.views.View;

/**
 * Index view.
 */
public class IndexView extends View {

    /**
     * Constructor.
     */
    public IndexView() {
        super("index.mustache");
    }
}
