package com.diwa.chatServer.model;

import java.util.List;

/**
 * Created by di on 18/4/15.
 */
public class Pool {
    private int count;
    private List<User> onlineList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<User> getOnlineList() {
        return onlineList;
    }

    public void setOnlineList(List<User> onlineList) {
        this.onlineList = onlineList;
    }
}
