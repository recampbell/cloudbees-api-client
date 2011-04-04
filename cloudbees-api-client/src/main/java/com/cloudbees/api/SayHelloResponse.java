package com.cloudbees.api;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author Fabian Donze
 */
@XStreamAlias("SayHelloResponse")
public class SayHelloResponse
{
    private String message;

    public String getMessage()
    {
        return message;
    }
}