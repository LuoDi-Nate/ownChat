package com.diwa.common.job;

import java.util.Date;

/**
 * Created by di on 29/4/15.
 */
public class LoginJob extends Job {
    private int id;
    private Date time;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        String returnStr = String.format("LoginJOB, from %s , ", this.getOperatorId());
        return returnStr;
    }
}
