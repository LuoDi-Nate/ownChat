package com.diwa.chatServer.serverMain;

import com.diwa.chatServer.model.Pool;
import com.diwa.chatServer.view.ServerView;
import com.diwa.common.dto.MessageDto;
import com.diwa.common.exceptions.runServerException;
import org.codehaus.jackson.map.ObjectMapper;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by di on 6/4/15.
 */
public class ChatServer {
    public static void main(String[] args) throws Exception {
        //打开jackson
        ObjectMapper entityReader = new ObjectMapper();

        ServerView sv = new ServerView(9999);
        sv.setVisible(true);
        //wating for start
        while (true) {
            try {
                if (sv.getStates() == 1) break;
                Thread.sleep(500);
            } catch (Exception e) {
                throw new runServerException("trying sleep server error!");
            }
        }

        //先开启keeper
        Keeper keeper = new Keeper();
        keeper.start();

        //开启服务器
        DatagramSocket server = new DatagramSocket(sv.getPort());
        while (sv.getStates() == 1) {
            byte[] buff = new byte[2048];
            DatagramPacket packet = new DatagramPacket(buff, buff.length);
            server.receive(packet);
            String jobStr = packet.toString();
            //反序列化MessageDto
            MessageDto entity = entityReader.readValue(jobStr, MessageDto.class);
            //生产好,等待消费
            Pool.getJobList().add(entity);
        }
    }
}

