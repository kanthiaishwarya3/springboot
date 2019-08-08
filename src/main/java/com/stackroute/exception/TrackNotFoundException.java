package com.stackroute.exception;

public class TrackNotFoundException extends Exception
{
    private String exceptionmessage;

    public TrackNotFoundException()
    {
    }

    public TrackNotFoundException(String message)
    {
        super(message);
        this.exceptionmessage=message;
    }

}

