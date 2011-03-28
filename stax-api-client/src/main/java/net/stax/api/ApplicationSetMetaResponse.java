/*
 * Copyright 2010-2011, CloudBees Inc.
 */

package net.stax.api;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ApplicationSetMetaResponse")
public class ApplicationSetMetaResponse {
    private boolean success;

    public ApplicationSetMetaResponse() {
    }

    public ApplicationSetMetaResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}