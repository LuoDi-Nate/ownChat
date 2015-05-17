package com.diwa.chatServer.temp;

import com.diwa.common.dto.Message2Client;
import com.diwa.common.dto.OnlineFriend;
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
    //<"diwa", "13434">
    public static HashMap<String, Integer> OnlineUsers = new HashMap<>();

    //用来存放用户的ip和port
    //<"diwa", "127.0.0.1#50000">
    //TODO 持久化到DB
    public static HashMap<String, String> UserAddress = new HashMap<String, String>();

    //通过id拿到已经登录用户的用户名
    public static String getNickNameById (int id){
        for(String str:OnlineUsers.keySet()){
            if(id == OnlineUsers.get(str)){
                return str;
            }
        }
        return null;
    }

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
