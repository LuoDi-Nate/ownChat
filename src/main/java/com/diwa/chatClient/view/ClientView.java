package com.diwa.chatClient.view;

import com.diwa.chatClient.Vairable.Utils;
import com.diwa.common.dto.Message2Client;
import com.diwa.common.dto.MessageDto;
import com.diwa.common.dto.OnlineFriend;
import com.diwa.common.job.FlashFriendJob;
import com.diwa.common.job.MessageJob;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.*;
import java.util.List;

/**
 * Created by di on 2/5/15.
 */
public class ClientView extends JFrame {
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

    public ClientView() {
        cc = getContentPane();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(400, 500);
        setTitle("ownChatClient        v1.0");
        setLocation(400, 200);
        init();
    }

    public void init() {
        cc.setLayout(new GridLayout(2, 1));

        //top
        topPanel = new JPanel();
        versionLabel = new JLabel("\tThx for useing this.    ver:[1.0]");
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
        friendContext.setLayout(new GridLayout(1, 1));
        friendContext.add(new JScrollPane(friendList));
        friendList.setEditable(false);
        friendList.setLineWrap(true);

        JPanel tempFriend = new JPanel();
        friendPanel.add(tempFriend, BorderLayout.SOUTH);
        tempFriend.setLayout(new GridLayout(2, 1));
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
        profile1 = new JLabel("NickName:[" + Utils.getSelfName() + "]");
        profile2 = new JLabel("Port:[" + Utils.getSelfPort() + "]");
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
        downRightPanel1.setLayout(new GridLayout(2, 1));
        downRightPanel1.add(profile1);
        downRightPanel1.add(profile2);
        downRightPanel2.setLayout(new GridLayout(2, 1));
        downRightPanel2.add(exportBtn);
        downRightPanel2.add(exitBtn);

        bottomPanel2.add(sendBtn);
        bottomPanel2.add(resetBtn);


        actionInit();
    }

    public void actionInit() {
        confirmFriendBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String msg = "";
                int distFriendInt = 0;

                try {
                    //先试图按照id解析
                    distFriendInt = Integer.parseInt(nowFriend.getText());
                } catch (Exception e2) {
                    //用户输入的不是数字
                    String friendNickName = nowFriend.getText().trim();
                    int friendId = Utils.getIdByName(friendNickName);
                    if(friendId == -1){
                        //这个好友不存在
                        msg = "u try to talk someone, but\n" + nowFriend.getText() + " is not exists, try again please.\n";
                        flushDisplay(msg);
                    }else {
                        //好友存在 根据nickname拿到id
                        distFriendInt = friendId;
                    }
                }
                //not 0, mean a correct friend
                if (distFriendInt != 0) {
                    //看本地好友列表有没有这个好友
                    HashMap<Integer, String> friendMap = Utils.getFriendMap();
                    String friendNickName = friendMap.get(distFriendInt);
                    if(friendNickName == null){
                        //没有该好友
                        msg = "u try to talk someone, but\n" + nowFriend.getText() + " is not exists, try again please.\n";
                        flushDisplay(msg);
                        return;
                    }
                    //有该好友
                    Utils.setDistFriend(distFriendInt);
                    msg = "u want to talk with [" + friendNickName + "] , ^_^  switch display ...";
                    flushConsole(msg);
                    //把设置flag调成true
                    Utils.setSetDistFriendOrNot(true);
                    //把视图切换到该聊天对象
                    flushDisplayById(distFriendInt);
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
                FlashFriendJob flashFriendJob = new FlashFriendJob();
                flashFriendJob.setOperatorId(Utils.getSelfId());
                flashFriendJob.setSelfIp(Utils.getSelfIpStr());
                flashFriendJob.setSelfNickName(Utils.getSelfName());
                flashFriendJob.setSelfPort(Utils.getSelfPort());

                ObjectMapper objectMapper = new ObjectMapper();
                String context = "";
                try {
                    context = objectMapper.writeValueAsString(flashFriendJob);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                MessageDto entity = new MessageDto();
                entity.setOperatorNickName(Utils.getSelfName());
                entity.setContext(context);
                entity.setOption(4);

                try {
                    Utils.sendEntity(entity);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                flushConsole("    U just ask server for friends who online");

            }
        });

        sendBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //判断是否有聊天对象
                if (!Utils.isSetDistFriendOrNot()) {
                    flushDisplay("sorry buddy, please choose a chat target first!");
                    return;
                }
                MessageJob messageJob = new MessageJob();
                messageJob.setFromId(Utils.getSelfId());
                messageJob.setToId(Utils.getDistFriend());
                messageJob.setContext(inputText.getText());
                messageJob.setCreateTime(new Date());
                messageJob.setToNickName(Utils.getFriendMap().get(Utils.getDistFriend()+""));

                String context = "";
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    context = objectMapper.writeValueAsString(messageJob);
                } catch (Exception e3) {
                    logger.error("Json error!", e3);
                }

