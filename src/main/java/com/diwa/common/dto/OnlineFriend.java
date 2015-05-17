package com.diwa.common.dto;

import java.util.HashMap;

/**
 * Created by di on 17/5/15.
 */
public class OnlineFriend {

    //<"diwa", "">
    private HashMap<String, Integer> friendMap;

    public HashMap<String, Integer> getFriendMap() {
        return friendMap;
    }

    public void setFriendMap(HashMap<String, Integer> friendMap) {
        this.friendMap = friendMap;
    }
}
