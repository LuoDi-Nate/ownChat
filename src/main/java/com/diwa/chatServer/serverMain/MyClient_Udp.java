package com.diwa.chatServer.serverMain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.*;
import java.util.Date;

import javax.swing.*;





class clientLog{		//用来存放客户端的记录 上限10000  静态
    private static String[] log=new String[10000];
    private static int count=1;

    public static String getLog() {
        String all="";
        for(int i=0;i<count;i++){
            all+=log[i];
        }
        return all;
    }

    public static void addLog(String str) {
        log[count]=str;
        count++;
    }

}


public class MyClient_Udp extends JFrame{
    //用户名 使用端口号
    private String nickname="###";
    private int port;
    private String serverIp="###";

    private String destNickname="";
    private boolean destIpSet=false;
    private Socket s;

    //各种组件
    private JTextArea displayText;
    private JLabel norLab;
    private JTextField inputTxt;
    private JButton sendBtn;
    private JTextArea ipTxt;
    private JButton ipconfigBtn;
    private JButton confirmDest;
    private JButton exitBtn;
    private Container cc;
    private JButton connectionBtn;

    //构造器
    public MyClient_Udp()throws Exception{

        //构造窗口的开始  询问用户的昵称 以及想使用的端口号  保证输入不为空 以及服务器ip
        nickname=JOptionPane.showInputDialog("请输入一个昵称:");
        while(nickname.equals("###")||nickname.equals("")){
            nickname=JOptionPane.showInputDialog("请输入正确的昵称！:");
        }
        //询问用户需要的服务器ip地址

        serverIp=JOptionPane.showInputDialog("输入服务器ip地址:");
        while(serverIp.equals("###")||serverIp.equals("")){
            serverIp=JOptionPane.showInputDialog("请输入正确的服务器地址！");
        }

        //录入端口号 保证在9999-65534
        port=Integer.parseInt(JOptionPane.showInputDialog("请选择一个端口号 (推荐9999):"));
        while(port<9999 || port >65534){
            port=Integer.parseInt(JOptionPane.showInputDialog("错误的端口号 (推荐9999) 重新键入:"));

        }
        setTitle("   你好! "+nickname+"   :)        端口占用:["+port+"]    ");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cc=getContentPane();
        setResizable(false);

        setSize(400,600);
        setLocation(400,100);
        init();
    }
    public void init()throws Exception{		//创建窗体	实现界面
        norLab=new JLabel("                                             Chatting[Client]");

        cc.add(norLab,BorderLayout.NORTH);

        JPanel pan1=new JPanel();
        pan1.setLayout(new GridLayout(2, 1));

        cc.add(pan1,BorderLayout.CENTER);
        displayText=new JTextArea();

        displayText=new JTextArea();
        displayText.setAutoscrolls(true);

        displayText.setEditable(false);
        pan1.add(new JScrollPane(displayText));
        displayText.setWrapStyleWord(true);

        JPanel pan2=new JPanel();
        pan1.add(pan2);
        pan2.setLayout(new GridLayout(2,1));
        JPanel pan4=new JPanel();

        ipTxt=new JTextArea("这里输入对方昵称");
        ipconfigBtn =new JButton("登陆");

        JPanel pan5=new JPanel();
        pan5.setLayout(new GridLayout(1,2));
        pan5.add(ipTxt);
        ipTxt.setBackground(Color.cyan);
        confirmDest =new JButton("确认对方");
        JPanel pan9=new JPanel();
        pan9.setLayout(new GridLayout(1,2));
        pan9.add(confirmDest);
        pan9.add(ipconfigBtn);
        pan5.add(pan9);


        pan4.setLayout(new GridLayout(5,1));
        pan4.add(new JLabel("========================================================"));
        pan4.add(new JLabel("       U can view UP  ,edit DOWN   ^_^  ......          【迪娃】made_"));
        pan4.add(pan5);
        JPanel pan8=new JPanel();
        pan8.setLayout(new GridLayout(1,2));
//		pan4.add(new JLabel("       Ctrl + [s]  SaveChatLog"));
        connectionBtn =new JButton("NO");
        connectionBtn.setBackground(Color.RED);
        pan8.add(new JLabel("       Ctrl + [s]  SaveChatLog"));

        JPanel pan91=new JPanel();
        pan91.add(new JLabel("CONNECTION:"));
        pan91.add(connectionBtn);
        pan8.add(pan91);

        pan4.add(pan8);
        pan4.add(new JLabel("========================================================"));

        pan2.add(pan4);
        inputTxt=new JTextField();
        pan2.add(inputTxt);


        JPanel pan3=new JPanel();
        cc.add(pan3,BorderLayout.SOUTH);

        sendBtn =new JButton("SEND         [Ctrl+Enter]");
        exitBtn =new JButton("exit");
        pan3.setLayout(new GridLayout(1,2));
        pan3.add(sendBtn);
        pan3.add(exitBtn);

        actioninit();
    }

    //登陆函数
    public void login() throws Exception{
        s=new Socket(serverIp,port);
//			System.out.println(s.getInetAddress().getHostAddress());
        BufferedWriter buf=new BufferedWriter( new OutputStreamWriter(s.getOutputStream()));
        buf.write("+@#"+nickname+"#"+destNickname+"#"+"我上线了");
        buf.newLine();
        buf.flush();
//			String sendStr=s.getInetAddress().getHostAddress()+"#"
//							+nickname+"#"+destIp+"#"+nickname+"来了\r\n";
//			buf.write(sendStr);
//			buf.flush();
        connectionBtn.setText("RUN");
        connectionBtn.setBackground(Color.GREEN);

        displayCheck dc=new displayCheck();
        dc.start();
    }

