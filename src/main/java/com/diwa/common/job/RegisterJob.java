package com.diwa.common.job;

/**
 * Created by di on 29/4/15.
 */
public class RegisterJob extends Job{
    private String ip;
    private int port;
    private String nickName;
    private String password;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        String returnStr = String.format("RegisterJOB, from %s , ", this.getOperatorId());
        return returnStr;
    }
}
