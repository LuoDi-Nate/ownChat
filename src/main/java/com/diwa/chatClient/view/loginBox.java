package com.diwa.chatClient.view;

/**
 * Created by di on 18/4/15.
 */
//loginBox.java

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/**
 * 登陆窗口
 *
 *
 */

public class loginBox extends JFrame{
    /*
     * 通过屏幕大小确定位置
     */
    private static Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();
    private int locatedX = screenSize.width/3;
    private int locatedY = screenSize.height/3;

    /*
     * 各种图片
     */
    private static Image bgImg = kit.getImage(loginBox.class.getClassLoader().getResource("images/background.jpg"));
    private static Image Icon = kit.getImage(loginBox.class.getClassLoader().getResource("images/Icon.jpg"));
    private static Image photoImg = kit.getImage(loginBox.class.getClassLoader().getResource("images/bigphoto.jpg"));
    /*
     * 所需组件
     */
    private JPanel bJPanel = null;  //内存窗格组件，用以装载其他组件 
    private JLabel background = null; //背景图片
    private JLabel photo = null;  //头像
    private JLabel name = null;  //用户名
    private JTextField logName = null; //输入用户名的文本框
    private JLabel password = null; //密码
    private JPasswordField logPass = null; //输入密码的文本框
    private JButton login = null; //登录按钮

    public static void main(String[] args) {
        new loginBox().launchFrame();
    }

    public void launchFrame() {
        this.setBounds(locatedX,locatedY,350,250);  //设置位置
        this.setVisible(true);  //设置可见性
        this.setTitle("welcome to StudyZone!");  //设置可见性
        this.setIconImage(Icon);  //设置图标
        bJPanel = new JPanel();  //定义bJPanel
        bJPanel.setLayout(null);  //手动设置布局管理器
        this.getContentPane().add(bJPanel); //将bJPanel添加到内容窗格
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //设置关闭窗口按钮
        
        /*
         * 定义bJPanel内的组件
         */
        Icon bgIcon = new ImageIcon(bgImg);  //背景
        background = new JLabel(bgIcon);
        background.setBounds(0,0,340,66);

        Icon photoIcon = new ImageIcon(photoImg);  //头像
        photo = new JLabel(photoIcon);
        photo.setBounds(10,80,90,90);

        Font font = new Font("微软雅黑",Font.PLAIN,15);  //设置字体
        name = new JLabel("用户名：");  //用户名
        name.setFont(font);
        name.setBounds(100,80,80,30);

        logName = new JTextField();  //用户名输入框
        logName.setBounds(160,85,150,22);

        password = new JLabel("   密码："); //密码
        password.setFont(font);
        password.setBounds(100,120,80,30);

        logPass = new JPasswordField();  //密码输入框
        logPass.setBounds(160,125,150,22);

        login = new JButton("登录");  //登录按钮
        login.setFont(new Font("微软雅黑",Font.PLAIN,12));
        login.setBounds(235,160,70,30);
        login.addActionListener(new ActionListener()   //添加按钮监听器
        {
            public void actionPerformed(ActionEvent e) {
//                new StudyClient().launchFrame();
//                setVisible(false);  //当启动StudyClient时，将登录窗口设为不可见
            }
        });
        /*
         * 将组件添加到JPanel
         */
        bJPanel.add(background);  //背景
        bJPanel.add(photo);       //头像
        bJPanel.add(name);  //用户名
        bJPanel.add(password); //密码
        bJPanel.add(logName); //用户名输入框
        bJPanel.add(logPass); //密码输入框
        bJPanel.add(login); //登录按钮

    }

}
