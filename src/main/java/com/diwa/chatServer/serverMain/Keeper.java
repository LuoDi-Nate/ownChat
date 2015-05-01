package com.diwa.chatServer.serverMain;

import com.diwa.chatServer.model.Pool;
import com.diwa.chatServer.view.ServerView;
import com.diwa.common.exceptions.KeeperException;
import com.diwa.common.job.Job;

/**
        * Created by di on 29/4/15.
        * 消费者 不断消费pool中的任务
        */
public class Keeper extends Thread{
    @Override
    public void run() {
        //不断从pool中拿任务 消费任务
        while (true){
            if(Pool.getJobList().size() == 0){
                try {
                    Thread.sleep(200);
                    continue;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //消费0位的任务
            Job job = Pool.getJobList().get(0);
            Foreman.pushWorker(job);
            Pool.getJobList().remove(0);
        }
    }
}
