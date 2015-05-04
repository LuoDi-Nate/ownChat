package com.diwa.chatServer.serverMain;

import com.diwa.chatServer.model.Pool;
import com.diwa.chatServer.service.EntityHandler;
import com.diwa.chatServer.view.ServerView;
import com.diwa.common.dto.MessageDto;
import com.diwa.common.exceptions.runServerException;
import com.diwa.common.job.Job;
import org.codehaus.jackson.map.ObjectMapper;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by di on 6/4/15.
 */
public class ChatServer {
    public static void main(String[] args) {
        //打开jackson
        ObjectMapper entityReader = new ObjectMapper();
        //准备好entityHandler
        EntityHandler entityHandler = new EntityHandler();

        ServerView sv = new ServerView(9999);
        sv.setVisible(true);
        //wating for start
        while (true) {
            try {
                if (sv.getStates() == 1) break;
                Thread.sleep(500);
            } catch (Exception e) {

            }
        }

        //先开启keeper
        Keeper keeper = new Keeper();
        keeper.start();

        try {
            //开启服务器
            DatagramSocket server = new DatagramSocket(sv.getPort());
            sv.flashServerView("Server run...\nlisten on " + sv.getPort(), "wating for entity...");
            while (sv.getStates() == 1) {
                byte[] buff = new byte[2048];
                DatagramPacket packet = new DatagramPacket(buff, buff.length);
                server.receive(packet);
                String jobStr = new String(packet.getData(), 0, packet.getLength());
                //反序列化MessageDto
                System.out.println(jobStr);
                MessageDto entity = entityReader.readValue(jobStr, MessageDto.class);
                sv.flashServerView("got an entity!      come from :" + entity.getContext());
                //生产好,等待消费
                Job job = entityHandler.dealWithEntity(entity);
                sv.flashEventOnly("get a job!      " + job.toString());

                Pool.getJobList().add(job);
            }
        }catch (Exception e){
            sv.flashServerView(e.toString());
            e.printStackTrace();
        }
    }
}

