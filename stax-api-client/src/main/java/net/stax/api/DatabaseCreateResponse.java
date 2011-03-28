/*
 * Copyright 2010-2011, CloudBees Inc.
 */

package net.stax.api;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("DatabaseCreateResponse")
public class DatabaseCreateResponse {
    private String databaseId;

    public DatabaseCreateResponse() {
    }

    public DatabaseCreateResponse(String databaseId) {
        this.databaseId = databaseId;
    }

    public String getDatabaseId() {
        return databaseId;
    }
}