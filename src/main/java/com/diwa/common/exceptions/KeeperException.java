package com.diwa.common.exceptions;

/**
 * Created by di on 29/4/15.
 */
public class KeeperException extends Exception{
    private static final long serialVersionUID = 1L;

    public KeeperException(){
        super();
    }
    public KeeperException(String msg){
        super(msg);
    }
    public KeeperException(String msg, Throwable throwable){
        super(msg, throwable);
    }
    public KeeperException(Throwable throwable){
        super(throwable);
    }
}
