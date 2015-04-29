package com.diwa.common.dto;

import java.util.Date;

/**
 * Created by di on 18/4/15.
 */
public class MessageDto {
    //0:注册 1:登陆 2:发送消息 3:下线
    private int option;

    private Object context;
    private int fromId;
    private int toId;
}
