/*
 * Copyright 2010-2011, CloudBees Inc.
 */

package net.stax.api;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("DatabaseDeleteResponse")
public class DatabaseDeleteResponse {
    private boolean deleted;

    public DatabaseDeleteResponse() {
    }

    public DatabaseDeleteResponse(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isDeleted() {
        return deleted;
    }
}