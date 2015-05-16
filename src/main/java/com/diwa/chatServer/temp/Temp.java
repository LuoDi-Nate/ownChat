package com.diwa.chatServer.temp;

import com.diwa.common.dto.Message2Client;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.*;
import java.util.HashMap;

/**
 * Created by di on 17/5/15.
 */
public class Temp {
    //目前做一个Hashmap 存放在线用户
    //TODO 放入redis

    public static HashMap<String, Integer> OnlineUsers = new HashMap<>();

    public static void sendMsg2Client(Message2Client msg, String clientIp, int clientPort) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        //Message2Client --> json
        String sendStr = "";
        try {
            sendStr = objectMapper.writeValueAsString(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(msg);

        byte[] sendByte = sendStr.getBytes();

        //get ip
        InetAddress ip = null;
        try {
            ip = InetAddress.getByName(clientIp);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        DatagramPacket dm = new DatagramPacket(sendByte, sendByte.length, ip, clientPort);
        DatagramSocket ds = new DatagramSocket();
        ds.send(dm);
        ds.close();

    }
}
