package com.diwa.chatClient.Vairable;

import com.diwa.common.dto.MessageDto;
import com.diwa.common.job.Job;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.*;
import java.util.HashMap;

/**
 * Created by di on 3/5/15.
 */
public class Utils {
    //状态常量 0:刚刚打开程序需要登录 1:点击注册跳转 2:已经登陆聊天界面 3:退出
    private static int status = 0;
    private static String serverIpStr = "127.0.0.1";
    private static int serverPort = 9999;

    private static String selfIpStr = "";
    private static int selfPort = 0;
    private static int selfId = -1;
    private static int distFriend = 0;
    private static String selfName = "";
    private static boolean setDistFriendOrNot = false;
    private static HashMap<String, String> History = new HashMap<String, String>();

    //发送entity方法 公用
    public static void sendEntity(MessageDto job) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        //entity --> json
        String entityStr = "";
        try {
            entityStr = objectMapper.writeValueAsString(job);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        System.out.println(entityStr);
        byte[] sendByte = entityStr.getBytes();

        //get ip
        InetAddress serverIp = null;
        try {
            serverIp = InetAddress.getByName(serverIpStr);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        DatagramPacket dm = new DatagramPacket(sendByte, sendByte.length, serverIp, serverPort);
        DatagramSocket ds = new DatagramSocket();
        ds.send(dm);
        ds.close();
    }

    public static int getStatus() {
        return status;
    }

    public static void setStatus(int status) {
        Utils.status = status;
    }

    public static String getServerIp() {
        return serverIpStr;
    }

    public static void setServerIp(String serverIp) {
        Utils.serverIpStr = serverIp;
    }

    public static int getServerPort() {
        return serverPort;
    }

    public static void setServerPort(int serverPort) {
        Utils.serverPort = serverPort;
    }

    public static String getSelfIpStr() {
        return selfIpStr;
    }

    public static void setSelfIpStr(String selfIpStr) {
        Utils.selfIpStr = selfIpStr;
    }

    public static int getSelfPort() {
        return selfPort;
    }

    public static void setSelfPort(int selfPort) {
        Utils.selfPort = selfPort;
    }

    public static int getSelfId() {
        return selfId;
    }

    public static void setSelfId(int selfId) {
        Utils.selfId = selfId;
    }

    public static int getDistFriend() {
        return distFriend;
    }

    public static void setDistFriend(int distFriend) {
        Utils.distFriend = distFriend;
    }

    public static String getSelfName() {
        return selfName;
    }

    public static void setSelfName(String selfName) {
        Utils.selfName = selfName;
    }

    public static HashMap<String, String> getHistory() {
        return History;
    }

    public static void setHistory(HashMap<String, String> history) {
        History = history;
    }

    public static boolean isSetDistFriendOrNot() {
        return setDistFriendOrNot;
    }

    public static void setSetDistFriendOrNot(boolean setDistFriendOrNot) {
        Utils.setDistFriendOrNot = setDistFriendOrNot;
    }
}
