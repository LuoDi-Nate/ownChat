package com.diwa.common.dto;

/**
 * Created by di on 15/5/15.
 * 只用于服务器给client转发别人的聊天
 */
public class Message2Client {

    //如果是系统服务 fromId 会是 -10086
    private int fromId;
    //如果是系统 fromName 会是 10086
    private String fromName;
    private String msg;

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    @Override
    public String toString() {
        return "Message2Client{" +
                "fromId=" + fromId +
                ", fromName='" + fromName + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
