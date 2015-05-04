package com.diwa.chatClient.Vairable;

import com.diwa.common.dto.MessageDto;
import com.diwa.common.job.Job;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.*;

/**
 * Created by di on 3/5/15.
 */
public class Utils {
    //状态常量 0:刚刚打开程序需要登录 1:点击注册跳转 2:已经登陆聊天界面 3:退出
    private static int status = 0;
    private static String serverIpStr = "127.0.0.1";
    private static int serverPort = 9999;

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
}
