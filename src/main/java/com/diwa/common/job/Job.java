package com.diwa.common.job;

/**
 * Created by di on 29/4/15.
 * 放在pool中的任务
 * 是一个父类
 */
public class Job {
    protected int operatorId;

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    @Override
    public String toString(){
        String returnStr = String.format("JOB, from %s , ");
        return returnStr;
    }
}
