package com.diwa.chatServer.model;

import java.util.List;

/**
 * Created by di on 18/4/15.
 *  维护一个池子,保持在线人员在这中间,每隔5分钟,踢出没有响应的旧用户
 *  如果用户主动logout 也踢出池子
 *  有可能在建立一个隐私池.或者在池子中有状态
 *  一个server开启,只能有一个池子.
 */
public class Pool {
    private static int count;
    private static List<User> onlineList;

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Pool.count = count;
    }

    public static List<User> getOnlineList() {
        return onlineList;
    }

    public static void setOnlineList(List<User> onlineList) {
        Pool.onlineList = onlineList;
    }
}
