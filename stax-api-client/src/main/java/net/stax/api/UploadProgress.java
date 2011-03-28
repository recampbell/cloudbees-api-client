/*
 * Copyright 2010-2011, CloudBees Inc.
 */

package net.stax.api;

public interface UploadProgress
{
    /**
     * 
     * @param deltaCount the number of new bytes sent since last notification.
     * @param totalWritten the total number of bytes sent so far
     * @param totalToSend the total bytes being sent
     * @return
     */
    public void handleBytesWritten(long deltaCount, long totalWritten, long totalToSend);
}