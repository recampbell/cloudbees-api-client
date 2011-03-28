/*
 * Copyright 2010-2011, CloudBees Inc.
 */

package net.stax.api;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("AccountInfo")
public class AccountInfo {
    private String name;

    public AccountInfo(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
