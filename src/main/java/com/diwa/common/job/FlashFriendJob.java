package com.diwa.common.job;

/**
 * Created by di on 16/5/15.
 */
public class FlashFriendJob extends Job {
    private String selfNickName;

    private String selfIp;
    private int selfPort;

    public String getSelfNickName() {
        return selfNickName;
    }

    public void setSelfNickName(String selfNickName) {
        this.selfNickName = selfNickName;
    }

    public String getSelfIp() {
        return selfIp;
    }

    public void setSelfIp(String selfIp) {
        this.selfIp = selfIp;
    }

    public int getSelfPort() {
        return selfPort;
    }

    public void setSelfPort(int selfPort) {
        this.selfPort = selfPort;
    }

    @Override
    public Thread killJob() {
        return null;
    }

    @Override
    public String toString() {
        String returnStr = String.format("FlashFriendJob, from %s , ", this.getOperatorId());
        return returnStr;    }
}
