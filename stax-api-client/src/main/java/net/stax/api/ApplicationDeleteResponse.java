/*
 * Copyright 2010-2011, CloudBees Inc.
 */

package net.stax.api;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ApplicationDeleteResponse")
public class ApplicationDeleteResponse {
    private boolean deleted;

    public ApplicationDeleteResponse() {
    }

    public ApplicationDeleteResponse(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isDeleted() {
        return deleted;
    }
}