/*
 * Copyright 2010-2011, CloudBees Inc.
 */

package net.stax.api;

public class StaxClientException extends Exception{
    private ErrorResponse error;

    public StaxClientException(ErrorResponse error) {
        super(error.getErrorCode() + " - " + error.getMessage());
        this.error = error;
    }

    public ErrorResponse getError() {
        return error;
    }
}
