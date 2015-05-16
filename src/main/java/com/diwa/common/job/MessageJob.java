package com.diwa.common.job;

import java.util.Date;

/**
 * Created by di on 18/4/15.
 */
public class MessageJob extends Job {
    private int fromId;
    private int toId;
    private String toNickName;
    private String context;
    private Date createTime;

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getToNickName() {
        return toNickName;
    }

    public void setToNickName(String toNickName) {
        this.toNickName = toNickName;
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
        String returnStr = String.format("MessageJOB, from %s , ", this.getOperatorId());
        return returnStr;
    }
}
