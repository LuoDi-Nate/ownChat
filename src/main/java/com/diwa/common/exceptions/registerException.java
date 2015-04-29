package com.diwa.common.exceptions;

/**
 * Created by di on 20/4/15.
 */
public class registerException extends Exception{
    private static final long serialVersionUID = 1L;

    public registerException(){
        super();
    }
    public registerException(String msg){
        super(msg);
    }
    public registerException(String msg, Throwable throwable){
        super(msg, throwable);
    }
    public registerException(Throwable throwable){
        super(throwable);
    }

}
