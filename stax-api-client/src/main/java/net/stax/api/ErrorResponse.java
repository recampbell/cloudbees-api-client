/*
 * Copyright 2010-2011, CloudBees Inc.
 */

package net.stax.api;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("error")
public class ErrorResponse {
    public String errorCode;
    public String message;
    public String getErrorCode() {
        return errorCode;
    }
    public String getMessage() {
        return message;
    }
}