    //检测函数   持续监听服务器返回数据
    class displayCheck extends Thread{
        @Override
        public void run() {


            // TODO 自动生成的方法存根
            BufferedReader bufin = null;
            try {
                bufin = new BufferedReader(new InputStreamReader(s.getInputStream()));
            } catch (IOException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
            while(true){
//				String old3=displayText.getText();
                try {
                    Date now=new Date();
                    String msg=bufin.readLine();
                    System.out.println(msg);
                    String[] msgok=msg.split("#");

                    clientLog.addLog(now.toString());
                    clientLog.addLog("\n"+now+msgok[0]+"发送来了\n");
                    clientLog.addLog(msgok[1]+"\n");
                    flashConsole();

                } catch (IOException e) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }
//				try {
//					clientLog.addLog("\n["+s.getInetAddress().getHostAddress()+":] "+now+"\n      "+bufin.readLine());
//					flashConsole();
//				} catch (IOException e) {
//					// TODO 自动生成的 catch 块
//					e.printStackTrace();
//				}
            }
        }
    }

    //用来刷新显示板的内容
    public void flashConsole(){
        String a=clientLog.getLog();
        displayText.setText(a);
    }

    //发送信息线程

    class sendMsg extends Thread{
        public void run(){
            try {
                BufferedWriter bufout=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

                //遵循自定义聊天规范
//			buf.write("+@#"+nickname+"#"+destNickname+"#"+"我上线了");

                System.out.println("正在发送：  "+"-@#"+nickname+"#"+destNickname+"#"+inputTxt.getText());
                bufout.write("-@#"+nickname+"#"+destNickname+"#"+inputTxt.getText());

                bufout.newLine();
                bufout.flush();
            } catch (IOException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }

            //将发送的信息同步显示到自己的屏幕上
            Date now=new Date();
//		String old=displayText.getText();
//		displayText.setText(old+"\n[yourself:]"+now+inputTxt.getText());
            clientLog.addLog("\n["+nickname+":]"+now+"\n      "+inputTxt.getText());
            flashConsole();
            inputTxt.setText("");

        }
    }


    public void actioninit()throws Exception{
        //给输入文本框添加监听
        inputTxt.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent arg0) {
                // TODO 自动生成的方法存根

            }

            public void keyReleased(KeyEvent arg0) {
                // TODO 自动生成的方法存根

            }

            public void keyPressed(KeyEvent e) {
                // TODO 自动生成的方法存根
                if(e.isControlDown()&&e.getKeyCode()==KeyEvent.VK_ENTER){
//					System.out.println("46456546");
                    sendMsg sm=new sendMsg();
                    sm.start();
                }
                if(e.isControlDown()&&e.getKeyCode()==KeyEvent.VK_S){
//					System.out.println("111111111111111111111");
                    String url=JOptionPane.showInputDialog("请输入保存聊天记录路径（例如C:\\log.txt）");
                    File file=new File(url);
                    try {
                        file.createNewFile();
                    } catch (IOException e1) {
                        // TODO 自动生成的 catch 块
                        e1.printStackTrace();
//						JOptionPane.showMessageDialog(exitBtn,"错误的文件地址！" );
                    }
                    String log=clientLog.getLog();

                    BufferedWriter buf=null;
                    try {
                        buf=new BufferedWriter(
                                new OutputStreamWriter(new FileOutputStream(file)));

                        buf.write(log);
                        buf.flush();

                    } catch (Exception e1) {
                        // TODO 自动生成的 catch 块
                        e1.printStackTrace();
                    }finally{
                        if(buf!=null){
                            try {
                                buf.close();
                            } catch (IOException e1) {
                                // TODO 自动生成的 catch 块
                                e1.printStackTrace();
                            }
                        }
                    }

                }
            }
        });

        //给发送键设置监听
        sendBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                sendMsg sm=new sendMsg();
                sm.start();
            }
        });

        //给登陆键添加事件
        ipconfigBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                // TODO 自动生成的方法存根
                if(destIpSet!=true){
                    clientLog.addLog("\n未设置聊天目标！\n");
                    flashConsole();
                }else{

                    clientLog.addLog("\n正在连接服务器。。。\n");
                    flashConsole();

                    try {
                        login();
                    } catch (Exception e) {
                        // TODO 自动生成的 catch 块
                        e.printStackTrace();
                    }

                    clientLog.addLog("\n连接服务器成功！");
                    flashConsole();
                }
            }
        });

        //给确认ip按键添加事件
        confirmDest.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                // TODO 自动生成的方法存根
                destNickname=ipTxt.getText();
                destIpSet=true;
                clientLog.addLog("\n对方IP地址已经设置为："+destNickname+"\n");
                flashConsole();
            }
        });

        //给退出键添加事件
        exitBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                // TODO 自动生成的方法存根
                System.exit(0);
            }
        });

        //
    }

    public static void main(String[] args)throws Exception {
        MyClient_Udp cs=new MyClient_Udp();
        cs.setVisible(true);
    }
}
