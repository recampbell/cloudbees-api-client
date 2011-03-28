/*
 * Copyright 2010-2011, CloudBees Inc.
 */

package net.stax.api;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ApplicationRestartResponse")
public class ApplicationRestartResponse {
    private boolean restarted;

    public ApplicationRestartResponse() {
    }

    public ApplicationRestartResponse(boolean restarted) {
        this.restarted = restarted;
    }

    public boolean isRestarted() {
        return restarted;
    }
}