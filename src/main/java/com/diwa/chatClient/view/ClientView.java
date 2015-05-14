package com.diwa.chatClient.view;

import com.diwa.chatClient.Vairable.Utils;
import com.diwa.common.dto.MessageDto;
import com.diwa.common.job.MessageJob;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by di on 2/5/15.
 */
public class ClientView extends JFrame{
    private final static Logger logger = LoggerFactory.getLogger(ClientView.class);


    //parameter
    private int port;
    private String nickName;
    private String ip;


    //整体结构
    private Container cc;
    private JPanel topPanel;
    private JPanel bottomPanel;

    //top
    private JLabel versionLabel;
    private JPanel friendPanel;
    private JTextField nowFriend;
    private JTextArea displayText;
    private JButton flashFriendBtn;
    private JPanel friendContext;
    private JTextArea friendList;
    private JButton confirmFriendBtn;

    //bottom
    private JLabel consoleLabel;
    private JPanel downRightPanel1;
    private JPanel downRightPanel2;
    private JLabel profile1;
    private JLabel profile2;
    private JButton exitBtn;
    private JButton exportBtn;
    private JTextField inputText;
    private JPanel bottomPanel2;
    private JButton sendBtn;
    private JButton resetBtn;

    public ClientView(){
        cc = getContentPane();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(400, 500);
        setTitle("ownChatClient        v1.0");
        setLocation(400, 200);
        init();
    }

    public void init(){
        cc.setLayout(new GridLayout(2,1));

        //top
        topPanel = new JPanel();
        versionLabel = new JLabel("Thx for useing this.    ver:1.0");
        friendPanel = new JPanel();
        displayText = new JTextArea();
        displayText.setEditable(false);
        flashFriendBtn = new JButton("FlashFriend");
        friendContext = new JPanel();
        nowFriend = new JTextField("Now:[nobady]");
        confirmFriendBtn = new JButton("Comfirm Friend");
        cc.add(topPanel);
        topPanel.setLayout(new BorderLayout());
        topPanel.add(versionLabel, BorderLayout.NORTH);
//        topPanel.add(displayText, BorderLayout.CENTER);
        topPanel.add(new JScrollPane(displayText), BorderLayout.CENTER);
        displayText.setLineWrap(true);
        topPanel.add(friendPanel, BorderLayout.EAST);
        friendPanel.setLayout(new BorderLayout());
        friendPanel.add(friendContext, BorderLayout.CENTER);
        friendContext.setBackground(Color.white);
        friendList = new JTextArea();
        friendContext.setLayout(new GridLayout(1,1));
        friendContext.add(new JScrollPane(friendList));
        friendList.setEditable(false);
        friendList.setLineWrap(true);

        JPanel tempFriend = new JPanel();
        friendPanel.add(tempFriend, BorderLayout.SOUTH);
        tempFriend.setLayout(new GridLayout(2,1));
        JPanel comfirmFriendPan = new JPanel();
        comfirmFriendPan.setLayout(new GridLayout(2, 1));
        comfirmFriendPan.add(nowFriend);
        comfirmFriendPan.add(confirmFriendBtn);
        tempFriend.add(comfirmFriendPan);
        tempFriend.add(flashFriendBtn);

        //bottom
        bottomPanel = new JPanel();
        consoleLabel = new JLabel("WelCome,diwa say hi there!");
        downRightPanel1 = new JPanel();
        downRightPanel2 = new JPanel();
        profile1 = new JLabel("NickName:["+Utils.getSelfName()+"]");
        profile2 = new JLabel("Port:["+Utils.getSelfPort()+"]");
        exitBtn = new JButton("Exit");
        exportBtn = new JButton("Export");
        inputText = new JTextField();
        bottomPanel2 = new JPanel();
        sendBtn = new JButton("Send");
        resetBtn = new JButton("Reset");
        cc.add(bottomPanel);
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(consoleLabel, BorderLayout.NORTH);
        bottomPanel.add(inputText, BorderLayout.CENTER);
        bottomPanel.add(bottomPanel2, BorderLayout.SOUTH);
        JPanel tempProfilePanel = new JPanel();
        bottomPanel.add(tempProfilePanel, BorderLayout.EAST);
        tempProfilePanel.setLayout(new GridLayout(2, 1));
        tempProfilePanel.add(downRightPanel1);
        tempProfilePanel.add(downRightPanel2);
        downRightPanel1.setLayout(new GridLayout(2,1));
        downRightPanel1.add(profile1);
        downRightPanel1.add(profile2);
        downRightPanel2.setLayout(new GridLayout(2,1));
        downRightPanel2.add(exportBtn);
        downRightPanel2.add(exitBtn);

        bottomPanel2.add(sendBtn);
        bottomPanel2.add(resetBtn);


        actionInit();
    }

    public void actionInit(){
        confirmFriendBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String msg = "";
                int distFriendInt = 0;
                try{
                    distFriendInt = Integer.parseInt(nowFriend.getText());
                }catch (Exception e2){
                    msg = "u try to talk someone, but\n"+nowFriend.getText()+" format error, try again please.\n";
                    flushDisplay(msg);
                }
                //not 0, mean a correct friend
                if(distFriendInt != 0){
                    Utils.setDistFriend(distFriendInt);
                    msg = "u want to talk with "+distFriendInt+" , ^_^";
                    flushDisplay(msg);
                }
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Utils.setStatus(3);
                System.exit(0);
            }
        });

        flashFriendBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        sendBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MessageJob messageJob = new MessageJob();
                messageJob.setFromId(Utils.getSelfId());
                messageJob.setToId(Utils.getDistFriend());
                messageJob.setContext(inputText.getText());
                inputText.setText("");
                messageJob.setCreateTime(new Date());

                String context = "";
                ObjectMapper objectMapper = new ObjectMapper();
                try{
                    context = objectMapper.writeValueAsString(messageJob);
                }catch (Exception e3){
                    logger.error("Json error!", e3);
                }

                MessageDto entity = new MessageDto();
                entity.setOption(2);
                entity.setOperatorId(Utils.getSelfId());
                entity.setContext(context);
                try {
                    Utils.sendEntity(entity);
                } catch (IOException e1) {
                    logger.error("send entity error!", e1);
                }

                //TODO
                发送完消息后的处理 给本地加入history

            }
        });
    }

    public static void main(String[] args) {
        ClientView cv = new ClientView();
        cv.setVisible(true);
    }

    //getter setters
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void flushDisplay(String str){
        String oldStr = displayText.getText();
        String timeStr = new Date().toString();
        displayText.setText(oldStr+"\n"+timeStr+"\n\t"+str);
    }

    public void flushFriend(List<String> list){
        StringBuilder sb = new StringBuilder();
        for(String str : list){
            sb.append(str+"\n");
        }
        friendList.setText(sb.toString());
    }

    public void updateSelf(){
        profile1.setText("hi, "+Utils.getSelfName());
        profile2.setText("Port:["+Utils.getSelfPort()+"]");
    }
}
