package com.diwa.chatServer.service;

import com.diwa.common.job.*;
import com.diwa.common.dto.MessageDto;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by di on 29/4/15.
 */
public class EntityHandler {
    public Job dealWithEntity(MessageDto messageDto) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        int operation = messageDto.getOption();
        Job resultJob = null;
        switch (operation){
            //注册
            case 0: resultJob = objectMapper.readValue(messageDto.getContext(), new RegisterJob().getClass());
                break;
            //登陆
            case 1: resultJob = objectMapper.readValue(messageDto.getContext(), new LoginJob().getClass());
                break;
            //发送消息
            case 2: resultJob = objectMapper.readValue(messageDto.getContext(), new MessageJob().getClass());
                break;
            //下线
            case 3: resultJob = objectMapper.readValue(messageDto.getContext(), new LogoutJob().getClass());
                break;
        }
        return resultJob;
    }
}
