package com.diwa.chatServer.model;

import java.util.Date;

/**
 * Created by di on 18/4/15.
 */
public class User {
    private int id;
    private String nickName;
    private String ip;
    private int port;
    private Date createTime;
    private String password;

    public Date getLastOnlineTime() {
        return lastOnlineTime;
    }

    public void setLastOnlineTime(Date lastOnlineTime) {
        this.lastOnlineTime = lastOnlineTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    private Date lastOnlineTime;

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
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", createTime=" + createTime +
                ", lastOnlineTime=" + lastOnlineTime +
                '}';
    }
}
