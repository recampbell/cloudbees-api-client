/*
 * Copyright 2010-2011, CloudBees Inc.
 */

package net.stax.api;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("DatabaseListResponse")
public class DatabaseListResponse {
    private List<DatabaseInfo> databases;

    public DatabaseListResponse() {
    }

    public List<DatabaseInfo> getDatabases() {
        if(databases == null)
            databases = new ArrayList<DatabaseInfo>();
        return databases;
    }

    public void setInfos(List<DatabaseInfo> infos) {
        this.databases = infos;
    }
}