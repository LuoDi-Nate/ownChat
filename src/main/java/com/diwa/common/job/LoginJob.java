package com.diwa.common.job;

import java.util.Date;

/**
 * Created by di on 29/4/15.
 */
public class LoginJob extends Job {
    private String nickName;
    private String ip;
    private int port;
    private int id;
    private Date time;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public Thread killJob() {
        return new Thread(new Runnable() {
            public void run() {

            }
        });
    }

    @Override
    public String toString() {
        String returnStr = String.format("LoginJOB, from %s , ", this.getOperatorId());
        return returnStr;
    }
}
