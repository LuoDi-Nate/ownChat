package com.diwa.chatServer.model;

import com.diwa.common.job.Job;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by di on 18/4/15.
 * 单例的pool 用来堆积消息
 * 供keepter来消费
 */
public class Pool {
    static Integer count;
    static List<Job> jobList;

    public static int getCount() {
        if(count == null){
            count = new Integer(0);
            return count;
        }
        return count;
    }

    public static void setCount(int count) {
        Pool.count = count;
    }

    public static List<Job> getJobList() {
        if (jobList == null) {
            jobList = new ArrayList<Job>();
            return jobList;
        }
        return jobList;
    }

    public static void setJobList(List jobList) {
        Pool.jobList = jobList;
    }
}
