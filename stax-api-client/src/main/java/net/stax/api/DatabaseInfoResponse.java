/*
 * Copyright 2010-2011, CloudBees Inc.
 */

package net.stax.api;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("DatabaseInfoResponse")
public class DatabaseInfoResponse {
    private DatabaseInfo database;

    public DatabaseInfoResponse(DatabaseInfo database) {
        this.database = database;
    }

    public DatabaseInfo getDatabaseInfo() {
        return database;
    }
}