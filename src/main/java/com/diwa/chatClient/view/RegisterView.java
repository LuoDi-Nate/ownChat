package com.diwa.chatClient.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by di on 2/5/15.
 */
public class RegisterView extends JFrame {
    //整体构架
    private Container cc;
    private JPanel topPanel;
    private JPanel midPanel;
    private JPanel bottomPanel;

    //top
    private JLabel versionLabel;

    //mid input部分
    private JLabel nickNameLabel;
    private JTextField nickName;
    private JLabel passwdLabel;
    private JPasswordField passwd;
    private JLabel portLabel;
    private JTextField port;

    //bottom部分
    private JButton registerBtn;
    private JButton resetBtn;
    private JLabel messageLabel;

    public RegisterView() {
        cc = getContentPane();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(300, 400);
        setTitle("ownChatLogin       v1.0");
        setLocation(400, 200);
        init();
    }

    //构造界面
    public void init() {
        cc.setLayout(new GridLayout(3, 1));

        //top
        topPanel = new JPanel();
        versionLabel = new JLabel("Sign In");
        versionLabel.setFont(new Font("楷体", Font.BOLD, 60));
        cc.add(topPanel);
        topPanel.add(versionLabel);

        //mid
        midPanel = new JPanel();
        nickNameLabel = new JLabel("nickName here _");
        nickName = new JTextField("di.luo");
        passwdLabel = new JLabel("passWord here _");
        passwd = new JPasswordField("root");
        portLabel = new JLabel("port here _ , range(>9999)");
        port = new JTextField("20001");
        cc.add(midPanel);
        midPanel.setLayout(new GridLayout(6, 1));
        midPanel.add(nickNameLabel, BorderLayout.CENTER);
        midPanel.add(nickName, BorderLayout.CENTER);
        midPanel.add(passwdLabel, BorderLayout.CENTER);
        midPanel.add(passwd, BorderLayout.CENTER);
        midPanel.add(portLabel, BorderLayout.CENTER);
        midPanel.add(port, BorderLayout.CENTER);

        //bottom
        bottomPanel = new JPanel();
        cc.add(bottomPanel);
        registerBtn = new JButton("sign in");
        resetBtn = new JButton("resetAll");

        Date dateNow = new Date();
        messageLabel = new JLabel("\n\n\n\n" + dateNow.toString() + "\nv1.0");
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(new JLabel("\n\n\n\n\n\n"), BorderLayout.NORTH);
        bottomPanel.add(registerBtn, BorderLayout.CENTER);
        bottomPanel.add(resetBtn, BorderLayout.EAST);
        bottomPanel.add(messageLabel, BorderLayout.SOUTH);


        actionInit();
    }

    public void actionInit() {
        resetBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nickName.setText("");
                passwd.setText("");
                port.setText("");
            }
        });
    }

    public static void main(String[] args) {
        RegisterView rv = new RegisterView();
        rv.setVisible(true);
    }
}
