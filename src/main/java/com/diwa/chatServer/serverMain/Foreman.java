package com.diwa.chatServer.serverMain;

import com.diwa.common.job.Job;

import java.util.HashMap;

/**
 * Created by di on 30/4/15.
 * 工头
 * keeper每次从pool中拿到一个任务
 * 丢给foreman foreman会自动检测job的具体类型,然后会包装一个worker线程去消费这个job
 *
 */
public class Foreman {
    public static void pushWorker(Job job){
        Thread worker = job.killJob();
        System.out.println("just push a worker");
        worker.start();
    }
}
