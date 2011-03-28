/*
 * Copyright 2010-2011, CloudBees Inc.
 */

package net.stax.api;

import java.text.ParseException;
import java.util.Date;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("DatabaseInfo")
public class DatabaseInfo {
    private String name;
    private String owner;
    private String username;
    private String password;
    private String created;
    private String status;
    private String master;
    private String[] slaves;
    private int port;
            
    public DatabaseInfo(String name, String owner, String username, String password,
            Date created, String status, String master, String[] slaves, int port) {
        super();
        this.name = name;
        this.owner = owner;
        this.username = username;
        this.password = password;
        this.created = DateHelper.toW3CDateString(created);
        this.status = status;
        this.master = master;
        this.slaves = slaves;
        this.port = port;
    }
    
    public String getName() {
        return name;
    }
    public String getOwner() {
        return owner;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public Date getCreated() {
        if(created == null)
            return null;
        try {
            return DateHelper.parseW3CDate(created);
        } catch (ParseException e) {
            return null;
        }
    }
    public String getStatus() {
        return status;
    }
    public String getMaster() {
        return master;
    }
    public String[] getSlaves()
    {
        return slaves;
    }

    public int getPort() {
        return port;
    }
}
