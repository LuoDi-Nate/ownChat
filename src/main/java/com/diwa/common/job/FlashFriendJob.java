package com.diwa.common.job;

import com.diwa.chatServer.temp.Temp;
import com.diwa.common.dto.Message2Client;
import com.diwa.common.dto.OnlineFriend;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

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
        return new Thread(new Runnable() {
            @Override
            public void run() {
                ObjectMapper objectMapper = new ObjectMapper();
                OnlineFriend friendlist = new OnlineFriend();
                friendlist.setFriendMap(Temp.OnlineUsers);

                String online = "";
                //发送在线成员
                try {
                    online = objectMapper.writeValueAsString(friendlist);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Message2Client message2Client = new Message2Client();
                message2Client.setFromId(-10086);
                message2Client.setFromName(10086+"");
                message2Client.setMsg(online);

                try {
                    Temp.sendMsg2Client(message2Client, selfIp, selfPort);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public String toString() {
        String returnStr = String.format("FlashFriendJob, from %s , ", this.getOperatorId());
        return returnStr;    }
}
