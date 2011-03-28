/*
 * Copyright 2010-2011, CloudBees Inc.
 */

package net.stax.api;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

@XStreamAlias("ApplicationCheckSumsResponse")
public class ApplicationCheckSumsResponse {
    Map<String, Long> checkSums;

    public ApplicationCheckSumsResponse() {
    }

    public ApplicationCheckSumsResponse(Map<String, Long> checkSums) {
        this.checkSums = checkSums;
    }

    public Map<String, Long> getCheckSums() {
        return checkSums;
    }

    public void setCheckSums(Map<String, Long> checkSums) {
        this.checkSums = checkSums;
    }
}
