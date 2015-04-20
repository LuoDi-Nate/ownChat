package com.diwa.chatServer.service;

import com.diwa.chatServer.model.User;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import java.io.IOException;
import java.util.Date;

/**
 * Created by di on 20/4/15.
 */
public class testJackson {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        User testUser = new User();
        testUser.setNickName("test");
        testUser.setId(5);
        testUser.setPort(222);
        testUser.setIp("127.0.0.1");
        testUser.setCreateTime(new Date());

        System.out.println(testUser);
        System.out.println("=======================");

        String testUserString = null;

        objectMapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
        try {
            testUserString = objectMapper.writeValueAsString(testUser);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(testUserString);
        System.out.println("=======================");

        User jsonUser = null;
        try {
            jsonUser = objectMapper.readValue(testUserString, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(jsonUser);
    }
}
