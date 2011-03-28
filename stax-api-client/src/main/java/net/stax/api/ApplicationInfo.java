/*
 * Copyright 2010-2011, CloudBees Inc.
 */

package net.stax.api;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("ApplicationInfo")
public class ApplicationInfo {
    private String id;
    private String title;
    private String created;
    private String status;
    
    @XStreamImplicit(itemFieldName="url")
    private List<String> urls;
    
    public ApplicationInfo(String id, String title, Date created,
            String status, String[] urls) {
        super();
        this.id = id;
        this.title = title;
        this.created = DateHelper.toW3CDateString(created);
        this.status = status;
        this.urls = new ArrayList<String>();
        for(String url : urls)
            this.urls.add(url);
    }
    
    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
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
    public String[] getUrls() {
        if(urls == null)
            urls = new ArrayList<String>();
        
        return urls.toArray(new String[0]); 
    }
}
