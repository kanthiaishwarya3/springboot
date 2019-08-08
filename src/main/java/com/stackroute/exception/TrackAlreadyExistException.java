package com.stackroute.exception;

public class TrackAlreadyExistException extends Exception
{
    private String exceptionmessage;

    public TrackAlreadyExistException()
    {
    }

    public TrackAlreadyExistException(String message)
    {
        super(message);
        this.exceptionmessage=message;
    }

}

