package com.diwa.chatServer.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by di on 28/4/15.
 */
public class ServerView extends JFrame {
    //param
    //server目前状态
    private String status = "ON";
    private Color Color = java.awt.Color.red;
    private String consoleStr = "CONSOLE:\n";
    private String eventStr = "EVENT:\n";
    private int port = 9999;


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
    private JButton stopBtn;
    private JButton exportBtn;
    private JButton exitBtn;

    public ServerView(int port) {
        this.port = port;
        cc = getContentPane();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(600, 360);
        setTitle("ownChatServer    _v1.0");
        setLocation(400, 200);
        init();
    }

    //构造界面
    private void init() {
        //north
        northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(1, 3));
        topLabel = new JLabel("ownChatServer_         ");
        topLabel.setFont(new Font("楷体", Font.BOLD, 25));
        topVersionLabel = new JLabel("             author:diwa");
        northPanel.setLayout(new FlowLayout());
        northPanel.add(topLabel);
        northPanel.add(new JLabel("DefaultPort:[" + port + "]"));
        northPanel.add(topVersionLabel);

        //mid
        midPanel = new JPanel();
        consoleText = new JTextArea(consoleStr);
        eventText = new JTextArea(eventStr);
        midPanel.setLayout(new GridLayout(1, 2));
        midPanel.add(new JScrollPane(consoleText));
        consoleText.setWrapStyleWord(true);
        consoleText.setEnabled(false);
        midPanel.add(new JScrollPane(eventText));
        eventText.setEnabled(false);
        eventText.setWrapStyleWord(true);

        //sourth
        sourthPanel = new JPanel();
        startBtn = new JButton("START");
        statusBtn = new JButton(status);
        statusBtn.setFont(new Font("楷体", Font.BOLD, 25));
        statusBtn.setBackground(Color);
        stopBtn = new JButton("STOP");
        exportBtn = new JButton("export");
        exitBtn = new JButton("exit");
        sourthPanel.add(startBtn);
        sourthPanel.add(statusBtn);
        sourthPanel.add(stopBtn);
        sourthPanel.add(exportBtn);
        sourthPanel.add(exitBtn);

        //all
        cc.setLayout(new BorderLayout());
        cc.add(northPanel, BorderLayout.NORTH);
        cc.add(midPanel, BorderLayout.CENTER);
        cc.add(sourthPanel, BorderLayout.SOUTH);

        //按键监听
        actionInit();
    }

    //添加监听
    private void actionInit() {
        //退出键
        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });

        //start键添加动作
        startBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        ServerView sv = new ServerView(9999);
        sv.setVisible(true);
    }

    //三个同名方法,重载,刷新界面用
    public void flashServerView(int status, String newConsoleStr, String newEventStr) {
        switch (status) {
            case 0:
                this.status = "ON";
                this.Color = Color.green;
                break;
            case 1:
                this.status = "OFF";
                break;
        }
        this.statusBtn.setText(this.status);
        this.statusBtn.setBackground(this.Color);

        String oldConsole = consoleText.getText();
        this.consoleText.setText(oldConsole + "\n" + newConsoleStr);

        String oldEventStr = eventText.getText();
        this.eventText.setText(oldEventStr + "\n" + newEventStr);

    }

    public void flashServerView(String newConsoleStr, String newEventStr) {
        String oldConsole = consoleText.getText();
        this.consoleText.setText(oldConsole + "\n" + newConsoleStr);

        String oldEventStr = eventText.getText();
        this.eventText.setText(oldEventStr + "\n" + newEventStr);
    }

    public void flashServerView(String newConsoleStr) {
        String oldConsole = consoleText.getText();
        this.consoleText.setText(oldConsole + "\n" + newConsoleStr);
    }


}
