package com.diwa.chatServer.serverMain;

import com.diwa.chatServer.model.Pool;
import com.diwa.chatServer.view.ServerView;
import com.diwa.common.exceptions.KeeperException;
import com.diwa.common.job.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by di on 29/4/15.
 * 消费者 不断消费pool中的任务
 */
public class Keeper extends Thread {
    private final static Logger logger = LoggerFactory.getLogger(Keeper.class);

    @Override
    public void run() {
        //不断从pool中拿任务 消费任务
        while (true) {
            if (Pool.getJobList().size() == 0) {
                try {

                    Thread.sleep(200);
//                    logger.info("no job to do , sleep for a while.");
                    continue;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //消费0位的任务
            Job job = Pool.getJobList().get(0);

            logger.info("get a job "+job);

            Foreman.pushWorker(job);
            Pool.getJobList().remove(0);
        }
    }
}
