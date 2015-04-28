package com.diwa.chatServer.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by di on 28/4/15.
 */
public class ServerView extends JFrame {
    //param
    //server目前状态 0 on 1 stop
    private int status = 1;
    

    //整体构架
    private Container cc;
    private JPanel northPanel;
    private JPanel midPanel;
    private JPanel sourthPanel;


    //northPanel的构架
    private JLabel topLabel;
    private JLabel topVersionLabel;

    //midPanel
    private JTextArea consoleText;
    private JTextArea eventText;

    //sourthPanel
    private JButton startBtn;
    private JButton statusBtn;
    private JButton exportBtn;
    private JButton exitBtn;

    public ServerView(){
        cc = getContentPane();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(600, 360);
        setTitle("ownChatServer    v1.0      diwaMade");
        setLocation(400,200);
        init();
    }

    //构造界面
    private void init(){
        //north
        northPanel = new JPanel();
        topLabel = new JLabel();
        topVersionLabel = new JLabel();
        northPanel.setLayout(new FlowLayout());
        northPanel.add(topLabel);
        northPanel.add(topVersionLabel);

        //mid
        midPanel = new JPanel();
        consoleText = new JTextArea();
        eventText = new JTextArea();
        midPanel.add(consoleText);
        midPanel.add(eventText);

        //sourth
        startBtn = new JButton("START");
        statusBtn = new JButton("")

    }


}
