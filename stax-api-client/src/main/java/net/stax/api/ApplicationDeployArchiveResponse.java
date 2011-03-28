/*
 * Copyright 2010-2011, CloudBees Inc.
 */

package net.stax.api;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ApplicationDeployArchiveResponse")
public class ApplicationDeployArchiveResponse {
    private String id;
    private String url;

    public ApplicationDeployArchiveResponse() {
    }

    public ApplicationDeployArchiveResponse(String id, String url) {
        this.id = id;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}