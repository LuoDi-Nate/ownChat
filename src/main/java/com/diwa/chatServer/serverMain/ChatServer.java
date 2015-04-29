package com.diwa.chatServer.serverMain;

import com.diwa.chatServer.view.ServerView;
import com.diwa.common.exceptions.runServerException;
import org.codehaus.jackson.map.ObjectMapper;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Logger;

/**
 * Created by di on 6/4/15.
 */
public class ChatServer {
    public static void main(String[] args) throws Exception {
        //打开jackson
        ObjectMapper reader = new ObjectMapper();

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

        DatagramSocket server = new DatagramSocket(sv.getPort());
        while (sv.getStates() == 1) {
            byte[] buff = new byte[2048];
            DatagramPacket packet = new DatagramPacket(buff, buff.length);
            server.receive(packet);
            String jobStr = packet.toString();

        }
    }
}

