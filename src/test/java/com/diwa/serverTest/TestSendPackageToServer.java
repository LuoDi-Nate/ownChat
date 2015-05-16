package com.diwa.serverTest;

import com.diwa.common.dto.MessageDto;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by di on 30/4/15.
 */
public class TestSendPackageToServer {

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        DatagramSocket ds = new DatagramSocket();
        MessageDto md = new MessageDto();
        md.setOperatorNickName("diwa");
        md.setOption(0);
        md.setContext("lalala");
        String sendStr = objectMapper.writeValueAsString(md);
        System.out.println(sendStr);
        byte[] sendByte = sendStr.getBytes();
        System.out.println(sendByte);

        DatagramPacket dp = new DatagramPacket(sendByte, sendByte.length, InetAddress.getByName("127.0.0.1"), 9999);
        ds.send(dp);
        ds.close();
    }

}
