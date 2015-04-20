package com.diwa.common.exceptions;

/**
 * Created by di on 20/4/15.
 */
public class DbException extends Exception {
    DbException(){
        super();
    }

    DbException(String msg){
        super(msg);
    }

    DbException(String msg, Throwable throwable){
        super(msg, throwable);
    }

    DbException(Throwable throwable){
        super(throwable);
    }
}
