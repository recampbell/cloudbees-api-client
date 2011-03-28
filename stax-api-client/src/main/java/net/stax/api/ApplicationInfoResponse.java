/*
 * Copyright 2010-2011, CloudBees Inc.
 */

package net.stax.api;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ApplicationInfoResponse")
public class ApplicationInfoResponse {
    private ApplicationInfo application;
    
    public ApplicationInfoResponse(ApplicationInfo application) {
        this.application = application;
    }

    public ApplicationInfo getApplicationInfo() {
        return application;
    }
}