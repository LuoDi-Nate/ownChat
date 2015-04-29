package com.diwa.common.exceptions;

/**
 * Created by di on 29/4/15.
 */
public class runServerException extends Exception {
    private static final long serialVersionUID = 1L;

    public runServerException(){
        super();
    }
    public runServerException(String msg){
        super(msg);
    }
    public runServerException(String msg, Throwable throwable){
        super(msg, throwable);
    }
    public runServerException(Throwable throwable){
        super(throwable);
    }
}
