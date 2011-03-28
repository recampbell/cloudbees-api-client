/*
 * Copyright 2010-2011, CloudBees Inc.
 */

package net.stax.api;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ApplicationInstallResponse")
public class ApplicationInstallResponse {
    private String id;
    private String url;
    private String postInstallURL;

    public ApplicationInstallResponse() {
    }

    public ApplicationInstallResponse(String id, String url, String postInstallURL) {
        this.id = id;
        this.url = url;
        this.postInstallURL = postInstallURL;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getPostInstallURL() {
        return postInstallURL;
    }
}