package com.diwa.common.job;

import com.diwa.chatServer.temp.Temp;
import com.diwa.common.dto.Message2Client;

import java.io.IOException;
import java.util.Date;

/**
 * Created by di on 18/4/15.
 */
public class MessageJob extends Job {
    private String fromName;
    private int fromId;
    private int toId;
    private String toNickName;
    private String context;
    private Date createTime;

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

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
                //拿到目标的ip和port
                //<"diwa", "127.0.0.1#50000">
                String ipPort = Temp.UserAddress.get(toNickName);
                String toIp = ipPort.split("#")[0];
                String portStr = ipPort.split("#")[1];
                int toPort = Integer.parseInt(portStr);

                Message2Client entity = new Message2Client();
                entity.setMsg(context);
                entity.setFromId(fromId);
                entity.setFromName(fromName);

                try {
                    Temp.sendMsg2Client(entity, toIp, toPort);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public String toString() {
        String returnStr = String.format("MessageJOB, from %s , ", this.getOperatorId());
        return returnStr;
    }
}
