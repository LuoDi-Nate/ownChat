package com.diwa.common.dto;

/**
 * Created by di on 15/5/15.
 * 只用于服务器给client转发别人的聊天
 */
public class Message2Client {
    private int fromId ;
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

    @Override
    public String toString() {
        return "Message2Client{" +
                "fromId=" + fromId +
                ", msg='" + msg + '\'' +
                '}';
    }
}
