package com.diwa.chatClient.Vairable;

/**
 * Created by di on 3/5/15.
 */
public class Status {
    //状态常量 0:刚刚打开程序需要登录 1:点击注册跳转 2:已经登陆聊天界面 3:退出
    private static int status=0;
    private static String serverIp = "127.0.0.1";
    private static int serverPort = 9999;

    public static int getStatus() {
        return status;
    }

    public static void setStatus(int status) {
        Status.status = status;
    }

    public static String getServerIp() {
        return serverIp;
    }

    public static void setServerIp(String serverIp) {
        Status.serverIp = serverIp;
    }

    public static int getServerPort() {
        return serverPort;
    }

    public static void setServerPort(int serverPort) {
        Status.serverPort = serverPort;
    }
}
