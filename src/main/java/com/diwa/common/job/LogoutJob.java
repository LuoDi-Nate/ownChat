package com.diwa.common.job;

import java.util.Date;

/**
 * Created by di on 29/4/15.
 */
public class LogoutJob extends Job {
    private int id;
    private Date time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
