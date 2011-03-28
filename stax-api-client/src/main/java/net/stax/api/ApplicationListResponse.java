/*
 * Copyright 2010-2011, CloudBees Inc.
 */

package net.stax.api;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ApplicationListResponse")
public class ApplicationListResponse {
    private List<ApplicationInfo> applications;

    public ApplicationListResponse() {
    }
    
    public ApplicationListResponse(List<ApplicationInfo> applications) {
        this.applications = applications;
    }

    public List<ApplicationInfo> getApplications() {
        if(applications == null)
            applications = new ArrayList<ApplicationInfo>();
        return applications;
    }
}