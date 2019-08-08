package com.stackroute.exceptions;

public class TrackAlreadyExistsException extends Exception
{
    // Declaration
    private String exceptionmessage;

    public TrackAlreadyExistsException()
    {
    }

    public TrackAlreadyExistsException(String message)
    {
        super(message);
        this.exceptionmessage=message;
    }

}