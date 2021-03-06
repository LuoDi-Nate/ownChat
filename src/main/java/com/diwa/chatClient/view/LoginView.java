package com.diwa.chatClient.view;

import com.diwa.chatClient.Vairable.Utils;
import com.diwa.common.dto.MessageDto;
import com.diwa.common.job.LoginJob;
import com.diwa.common.job.RegisterJob;
import javafx.animation.Animation;
import org.codehaus.jackson.map.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * Created by di on 1/5/15.
 */
public class LoginView extends JFrame {
    //param 0刚刚打开 1点击了register 2登陆成功
    private int loginOrNot;

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
    private JButton loginBtn;
    private JButton registerBtn;
    private JButton resetBtn;
    private JLabel messageLabel;

    public LoginView() {
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
        versionLabel = new JLabel("Login");
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
        loginBtn = new JButton("login");
        resetBtn = new JButton("resetAll");
        registerBtn = new JButton("no count? sign in");

        Date dateNow = new Date();
        messageLabel = new JLabel("\n\n\n\n" + dateNow.toString() + "\nv1.0");
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(new JLabel("\n\n\n\n\n\n"), BorderLayout.NORTH);
        bottomPanel.add(loginBtn, BorderLayout.WEST);
        bottomPanel.add(registerBtn, BorderLayout.CENTER);
        bottomPanel.add(resetBtn, BorderLayout.EAST);
        bottomPanel.add(messageLabel, BorderLayout.SOUTH);


        actionInit();
    }

    public void actionInit() {

        //reset按钮事件
        resetBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nickName.setText("");
                passwd.setText("");
                port.setText("");
            }
        });

        //register按钮事件
        registerBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //把status设置成1
                Utils.setStatus(1);
            }
        });

        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nickName = getName();
                String passwd = getPasswd();
                int port = getPort();
                if (port < 9999 || port > 65535) {
                    JOptionPane.showInputDialog("Port must in [10000, 65535]");
                    return;
                }
                String ip = "";
                //获取本机ip
                try {
                    InetAddress address = InetAddress.getLocalHost();
                    ip = address.getHostAddress().toString();
                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                }
                if (ip.equals("")) {
                    JOptionPane.showInputDialog("无法得到本机IP");
                    ip = JOptionPane.showInputDialog(null, "input your ip manually.");
                }
                //填充messageDto
                MessageDto entity = new MessageDto();
                entity.setOption(1);    //登陆
                entity.setOperatorNickName(nickName);   //没有登陆 无法获得用户id
                //填充login对象
                LoginJob loginForm = new LoginJob();
                loginForm.setOperatorId(-1);    //刚刚登陆时 用户不知道自己id
                loginForm.setId(-1);
                loginForm.setNickName(getName());
                loginForm.setPort(getPort());
                loginForm.setIp(ip);
                loginForm.setTime(new Date());
                //把loginJob序列化成str
                ObjectMapper objectMapper = new ObjectMapper();
                String context = "";
                try {
                    context = objectMapper.writeValueAsString(loginForm);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                //entity对象拼装完成
                entity.setContext(context);
                //发送login消息
                try {
                    Utils.sendEntity(entity);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                //将本地信息更新
                Utils.setStatus(2);
                //填好本地端口和ip
                Utils.setSelfPort(getPort());
                Utils.setSelfIpStr(ip);
                Utils.setSelfName(getName());
            }
        });

    }


    //匿名内部类获得外部name
    public String getName() {
        return this.nickName.getText().trim();
    }

    //匿名内部类得到外部port
    public int getPort() {
        return Integer.parseInt(this.port.getText());
    }

    //匿名内部类得到外部passwd
    public String getPasswd() {
        return this.passwd.getText().trim();
    }


    public static void main(String[] args) {
        LoginView lv = new LoginView();
        lv.setVisible(true);
    }
}
