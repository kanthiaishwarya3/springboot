package com.stackroute.exceptions;

public class TrackNotFoundException extends Exception
{
    // Declaration
    private String exceptionmessage;

    public TrackNotFoundException(){}

    public TrackNotFoundException(String message)
    {
        super(message);
        this.exceptionmessage=message;
    }
}

