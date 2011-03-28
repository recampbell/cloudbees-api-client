/*
 * Copyright 2010-2011, CloudBees Inc.
 */

package net.stax.api;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ApplicationCreateDownloadUrlResponse")
public class ApplicationGetSourceUrlResponse {
    private String url;

    public ApplicationGetSourceUrlResponse() {
    }

    public ApplicationGetSourceUrlResponse(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}