                MessageDto entity = new MessageDto();
                entity.setOption(2);
                entity.setOperatorNickName(Utils.getSelfName());
                entity.setContext(context);
                try {
                    Utils.sendEntity(entity);
                } catch (IOException e1) {
                    logger.error("send entity error!", e1);
                }

                SetLogById("[U] :" + inputText.getText(), Utils.getSelfId());
                inputText.setText("");
                flushDisplayById(Utils.getDistFriend());


            }
        });

        //打开对server的收听
//        openPhone();
    }

    //刷新聊天记录 并刷新显示
    public void SetLogById(String str, int id) {
        Map<String, String> history = Utils.getHistory();
        String oldHistory = history.get(id + "");
        if (oldHistory == null) {
            oldHistory = "U are talking with : [" + Utils.getDistFriend() + "]\n";
        }
        String newHistory = oldHistory + "\n" +
                new Date().toString() + "\n    " + str;
        history.remove(Utils.getDistFriend());
        history.put(id + "", newHistory);
    }

    public static void main(String[] args) {
        ClientView cv = new ClientView();
        cv.setVisible(true);
    }

    //时刻等待的线程 从server那边获取消息
    class phoneKeeper extends Thread {

        @Override
        public void run() {
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                int selfPort = Utils.getSelfPort();
                DatagramSocket clientPhone = new DatagramSocket(selfPort);
                logger.info("phone runnig start on port"+Utils.getSelfPort());

                while (true) {
                    //退出时 关闭
                    if (Utils.getStatus() == 3) {
                        break;
                    }

                    byte[] buff = new byte[2048];
                    DatagramPacket packet = new DatagramPacket(buff, buff.length);
                    clientPhone.receive(packet);
                    String jobStr = new String(packet.getData(), 0, packet.getLength());
                    Message2Client message = objectMapper.readValue(jobStr, Message2Client.class);
                    logger.info(message.toString());
                    //如果是系统信息
                    if (message.getFromId() == -10086) {
                        dealWithSystem(message);
                    } else {
                        //不是系统信息 有人给自己发信息 判断是否是当前聊天者
                        if (Utils.getDistFriend() == message.getFromId()) {
                            //当前聊天对象 直接加入日志 并且刷新到面板
                            SetLogById("   [" + message.getFromName() + "]:"
                                    + message.getMsg(), message.getFromId());

                            flushDisplayById(message.getFromId());
                        } else {
                            //不是当前聊天对象
                            SetLogById("   [" + message.getFromName() + "]:"
                                    + message.getMsg(), message.getFromId());

                            consoleLabel.setText("\tU got a new msg from ["+message.getFromId()+"]!!");
                        }
                    }

                }
            } catch (SocketException e) {
                e.printStackTrace();
                logger.error(String.valueOf(e));
            } catch (IOException e2) {
                e2.printStackTrace();
                logger.error(e2.toString());

            }

        }
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

    public void flushDisplay(String str) {
        String oldStr = displayText.getText();
        String timeStr = new Date().toString();
        displayText.setText(oldStr + "\n" + timeStr + "\n    " + str);
    }

    public void flushDisplayById(int friendId) {
        String context = Utils.getHistory().get(friendId+"");
        displayText.setText(context);
    }

    public void flushFriend(String str) {
        friendList.setText(str);
    }

    public void updateSelf() {
        profile1.setText("hi, " + Utils.getSelfName());
        profile2.setText("Port:[" + Utils.getSelfPort() + "]");
    }

    public void flushConsole(String str) {
        consoleLabel.setText("\t" + str);
    }

    //打开电话 等待随时被server叫
    public void openPhone() {
        phoneKeeper phoneKeeper = new phoneKeeper();
        logger.info("phone is ready");
        phoneKeeper.start();
        logger.info("phone should running");
    }

    //处理系统来的信息
    public void dealWithSystem(Message2Client message) throws IOException {
        logger.info("got a list of friend");
        String list = message.getMsg();
        ObjectMapper objectMapper = new ObjectMapper();
        OnlineFriend friendMapObj = objectMapper.readValue(list, OnlineFriend.class);

        HashMap<String, Integer> friendMap = friendMapObj.getFriendMap();
        String flashStr = "";
        for (String str:friendMap.keySet()){
            Utils.friendMap.put(friendMap.get(str), str);
            flashStr += "    $["+friendMap.get(str)+"]"+"\n";
            flashStr += str;
        }
        flushFriend(flashStr);
    }
}
