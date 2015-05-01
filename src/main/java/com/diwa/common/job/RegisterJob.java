package com.diwa.common.job;

import com.diwa.chatServer.dao.UserDao;
import com.diwa.chatServer.model.User;

import java.util.Date;

/**
 * Created by di on 29/4/15.
 */
public class RegisterJob extends Job{
    protected String ip;
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
    public Thread killJob() {
        return new Thread(new Runnable() {
            public void run() {
                User user  = new User();
                user.setNickName(nickName);
                user.setIp(ip);
                user.setCreateTime(new Date());
                user.setPort(port);
                user.setLastOnlineTime(new Date());
                UserDao userDao = new UserDao();
                userDao.createUser(user);
            }
        });
    }

    @Override
    public String toString() {
        String returnStr = String.format("RegisterJOB, from %s , ", this.getOperatorId());
        return returnStr;
    }
}
