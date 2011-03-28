/*
 * Copyright 2010-2011, CloudBees Inc.
 */

package net.stax.api;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ApplicationStatusResponse")
public class ApplicationStatusResponse {
    private String status;

    public ApplicationStatusResponse() {
    }

    public ApplicationStatusResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}