package com.diwa.common.dto;

import java.util.Date;

/**
 * Created by di on 18/4/15.
 */
public class MessageDto {
    //0:注册 1:登陆 2:发送消息 3:下线 4:请求在线好友
    private int option;

    //一个json字符串 可以反序列化成任何实体
    private String context;

    //发送来命令的用户name
    private String operatorNickName;

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getOperatorNickName() {
        return operatorNickName;
    }

    public void setOperatorNickName(String operatorNickName) {
        this.operatorNickName = operatorNickName;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "option=" + option +
                ", context='" + context + '\'' +
                ", operatorNickName='" + operatorNickName + '\'' +
                '}';
    }
}
