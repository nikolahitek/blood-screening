package com.sorsix.bloodscreening.handler.exception;

public class DataBaseTransactionException extends RuntimeException{
    public DataBaseTransactionException(String message){
        super(message);
    }
}
