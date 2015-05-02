package com.diwa.chatClient.view;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by di on 2/5/15.
 */
public class ClientView extends JFrame{
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
    private JLabel nowFriend;
    private JTextField displayText;
    private JButton flashFriend;
    private JPanel friendContext;
    private List<JButton> friends;
    private JButton[] friend;

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
        nowFriend = new JLabel("Let's Chat!");
        displayText = new JTextField();
        flashFriend = new JButton("FlashFriend");
        friend = new JButton[100];
        friendContext = new JPanel();
        nowFriend = new JLabel("Now:[nobady]");
        cc.add(topPanel);
        topPanel.setLayout(new BorderLayout());
        topPanel.add(versionLabel, BorderLayout.NORTH);
        topPanel.add(displayText, BorderLayout.CENTER);
        topPanel.add(friendPanel, BorderLayout.EAST);
        friendPanel.setLayout(new BorderLayout());
        friendPanel.add(friendContext, BorderLayout.CENTER);
        friendContext.setBackground(Color.white);
        JPanel tempFriend = new JPanel();
        friendPanel.add(tempFriend, BorderLayout.SOUTH);
        tempFriend.setLayout(new GridLayout(2,1));
        tempFriend.add(nowFriend);
        tempFriend.add(flashFriend);

        //bottom
        bottomPanel = new JPanel();
        consoleLabel = new JLabel("WelCome,diwa say hi there!");
        downRightPanel1 = new JPanel();
        downRightPanel2 = new JPanel();
        profile1 = new JLabel("NickName:["+this.nickName+"]");
        profile2 = new JLabel("Port:["+this.port+"]");
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
}
