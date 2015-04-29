package com.diwa.common.dto;

import java.util.Date;

/**
 * Created by di on 18/4/15.
 */
public class MessageDto {
    //0:注册 1:登陆 2:发送消息 3:下线
    private int option;

    //一个json字符串 可以反序列化成任何实体
    private String context;

    //发送来命令的用户id
    private int operatorId;
}
